package com.magang.kelilingapp.layout


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import coil.api.load
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.magang.kelilingapp.R
import com.magang.kelilingapp.model.Datum
import com.magang.kelilingapp.util.Params
import kotlinx.android.synthetic.main.fragment_detail_page.*

/**
 * A simple [Fragment] subclass.
 */
class DetailPage : Fragment() {
    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private lateinit var wisataData: Datum
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val coordinatorLayout = inflater.inflate(R.layout.fragment_detail_page, container, false) as CoordinatorLayout
        val contentLayout:LinearLayout = coordinatorLayout.findViewById(R.id.contentLayout)
        val sheetBehavior = BottomSheetBehavior.from(contentLayout)
        wisataData = arguments?.getParcelable(Params.DashboardToDetail)!!
        sheetBehavior.apply {
            isFitToContents = false
            isHideable = false
            state = BottomSheetBehavior.STATE_COLLAPSED
        }

        mapView = coordinatorLayout.findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.onResume()
        Log.d("ASA", wisataData.address)
        try {
            MapsInitializer.initialize(activity?.applicationContext)
        }catch (e:Exception) {
            e.printStackTrace()
        }

        mapView.getMapAsync { map ->
            googleMap = map
            googleMap.isMyLocationEnabled = true
            val posisi = LatLng(wisataData.latitude,wisataData.longitude)
            googleMap.addMarker(MarkerOptions().position(posisi).title("Title").snippet("MMM"))
            val cameraPosition = CameraPosition.Builder().target(posisi).zoom(15f).build()
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }

        return coordinatorLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title_detail.text = wisataData.caption
        rating_detail.rating = wisataData.rating.toFloat()
        rating_total.text = wisataData.rating.toString()
        like_detail.text = wisataData.like
        detail_description.text = wisataData.description
        image1_detail.load(wisataData.image1)
        image2_detail.load(wisataData.image2)
        image3_detail.load(wisataData.image3)
        image4_detail.load(wisataData.image4)
    }
    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

}
