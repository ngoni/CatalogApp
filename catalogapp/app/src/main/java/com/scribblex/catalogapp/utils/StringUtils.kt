package com.cartrack.omdapi.utils

import androidx.annotation.StringRes
import com.scribblex.catalogapp.MainApplication

object StringUtils {
    fun getString(@StringRes resId: Int) : String {
        return MainApplication.appContext.getString(resId)
    }
}