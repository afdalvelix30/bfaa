package com.dicoding.githubuserapp.view_model

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.githubuserapp.fragment.FollowersUserFragment
import com.dicoding.githubuserapp.model.DataUser
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject

class FollowersUserViewModel: ViewModel() {
    companion object {
        private val TAG = FollowersUserFragment::class.java.simpleName
    }
    val listFollowers = MutableLiveData<ArrayList<DataUser>>()
    val detailUsers = MutableLiveData<DataUser>()

    fun setFollowersUsers(followersUser: String) {
        val listFollowersUsers = ArrayList<DataUser>()
        val client = AsyncHttpClient()
        val apiKey = "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"
        val url = "https://api.github.com/users/$followersUser/following"
        client.addHeader("Authorization", "token ${apiKey}")
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
                    val responseArray = JSONArray(result)
                    for (i in 0 until responseArray.length()){
                        val itemFollowers = responseArray.getJSONObject(i)
                        val dataUser = DataUser()
                        dataUser.username = itemFollowers.getString("login")
                        dataUser.avatar = itemFollowers.getString("avatar_url")
                        dataUser.id = itemFollowers.getInt("id")
                        listFollowersUsers.add(dataUser)
                    }
                    listFollowers.postValue(listFollowersUsers)
                    setDetailUsers(followersUser)
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

    fun setDetailUsers(detailUsername : String) {
        val client = AsyncHttpClient()
        val apiKey = "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"
        val url = "https://api.github.com/users/$detailUsername"
        client.addHeader("Authorization", "token ${apiKey}")
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

    fun getFollowersUsers(): LiveData<ArrayList<DataUser>> {
        return listFollowers
    }
}