package com.dicoding.githubuserapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubuserapp.adapter.ListDataUserAdapter
import com.dicoding.githubuserapp.R
import com.dicoding.githubuserapp.model.DataUser
import com.dicoding.githubuserapp.databinding.ActivityMainBinding
import com.dicoding.githubuserapp.view_model.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var listDataUserAdapter: ListDataUserAdapter
    private lateinit var binding : ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = resources.getString(R.string.title)

        listDataUserAdapter = ListDataUserAdapter()
        listDataUserAdapter.notifyDataSetChanged()

        binding.recylerView.layoutManager = LinearLayoutManager(this)
        binding.recylerView.adapter = listDataUserAdapter

        listDataUserAdapter.setOnItemClickCallback(object : ListDataUserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: DataUser) {
                Intent(this@MainActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USER, data.username)
                    startActivity(it)
                }
                showSelectedUser(data)
            }

        })

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        Log.d("Data User", toString())
        binding.btnSearch.setOnClickListener {
            val dataUser = binding.edtSearch.text.toString()
            if (dataUser.isEmpty()) return@setOnClickListener
            showIndicatorLoading(true)
            mainViewModel.setSearchUsers(dataUser)
        }
        mainViewModel.getUsers().observe(this, {dataUser ->
            if (dataUser != null) {
                listDataUserAdapter.setData(dataUser)
                showIndicatorLoading(false)
            }
        })
    }

    private fun showIndicatorLoading(state: Boolean) {
        if(state) {
            binding.apply {
                imgData.visibility = View.GONE
                txtVDataEmpty.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                imgData.visibility = View.GONE
                txtVDataEmpty.visibility = View.GONE
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun showSelectedUser(dataUser: DataUser) {
        Toast.makeText(this, dataUser.username, Toast.LENGTH_SHORT).show()
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
