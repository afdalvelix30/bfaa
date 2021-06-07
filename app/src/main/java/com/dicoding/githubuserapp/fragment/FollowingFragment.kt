//package com.dicoding.githubuserapp.fragment
//
//import android.content.Intent
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.dicoding.githubuserapp.R
//import com.dicoding.githubuserapp.activity.DetailUserActivity
//import com.dicoding.githubuserapp.adapter.ListDataUserAdapter
//import com.dicoding.githubuserapp.databinding.FragmentFollowingBinding
//import com.dicoding.githubuserapp.model.DataUser
//import com.dicoding.githubuserapp.view_model.FollowingViewModel
//
//class FollowingFragment : Fragment() {
//
//    private var _binding: FragmentFollowingBinding? = null
//    private val binding get() = _binding!!
//    private lateinit var followersViewModel: FollowingViewModel
//    private lateinit var listDataUserAdapter: ListDataUserAdapter
//    private lateinit var followingUsers: String
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val args = arguments
//        followingUsers = args?.getString(DetailUserActivity.EXTRA_USER).toString()
//        _binding = FragmentFollowingBinding.bind(view)
//        listDataUserAdapter = ListDataUserAdapter()
//        listDataUserAdapter.notifyDataSetChanged()
//        listDataUserAdapter.setOnItemClickCallback(object : ListDataUserAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: DataUser) {
//                Intent(context, DetailUserActivity::class.java).also {
//                    it.putExtra(DetailUserActivity.EXTRA_USER, data.username)
//                    startActivity(it)
//                }
//            }
//        })
//        binding.recylerView.adapter = listDataUserAdapter
//        binding.recylerView.layoutManager = LinearLayoutManager(activity)
//        binding.recylerView.setHasFixedSize(true)
//
//        showIndicatorLoading(true)
//        followersViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(FollowingViewModel::class.java)
//        followersViewModel.setFollowingUsers(followingUsers)
//        followersViewModel.getFollowingUsers().observe(viewLifecycleOwner, {
//            if (it != null) {
//                listDataUserAdapter.setData(it)
//                showIndicatorLoading(false)
//            }
//        })
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_following, container, false)
//        binding.recylerView.adapter = listDataUserAdapter
//    }
//
//    private fun showIndicatorLoading(state: Boolean) {
//        if(state) {
//            binding.progressBar.visibility = View.VISIBLE
//        } else {
//            binding.progressBar.visibility = View.GONE
//        }
//    }
//}