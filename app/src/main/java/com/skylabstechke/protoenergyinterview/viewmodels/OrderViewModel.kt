package com.skylabstechke.protoenergyinterview.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.skylabstechke.protoenergyinterview.data.network.OrdersApi
import com.skylabstechke.protoenergyinterview.utils.NetworkResult

class OrderViewModel @ViewModelInject constructor(application: Application) :
    AndroidViewModel(application) {
    var orderResponse: MutableLiveData<NetworkResult<OrdersApi>> = MutableLiveData()
}