package com.dicoding.githubuserapp.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.githubuserapp.activity.DetailUserActivity
import com.dicoding.githubuserapp.model.DataUser
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception

class DetailUserViewModel: ViewModel() {
    companion object {
        private val TAG = DetailUserActivity::class.java.simpleName
    }

    val detailUsers = MutableLiveData<DataUser>()

    fun setDetailUsers(detailUsername : String) {
        val client = AsyncHttpClient()
        val apiKey = "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"
        val url = "https://api.github.com/users/$detailUsername"
        client.addHeader("Authorization", "token ${apiKey} ")
        client.addHeader("User-Agent", "request")
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray
            ) {
                val result = String(responseBody)
                Log.d(TAG, result)
                try {
                    val itemUsers = JSONObject(result)
                    val dataUser = DataUser()
                    dataUser.avatar = itemUsers.getString("avatar_url")
                    dataUser.username = itemUsers.getString("login")
                    dataUser.name = itemUsers.getString("name")
                    dataUser.location = itemUsers.getString("location")
                    dataUser.company = itemUsers.getString("company")
                    dataUser.repos = itemUsers.getString("public_repos")
                    dataUser.followers = itemUsers.getString("followers")
                    dataUser.following = itemUsers.getString("following")
                    detailUsers.postValue(dataUser)
                }catch (e: Exception){
                    Log.d("Exception", e.message.toString())
                }

            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                Log.d("onFailure", error.message.toString())
            }
        })
    }

    fun getDetailUsers(): LiveData<DataUser> {
        return detailUsers
    }
}