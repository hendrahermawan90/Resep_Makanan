package com.hendra.resepmakanan.networking

object  Api {

    // URL untuk kategori resep
    const val Categories = "https://www.themealdb.com/api/json/v1/1/categories.php"

    // URL untuk filter berdasarkan kategori
    const val Filter = "https://www.themealdb.com/api/json/v1/1/filter.php?c={strCategory}"

    // URL untuk detail resep berdasarkan ID
    const val DetailRecipe = "https://www.themealdb.com/api/json/v1/1/lookup.php?i={idMeal}"

    // Fungsi URL untuk pencarian resep berdasarkan nama makanan
    fun SearchRecipe(query: String): String {
        return "https://www.themealdb.com/api/json/v1/1/search.php?s=$query"
    }
}
