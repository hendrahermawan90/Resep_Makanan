package com.hendra.resepmakanan.activities

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.hendra.resepmakanan.adapter.MainAdapter
import com.hendra.resepmakanan.databinding.ActivityMainBinding
import com.hendra.resepmakanan.model.ModelMain
import com.hendra.resepmakanan.networking.Api
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity(), MainAdapter.onSelectData {

    private lateinit var binding: ActivityMainBinding
    private var mainAdapter: MainAdapter? = null
    private var progressDialog: ProgressDialog? = null
    private val modelMain: MutableList<ModelMain> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Transparan Status Bar
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

        // Progress Dialog
        progressDialog = ProgressDialog(this).apply {
            setTitle("Mohon Tunggu")
            setCancelable(false)
            setMessage("Sedang menampilkan data...")
        }

        // RecyclerView Setup
        binding.rvMainMenu.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            setHasFixedSize(true)
        }

        // Fetch Data
        getCategories()
    }

    private fun getCategories() {
        progressDialog?.show()
        AndroidNetworking.get(Api.Categories)
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    try {
                        progressDialog?.dismiss()
                        val playerArray = response.getJSONArray("categories")
                        for (i in 0 until playerArray.length()) {
                            val temp = playerArray.getJSONObject(i)
                            val dataApi = ModelMain().apply {
                                strCategory = temp.getString("strCategory")
                                strCategoryThumb = temp.getString("strCategoryThumb")
                                strCategoryDescription = temp.getString("strCategoryDescription")
                            }
                            modelMain.add(dataApi)
                        }
                        showCategories()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        Toast.makeText(this@MainActivity, "Gagal menampilkan data!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(anError: ANError) {
                    progressDialog?.dismiss()
                    Toast.makeText(this@MainActivity, "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun showCategories() {
        mainAdapter = MainAdapter(this, modelMain, this)
        binding.rvMainMenu.adapter = mainAdapter
    }

    override fun onSelected(modelMain: ModelMain) {
        val intent = Intent(this, FilterFoodActivity::class.java)
        intent.putExtra("showFilter", modelMain)
        startActivity(intent)
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
