package com.zypherdev.lambrk.activities

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.zypherdev.lambrk.R
import com.zypherdev.lambrk.adapters.HomeViewPagerAdapter
import com.zypherdev.lambrk.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var mContext: Context

    private lateinit var viewBinding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val viewPager = viewBinding.vpHome
        viewPager.adapter = HomeViewPagerAdapter(this)
        viewBinding.vpHome.isUserInputEnabled = false

        // Set up BottomNavigationView
        val bottomNavigationView = viewBinding.bottomNavigationView

        // Sync ViewPager2 and BottomNavigationView
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bottomNavigationView.menu.getItem(position).isChecked = true
            }
        })

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_home_menu_item -> viewPager.currentItem = 0
                R.id.search_home_menu_item -> viewPager.currentItem = 1
                R.id.flickr_home_menu_item -> viewPager.currentItem = 2
                R.id.profile_home_menu_item -> viewPager.currentItem = 3
            }
            true
        }

    }
}