package com.dicoding.githubuserapp.adapter

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.githubuserapp.R
import com.dicoding.githubuserapp.fragment.FollowersUserFragment
//import com.dicoding.githubuserapp.fragment.FollowingUserFragment

class SectionsPagerFollowAdapter(private val context: Context,
                                 fragmentManager: FragmentManager,
                                 data: Bundle
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private var fragmentBundle: Bundle = data

    @StringRes
    private val TAB_TITLES = intArrayOf(
        R.string.tab1,
        R.string.tab2
    )

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = FollowersUserFragment()
        }
        fragment?.arguments = this.fragmentBundle

        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLES[position])
    }

}