package com.hendra.resepmakanan.activities

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.hendra.resepmakanan.model.ModelFilter
import com.hendra.resepmakanan.networking.Api
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hendra.resepmakanan.databinding.ActivityDetailRecipeBinding
import org.json.JSONException
import org.json.JSONObject

class DetailRecipeActivity : AppCompatActivity() {

    private var idMeal: String? = null
    private var strMeal: String? = null
    private var modelFilter: ModelFilter? = null
    private var progressDialog: ProgressDialog? = null
    private lateinit var binding: ActivityDetailRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding
        binding = ActivityDetailRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Status Bar and UI styling
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }

        setSupportActionBar(binding.toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        progressDialog = ProgressDialog(this).apply {
            setTitle("Mohon Tunggu")
            setCancelable(false)
            setMessage("Sedang menampilkan data...")
        }

        // Get intent FilterFoodActivity
        modelFilter = intent.getSerializableExtra("detailRecipe") as ModelFilter
        if (modelFilter != null) {
            idMeal = modelFilter!!.idMeal
            strMeal = modelFilter!!.strMeal

            // Set text
            binding.tvTitle.text = strMeal
            binding.tvTitle.isSelected = true

            // Load image using Glide
            Glide.with(this)
                .load(modelFilter!!.strMealThumb)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imgThumb)

            // Fetch details for the recipe
            fetchDetailRecipe()
        }
    }

    private fun fetchDetailRecipe() {
        progressDialog?.show()
        AndroidNetworking.get(Api.DetailRecipe)
            .addPathParameter("idMeal", idMeal)
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    try {
                        progressDialog?.dismiss()
                        val playerArray = response.getJSONArray("meals")
                        for (i in 0 until playerArray.length()) {
                            val temp = playerArray.getJSONObject(i)

                            val instructions = temp.optString("strInstructions", "").trim()
                            binding.tvInstructions.text = "$instructions\n\n"

                            val category = temp.optString("strCategory", "-")
                            val area = temp.optString("strArea", "-")
                            binding.tvSubTitle.text = "$category | $area"

                            val source = temp.optString("strSource", "")
                            if (source.isNotEmpty()) {
                                binding.tvSource.setOnClickListener {
                                    val intentSource = Intent(Intent.ACTION_VIEW, Uri.parse(source))
                                    startActivity(intentSource)
                                }
                            }

                            val youtube = temp.optString("strYoutube", "")
                            if (youtube.isNotEmpty()) {
                                binding.tvYoutube.setOnClickListener {
                                    val intentYoutube = Intent(Intent.ACTION_VIEW, Uri.parse(youtube))
                                    startActivity(intentYoutube)
                                }
                            }

                            // Bersihkan teks sebelumnya agar tidak numpuk
                            binding.tvIngredients.text = ""
                            binding.tvMeasure.text = ""

                            // Tampilkan Ingredients & Measure 1–20
                            for (index in 1..20) {
                                val ingredient = temp.optString("strIngredient$index", "").trim()
                                val measure = temp.optString("strMeasure$index", "").trim()
                                if (ingredient.isNotEmpty() && ingredient != "null") {
                                    binding.tvIngredients.append("\n\u2022 $ingredient")
                                    binding.tvMeasure.append("\n: $measure")
                                }
                            }
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        Toast.makeText(this@DetailRecipeActivity, "Gagal menampilkan data!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(anError: ANError) {
                    progressDialog?.dismiss()
                    Toast.makeText(this@DetailRecipeActivity, "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show()
                }
            })
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
            if (on) {
                winParams.flags = winParams.flags or bits
            } else {
                winParams.flags = winParams.flags and bits.inv()
            }
            win.attributes = winParams
        }
    }
}
