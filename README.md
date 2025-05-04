Siap, Hendra! Ini dia contoh **Storyboard README** buat GitHub project kamu yang isinya menggambarkan alur dan struktur aplikasi **Resep Makanan** yang sudah kamu bangun. Bahasa yang digunakan simple dan informatif, cocok buat dokumentasi pribadi atau kalau kamu mau share ke orang lain.

---

## 🍽️ Resep Makanan App

Aplikasi Android sederhana untuk mencari dan melihat resep makanan dari berbagai kategori. Data diambil dari API [TheMealDB](https://www.themealdb.com/).

---

## 🧭 Storyboard Aplikasi

### 1. **MainActivity (Halaman Utama)**

📄 File Layout: `activity_main.xml`
🎯 Fungsi:

* Menampilkan daftar kategori resep makanan.
* Klik kategori akan membuka halaman filter.

🔧 Komponen:

* Toolbar (`toolbar_main.xml`)
* RecyclerView (pakai `MainAdapter`)
* Item Layout: `list_item_categories.xml`

---

### 2. **FilterFoodActivity (Halaman Filter Resep)**

📄 File Layout: `activity_filter_food.xml`
🎯 Fungsi:

* Menampilkan resep berdasarkan kategori yang dipilih dari halaman utama.

🔧 Komponen:

* RecyclerView (pakai `FilterFoodAdapter`)
* Item Layout: `list_item_filter_food.xml`

---

### 3. **DetailRecipeActivity (Halaman Detail Resep)**

📄 File Layout: `activity_detail_recipe.xml`
🎯 Fungsi:

* Menampilkan detail resep: nama, gambar, bahan-bahan, dan cara memasak.

🔧 Komponen:

* ImageView, TextView
* ScrollView untuk konten panjang

---

## 📦 Struktur Folder

```
📁 adapter
  └── MainAdapter.kt
  └── FilterFoodAdapter.kt

📁 model
  └── ModelMain.kt
  └── ModelDetailRecipe.kt
  └── ModelFilter.kt

📁 network
  └── Api.kt

📁 activities
  └── MainActivity.kt
  └── FilterFoodActivity.kt
  └── DetailRecipeActivity.kt

📁 layout
  └── activity_main.xml
  └── activity_filter_food.xml
  └── activity_detail_recipe.xml
  └── list_item_categories.xml
  └── list_item_filter_food.xml
  └── toolbar_main.xml
```

---

## 🔌 Teknologi & Tools

* Kotlin
* Java
* Retrofit2
* ViewBinding
* RecyclerView
* Glide

---

## 🧪 Fitur yang Sudah Diuji

* ✅ Ambil data dari API (kategori & resep)
* ✅ Tampilkan daftar resep per kategori
* ✅ Navigasi antar halaman (Main → Filter → Detail)
* ✅ UI responsif dan rapi

---

## 📲 Screenshot (Opsional)

![UI](https://github.com/user-attachments/assets/d23a9ef8-c957-435c-a178-4542e33c8b2d)

---


## ✨ Credits
* Sumber Infromasi : Azhar Rivaldi https://www.youtube.com/watch?v=PX15pj-aHPU&t=5s

* API oleh [TheMealDB](https://www.themealdb.com/)


Dibuat oleh: **Hendra Hermawan**

---
