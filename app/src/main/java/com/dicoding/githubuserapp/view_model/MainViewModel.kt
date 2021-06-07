package com.dicoding.githubuserapp.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.githubuserapp.activity.MainActivity
import com.dicoding.githubuserapp.model.DataUser
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception

class MainViewModel: ViewModel() {
    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    val listSearchingUsers = MutableLiveData<ArrayList<DataUser>>()

    fun setSearchUsers(searchUser : String) {
        val listItemUsers = ArrayList<DataUser>()
        val client = AsyncHttpClient()
        val apiKey = "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"
        val url = "https://api.github.com/search/users?q=$searchUser"
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
                    val responseObject = JSONObject(result)
                    val listApi = responseObject.getJSONArray("items")

                    for (i in 0 until listApi.length()){
                        val itemUser = listApi.getJSONObject(i)
                        val dataUser = DataUser()
                        dataUser.username = itemUser.getString("login")
                        dataUser.avatar = itemUser.getString("avatar_url")
                        dataUser.id = itemUser.getInt("id")
                        listItemUsers.add(dataUser)
                    }
                    listSearchingUsers.postValue(listItemUsers)
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

    fun getUsers(): LiveData<ArrayList<DataUser>> {
        return listSearchingUsers
    }
}