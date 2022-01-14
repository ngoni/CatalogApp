package com.scribblex.catalogapp.data.entities

import android.os.Parcelable

sealed class BaseModel(open val viewType: Int = 0) : Parcelable
