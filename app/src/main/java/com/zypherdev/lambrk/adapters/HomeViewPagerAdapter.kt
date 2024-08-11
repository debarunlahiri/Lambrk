package com.zypherdev.lambrk.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zypherdev.lambrk.fragments.FlickrFragment
import com.zypherdev.lambrk.fragments.HomeFragment
import com.zypherdev.lambrk.fragments.ProfileFragment
import com.zypherdev.lambrk.fragments.SearchFragment

class HomeViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity)  {
    override fun getItemCount(): Int {
        return 4 // Number of items in your BottomNavigationView
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> SearchFragment()
            2 -> FlickrFragment()
            3 -> ProfileFragment()
            else -> HomeFragment() // Default case
        }
    }

}