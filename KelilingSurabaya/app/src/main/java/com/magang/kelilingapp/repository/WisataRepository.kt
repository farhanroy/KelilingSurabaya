package com.magang.kelilingapp.repository

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.requests.tryCancel
import com.github.kittinunf.fuel.gson.responseObject
import com.magang.kelilingapp.model.Wisata

class WisataRepository {
    private var URL = "https://wisatasurabayaapi.herokuapp.com/api"
    private lateinit var requestGallery: Request

    fun getWisataList(onSuccess:(Wisata) -> Unit, onError:(FuelError) -> Unit) {
        requestGallery = Fuel.get(URL).responseObject<Wisata> { _, _, result ->
            result.fold({
                onSuccess(it)
            }, {
                onError(it)
            })
        }
    }
    fun cleared() {
        requestGallery.tryCancel(true)
    }
}