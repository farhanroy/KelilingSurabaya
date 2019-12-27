package com.magang.kelilingapp.layout.splash


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator

import com.magang.kelilingapp.R
import kotlinx.android.synthetic.main.fragment_splash1.*

/**
 * A simple [Fragment] subclass.
 */
class Splash1 : Fragment() {
    companion object {
        fun newInstance() = Splash1()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iv_splash1.animate().apply {
            translationX(iv_splash1.width.toFloat())
            duration = 3000
            interpolator = AccelerateInterpolator()
            start()
        }
    }

}
