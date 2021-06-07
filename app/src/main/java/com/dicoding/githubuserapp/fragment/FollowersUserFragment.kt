package com.dicoding.githubuserapp.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubuserapp.view_model.FollowersUserViewModel
import com.dicoding.githubuserapp.R
import com.dicoding.githubuserapp.activity.DetailUserActivity
import com.dicoding.githubuserapp.adapter.ListDataUserAdapter
import com.dicoding.githubuserapp.databinding.FollowersUserFragmentBinding
import com.dicoding.githubuserapp.model.DataUser
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray

class FollowersUserFragment : Fragment(R.layout.followers_user_fragment) {

    private var _followersUserFragmentBinding: FollowersUserFragmentBinding? = null
    private val followersUserFragmentbinding get() = _followersUserFragmentBinding!!
    private lateinit var followersUserViewModel: FollowersUserViewModel
    private lateinit var listDataUserAdapter: ListDataUserAdapter
    private lateinit var followersUser: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _followersUserFragmentBinding = FollowersUserFragmentBinding.bind(view)
        listDataUserAdapter = ListDataUserAdapter()
        listDataUserAdapter.notifyDataSetChanged()
        val arguments = arguments
        followersUser = arguments?.getString(DetailUserActivity.EXTRA_USER).toString()

        followersUserFragmentbinding.recylerView.layoutManager = LinearLayoutManager(activity)
        followersUserFragmentbinding.recylerView.adapter = listDataUserAdapter
        followersUserFragmentbinding.recylerView.setHasFixedSize(true)

        followersUserViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowersUserViewModel::class.java)
        followersUserViewModel.setFollowersUsers(followersUser)
        followersUserViewModel.getFollowersUsers().observe(viewLifecycleOwner, {
            if (it != null)
                listDataUserAdapter.setData(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _followersUserFragmentBinding = null
    }


}