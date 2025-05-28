package com.hendra.resepmakanan.model

import java.io.Serializable

class ModelMain : Serializable {

    // Data dari endpoint: categories.php
    @JvmField
    var strCategory: String? = null

    @JvmField
    var strCategoryThumb: String? = null

    var strCategoryDescription: String? = null

    // Data dari endpoint: search.php?s=...
    var idMeal: String? = null
    var strMeal: String? = null
    var strMealThumb: String? = null
}
