# 🍽️ Aplikasi Resep Makanan Android - TheMealDB API

Selamat datang di **Aplikasi Resep Makanan Android**, sebuah aplikasi mobile yang memungkinkan pengguna mencari dan melihat berbagai resep makanan dari seluruh dunia langsung dari genggaman tangan. Aplikasi ini terintegrasi dengan [TheMealDB API](https://www.themealdb.com/api.php), yang menyediakan koleksi resep lengkap, termasuk bahan, langkah memasak, dan video tutorial.

## 📱 Fitur Utama

* 📚 Jelajahi kategori makanan (dessert, seafood, dll)
* 🍽️ Pilih masakan yang mau Anda buat
* 🔍 Pencarian resep berdasarkan nama makanan
* 📋 Lihat detail bahan dan langkah memasak
* 🎥 Tonton video tutorial dari YouTube langsung di aplikasi
* 🌐 Jelajahi website lengkap untuk resep-resep menarik lainnya

---

## 🎯 Tujuan Proyek

Proyek ini dibuat sebagai latihan pengembangan aplikasi Android dengan memanfaatkan REST API eksternal. Fokus utamanya adalah bagaimana mengelola data dari internet secara efisien dan menampilkannya dalam UI yang responsif dan menarik.

---

## 🧭 Storyboard Aplikasi

### 1. **MainActivity (Halaman Utama)**

📄 File Layout: `activity_main.xml`
🎯 Fungsi:

* Menampilkan daftar kategori resep makanan.
* Klik kategori akan membuka halaman filter.
* **Menyediakan fitur pencarian resep berdasarkan nama masakan.**

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
* ✅ Pencarian resep berdasarkan nama masakan

---

## 📲 Screenshot (Opsional)

![ss](https://github.com/user-attachments/assets/419eca45-1a64-4721-b98b-e82cde09ca34)

---

## ✨ Credits

* Sumber Infromasi : Azhar Rivaldi [https://www.youtube.com/watch?v=PX15pj-aHPU\&t=5s](https://www.youtube.com/watch?v=PX15pj-aHPU&t=5s)

* API :  [TheMealDB](https://www.themealdb.com/)

Dibuat oleh: **Hendra,** **Alik,** **Suparman,** **Rofi**

---
