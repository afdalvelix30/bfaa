//package com.dicoding.githubuserapp.fragment
//
//import androidx.lifecycle.ViewModelProvider
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.dicoding.githubuserapp.R
//import com.dicoding.githubuserapp.view_model.FollowingUserViewModel
//
//class FollowingUserFragment : Fragment() {
//
//    companion object {
//        fun newInstance() = FollowingUserFragment()
//    }
//
//    private lateinit var viewModel: FollowingUserViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.following_user_fragment, container, false)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(FollowingUserViewModel::class.java)
//        // TODO: Use the ViewModel
//    }
//
//}