package com.skylabstechke.protoenergyinterview.bindingadapters

import android.widget.TextView
import androidx.databinding.BindingAdapter

class RowLayoutBindingAdapter {
    companion object {
        @BindingAdapter("textVariant")
        @JvmStatic
        fun textVariant(text: TextView, textData: String) {
            text.text = textData.toString()
        }
    }
}