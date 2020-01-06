package com.magang.kelilingapp.layout


import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.magang.kelilingapp.R
import com.magang.kelilingapp.adapter.MostVisitedAdapter
import com.magang.kelilingapp.adapter.WisataListAdapter
import com.magang.kelilingapp.databinding.FragmentDashboardPageBinding
import com.magang.kelilingapp.viewmodel.WisataViewModel
import kotlin.math.abs
import kotlin.math.max


class DashboardPage : Fragment() {
    private lateinit var viewModel: WisataViewModel
    private lateinit var adapter: WisataListAdapter
    private lateinit var mostVisitedAdapter: MostVisitedAdapter
    private lateinit var binding: FragmentDashboardPageBinding
    private val PERMISSION_REQUEST_CODE: Int = 8

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard_page, container, false)
        if(!checkPermission())requestPermission()else Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_SHORT).show()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shimmerStart()
        viewModel = ViewModelProviders.of(this).get(WisataViewModel::class.java)
        binding.wisataViewModel = viewModel
        viewModel.requestListWisata()

        setObserve()
    }

    override fun onResume() {
        super.onResume()
        shimmerStart()
    }

    override fun onPause() {
        shimmerStop()
        super.onPause()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_REQUEST_CODE -> if (grantResults.isNotEmpty()){
                val locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
                val coarseAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED

                if(locationAccepted && coarseAccepted){
                    Toast.makeText(requireContext(), "Permission Granted, Now you can access location data", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireContext(), "Permission Denied, you can't access location data", Toast.LENGTH_SHORT).show()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)){
                            showMessageOkCancel("You need to allow access to both the permissions", DialogInterface.OnClickListener { _, _ ->
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    requestPermissions(arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION), PERMISSION_REQUEST_CODE)
                                }
                            })
                        }
                    }
                }

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun checkPermission() : Boolean{
        val fineLocation = ContextCompat.checkSelfPermission(requireContext(),ACCESS_FINE_LOCATION)
        val coarseLocation = ContextCompat.checkSelfPermission(requireContext(),ACCESS_COARSE_LOCATION)

        return fineLocation == PackageManager.PERMISSION_GRANTED && coarseLocation == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission(){
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION), PERMISSION_REQUEST_CODE)
    }

    private fun setObserve() {
        viewModel.wisataMutable.observe(this, Observer { t ->
            shimmerStop()
            binding.wisataShimmer.visibility = GONE
            adapter = WisataListAdapter(t.data, activity)
            mostVisitedAdapter = MostVisitedAdapter(t.data, activity?.applicationContext)
            setRecycler(adapter)
            setMostVisitedViewPager(mostVisitedAdapter)
        })
    }

    private fun setRecycler(adapters: WisataListAdapter){
        binding.rvDashboard.apply {
            adapter = adapters
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL, false)
            hasFixedSize()
        }
    }

    private fun setMostVisitedViewPager(adapters: MostVisitedAdapter){
        binding.viewPagerMost.apply {
            adapter = adapters
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            binding.dotsIndicatorDashboard.setViewPager2(binding.viewPagerMost)
            setPageTransformer { page, position ->
                val MIN_SCALE = 0.9f
                val MIN_ALPHA = 0.3f
                when {
                    position <-1 -> page.alpha = 0.toFloat()
                    position <=1 -> {
                        page.scaleX = max(MIN_SCALE, 1 - abs(position))
                        page.scaleY = max(MIN_SCALE, 1 - abs(position))
                        page.alpha = max(MIN_ALPHA, 1 - abs(position))

                    }
                    else -> page.alpha = 0.toFloat()
                }
            }
        }
    }

    private fun showMessageOkCancel(message:String, okListener:DialogInterface.OnClickListener){
        AlertDialog.Builder(requireActivity())
            .setMessage(message)
            .setPositiveButton("OK", okListener)
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }

    private fun shimmerStart(){
        binding.wisataShimmer.startShimmer()
    }
    private fun shimmerStop(){
        binding.wisataShimmer.stopShimmer()
    }


}
