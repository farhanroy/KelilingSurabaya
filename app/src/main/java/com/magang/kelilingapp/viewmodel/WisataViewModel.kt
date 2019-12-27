package com.magang.kelilingapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.magang.kelilingapp.model.Wisata
import com.magang.kelilingapp.repository.WisataRepository

class WisataViewModel(application: Application) : AndroidViewModel(application){
    private val repository: WisataRepository = WisataRepository()
    var wisataMutable = MutableLiveData<Wisata>()

    fun requestListWisata(){
        repository.getWisataList({
           wisataMutable.postValue(it)
        },{})
    }

    override fun onCleared() {
        super.onCleared()
        repository.cleared()
    }
}