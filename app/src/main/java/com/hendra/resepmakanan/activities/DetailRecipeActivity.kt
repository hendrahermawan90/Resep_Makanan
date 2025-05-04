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
import com.hendra.resepmakanan.model.ModelDetailRecipe
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

        progressDialog = ProgressDialog(this)
        progressDialog!!.setTitle("Mohon Tunggu")
        progressDialog!!.setCancelable(false)
        progressDialog!!.setMessage("Sedang menampilkan data...")

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
            detailRecipe
        }
    }

    private val detailRecipe: Unit
        private get() {
            progressDialog!!.show()
            AndroidNetworking.get(Api.DetailRecipe)
                .addPathParameter("idMeal", idMeal)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        try {
                            progressDialog!!.dismiss()
                            val playerArray = response.getJSONArray("meals")
                            for (i in 0 until playerArray.length()) {
                                val temp = playerArray.getJSONObject(i)
                                val dataApi = ModelDetailRecipe()
                                val Instructions = temp.getString("strInstructions")
                                binding.tvInstructions.text = Instructions

                                val Category = temp.getString("strCategory")
                                val Area = temp.getString("strArea")
                                binding.tvSubTitle.text = "$Category | $Area"

                                val Source = temp.getString("strSource")
                                binding.tvSource.setOnClickListener {
                                    val intentYoutube = Intent(Intent.ACTION_VIEW)
                                    intentYoutube.data = Uri.parse(Source)
                                    startActivity(intentYoutube)
                                }

                                val Youtube = temp.getString("strYoutube")
                                binding.tvYoutube.setOnClickListener {
                                    val intentYoutube = Intent(Intent.ACTION_VIEW)
                                    intentYoutube.data = Uri.parse(Youtube)
                                    startActivity(intentYoutube)
                                }

                                // Get Ingredients
                                dataApi.strIngredient1 = temp.optString("strIngredient1", "")
                                dataApi.strIngredient2 = temp.optString("strIngredient2", "")
                                // ... (Repeat for all ingredients)

                                // Append ingredients to tvIngredients
                                appendIngredientsToView(dataApi)

                                // Set Measures
                                appendMeasuresToView(dataApi)
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                            Toast.makeText(this@DetailRecipeActivity,
                                "Gagal menampilkan data!", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onError(anError: ANError) {
                        progressDialog!!.dismiss()
                        Toast.makeText(this@DetailRecipeActivity,
                            "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show()
                    }
                })
        }

    private fun appendIngredientsToView(dataApi: ModelDetailRecipe) {
        val ingredients = listOf(
            dataApi.strIngredient1, dataApi.strIngredient2, dataApi.strIngredient3,
            dataApi.strIngredient4, dataApi.strIngredient5, dataApi.strIngredient6,
            dataApi.strIngredient7, dataApi.strIngredient8, dataApi.strIngredient9,
            dataApi.strIngredient10, dataApi.strIngredient11, dataApi.strIngredient12,
            dataApi.strIngredient13, dataApi.strIngredient14, dataApi.strIngredient15,
            dataApi.strIngredient16, dataApi.strIngredient17, dataApi.strIngredient18,
            dataApi.strIngredient19, dataApi.strIngredient20
        )
        ingredients.filter { !it.isNullOrEmpty() }.forEach { ingredient ->
            binding.tvIngredients.append("\n \u2022 $ingredient")
        }
    }

    private fun appendMeasuresToView(dataApi: ModelDetailRecipe) {
        val measures = listOf(
            dataApi.strMeasure1, dataApi.strMeasure2, dataApi.strMeasure3,
            dataApi.strMeasure4, dataApi.strMeasure5, dataApi.strMeasure6,
            dataApi.strMeasure7, dataApi.strMeasure8, dataApi.strMeasure9,
            dataApi.strMeasure10, dataApi.strMeasure11, dataApi.strMeasure12,
            dataApi.strMeasure13, dataApi.strMeasure14, dataApi.strMeasure15,
            dataApi.strMeasure16, dataApi.strMeasure17, dataApi.strMeasure18,
            dataApi.strMeasure19, dataApi.strMeasure20
        )
        measures.filter { !it.isNullOrEmpty() }.forEach { measure ->
            binding.tvMeasure.append("\n : $measure")
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
        // Set Transparent Status bar
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