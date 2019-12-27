package com.magang.kelilingapp.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.magang.kelilingapp.layout.splash.Splash1
import com.magang.kelilingapp.layout.splash.Splash2
import com.magang.kelilingapp.layout.splash.Splash3

class SplashViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                Splash1.newInstance()
            }
            1 -> {
                Splash2.newInstance()
            }
            2 -> {
                Splash3.newInstance()
            }
            else -> {
                Splash1.newInstance()
            }
        }
    }

}