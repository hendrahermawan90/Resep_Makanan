package com.hendra.resepmakanan.networking

object Api {

    // URL untuk kategori resep
    var Categories = "https://www.themealdb.com/api/json/v1/1/categories.php"

    // URL untuk filter berdasarkan kategori
    var Filter = "https://www.themealdb.com/api/json/v1/1/filter.php?c={strCategory}"

    // URL untuk detail resep berdasarkan ID
    var DetailRecipe = "https://www.themealdb.com/api/json/v1/1/lookup.php?i={idMeal}"

    // URL untuk pencarian resep berdasarkan nama makanan
    var SearchRecipe = "https://www.themealdb.com/api/json/v1/1/search.php?s={query}"
}
