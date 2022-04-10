package com.example.moneyexchanger

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExchangeViewModel : ViewModel() {

    private val data : MutableLiveData<Double> = MutableLiveData()

    init {
        data.value =  0.0
    }

    fun getData() : LiveData<Double> = data

    fun setData(value : Double){
        data.value = value
    }
}