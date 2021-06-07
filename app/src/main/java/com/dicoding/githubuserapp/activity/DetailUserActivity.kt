package com.dicoding.githubuserapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.githubuserapp.R
import com.dicoding.githubuserapp.adapter.SectionsPagerFollowAdapter
//import com.dicoding.githubuserapp.adapter.SectionsPagerAdapter
import com.dicoding.githubuserapp.databinding.ActivityDetailUserBinding
import com.dicoding.githubuserapp.view_model.DetailUserViewModel
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_USER = "extra_user"
    }
    private lateinit var binding: ActivityDetailUserBinding

    private lateinit var detailUserViewModel: DetailUserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val users = intent.getStringExtra(EXTRA_USER)
        val bundle = Bundle()
        bundle.putString(EXTRA_USER, users)
        supportActionBar?.title = users

//
//        val sectionsPagerFollowAdapter = SectionsPagerFollowAdapter(this)
//        binding.viewPager2.adapter = sectionsPagerFollowAdapter
//        TabLayoutMediator(binding.tabs, binding.viewPager2) { tab, position ->
//            tab.text = resources.getString(TAB_TITLES[position])
//        }.attach()
        supportActionBar?.elevation = 0f

        val sectionsPagerFollowAdapter = SectionsPagerFollowAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionsPagerFollowAdapter
            tabs.setupWithViewPager(viewPager)
        }
        detailUserViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)
        users?.let { detailUserViewModel.setDetailUsers(it) }
        detailUserViewModel.getDetailUsers().observe(this,{
            if (it != null) {
                binding.apply {
                    txtVName.text = it.name
                    txtVLocation.text = it.location
                    txtVCompany.text = it.company
                    txtVValueRepos.text = it.repos
                    txtVValueFollowers.text = it.followers
                    txtVValueFollowing.text = it.following
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar)
                        .centerCrop()
                        .into(imgAvatarUser)

                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_change_settings) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        if (item.itemId == R.id.action_about) {
            val aIntent = Intent(this, AboutActivity::class.java)
            startActivity(aIntent)
        }
        return super.onOptionsItemSelected(item)
    }

}