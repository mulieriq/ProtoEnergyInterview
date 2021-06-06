package com.skylabstechke.protoenergyinterview.bindingadapters

import android.widget.TextView
import androidx.databinding.BindingAdapter

class BindingAdapter {
    companion object{
        @BindingAdapter("textVariant")
        @JvmStatic
        fun textVariant(text:TextView,textData:String){
            text.text = textData.toString()
        }
    }
}