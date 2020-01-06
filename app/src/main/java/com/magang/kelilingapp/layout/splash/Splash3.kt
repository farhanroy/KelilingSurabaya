package com.magang.kelilingapp.layout.splash


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.magang.kelilingapp.R

/**
 * A simple [Fragment] subclass.
 */
class Splash3 : Fragment() {
    companion object {
        fun newInstance() = Splash3()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash3, container, false)
    }


}
