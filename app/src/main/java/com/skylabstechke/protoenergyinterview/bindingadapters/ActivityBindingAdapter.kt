package com.skylabstechke.protoenergyinterview.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.skylabstechke.protoenergyinterview.models.OrdersModel
import com.skylabstechke.protoenergyinterview.utils.NetworkResult

class ActivityBindingAdapter {

    companion object {

        @BindingAdapter("errImageViewVisibility", "readApiResponse")
        @JvmStatic
        fun errImageViewVisibility(imageView: ImageView, apiResponse: NetworkResult<OrdersModel>) {
            when (apiResponse) {
                is NetworkResult.Error -> {
                    imageView.visibility = View.VISIBLE
                }
                is NetworkResult.Loading -> {
                    imageView.visibility = View.INVISIBLE
                }
                is NetworkResult.Success -> {
                    imageView.visibility = View.INVISIBLE
                }
            }
        }

        @BindingAdapter("errTextViewVisibility", "readApiResponse")
        @JvmStatic
        fun errTextViewVisibility(textView: TextView, apiResponse: NetworkResult<OrdersModel>) {
            when (apiResponse) {
                is NetworkResult.Error -> {
                    textView.visibility = View.VISIBLE
                }
                is NetworkResult.Loading -> {
                    textView.visibility = View.INVISIBLE
                }
                is NetworkResult.Success -> {
                    textView.visibility = View.INVISIBLE
                }
            }
        }
    }
}