package com.magang.kelilingapp.layout


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.magang.kelilingapp.R
import com.magang.kelilingapp.adapter.SplashViewPagerAdapter
import com.magang.kelilingapp.databinding.FragmentSplashScreenBinding

/**
 * A simple [Fragment] subclass.
 */
class SplashScreen : Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding
    private lateinit var viewPagerAdapter: SplashViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_splash_screen, container, false)
        viewPagerAdapter = SplashViewPagerAdapter(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.splashScreenViewPager){
            adapter = viewPagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if(position != 2)binding.nextDashboard.visibility = GONE else binding.nextDashboard.visibility = VISIBLE
                }
            })
        }
        binding.dotsIndicator.setViewPager2(binding.splashScreenViewPager)
        binding.nextDashboard.setOnClickListener { findNavController().navigate(R.id.action_splashScreen_to_dashboardList) }
    }

}
