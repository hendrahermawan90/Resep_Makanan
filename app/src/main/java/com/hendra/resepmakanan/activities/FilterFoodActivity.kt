package com.hendra.resepmakanan.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.hendra.resepmakanan.adapter.FilterFoodAdapter
import com.hendra.resepmakanan.databinding.ActivityFilterFoodBinding
import com.hendra.resepmakanan.model.ModelFilter
import com.hendra.resepmakanan.model.ModelMain
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import org.json.JSONArray
import org.json.JSONException

class FilterFoodActivity : AppCompatActivity(), FilterFoodAdapter.onSelectData {

    private lateinit var binding: ActivityFilterFoodBinding
    private var filterFoodAdapter: FilterFoodAdapter? = null
    private val modelFilter: MutableList<ModelFilter> = ArrayList()
    private var modelMain: ModelMain? = null
    private var strCategory: String? = null
    private var strCategoryDescription: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupStatusBar()
        setupToolbar()
        setupRecyclerView()

        // Ambil data dari intent
        modelMain = intent.getSerializableExtra("showFilter") as? ModelMain
        modelMain?.let {
            strCategory = it.strCategory
            strCategoryDescription = it.strCategoryDescription

            // Tampilkan info kategori
            binding.tvTitle.text = "Food List $strCategory"
            binding.tvDescCategories.text = strCategoryDescription

            Glide.with(this)
                .load(it.strCategoryThumb)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imgCategoriesBg)

            Glide.with(this)
                .load(it.strCategoryThumb)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imgCategories)

            getMealData() // Ambil data makanan berdasarkan kategori
        } ?: run {
            // Jika data kategori tidak ditemukan
            Toast.makeText(this, "Data kategori tidak ditemukan", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupToolbar() {
        binding.toolbarFilter.title = null
        setSupportActionBar(binding.toolbarFilter)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    )
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setupRecyclerView() {
        binding.rvFilter.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvFilter.setHasFixedSize(true)
    }

    private fun getMealData() {
        binding.progressBar.visibility = View.VISIBLE

        val url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=${strCategory}"

        val stringRequest = com.android.volley.toolbox.StringRequest(
            com.android.volley.Request.Method.GET, url,
            { response ->
                try {
                    val jsonObject = org.json.JSONObject(response)
                    val mealsArray = jsonObject.getJSONArray("meals")

                    for (i in 0 until mealsArray.length()) {
                        val item = mealsArray.getJSONObject(i)
                        val dataApi = ModelFilter().apply {
                            idMeal = item.getString("idMeal")
                            strMeal = item.getString("strMeal")
                            strMealThumb = item.getString("strMealThumb")
                        }
                        modelFilter.add(dataApi)
                    }

                    showFilter()
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Gagal parsing data!", Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                }
            },
            { error ->
                error.printStackTrace()
                Toast.makeText(this, "Gagal mengambil data dari server!", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
            })

        // Tambahkan request ke antrian
        com.android.volley.toolbox.Volley.newRequestQueue(this).add(stringRequest)
    }


    private fun showFilter() {
        // Pastikan data ada sebelum di-set ke adapter
        if (modelFilter.isNotEmpty()) {
            filterFoodAdapter = FilterFoodAdapter(this, modelFilter, this)
            binding.rvFilter.adapter = filterFoodAdapter
        } else {
            Toast.makeText(this, "Tidak ada makanan untuk kategori ini", Toast.LENGTH_SHORT).show()
        }
        binding.progressBar.visibility = View.GONE
    }

    override fun onSelected(model: ModelFilter) {
        // Pastikan model yang dikirim tidak null
        if (model != null) {
            val intent = Intent(this, DetailRecipeActivity::class.java)
            intent.putExtra("detailRecipe", model)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Data tidak tersedia", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val win = activity.window
            val winParams = win.attributes
            winParams.flags = if (on) winParams.flags or bits else winParams.flags and bits.inv()
            win.attributes = winParams
        }
    }
}
