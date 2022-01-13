package com.scribblex.catalogapp

object Constants {
    const val BASE_URL: String = "http://mobcategories.s3-website-eu-west-1.amazonaws.com"

    // supported app view types for Category listing screen
    const val VIEW_TYPE_UNSUPPORTED: Int = 0
    const val VIEW_TYPE_HEADER: Int = 1
    const val VIEW_TYPE_LIST_ITEM: Int = 2

    // Hard-coded values from the API
    const val CATEGORY_FOOD_ID: Int = 36802
    const val CATEGORY_DRINK_ID: Int = 36803
}