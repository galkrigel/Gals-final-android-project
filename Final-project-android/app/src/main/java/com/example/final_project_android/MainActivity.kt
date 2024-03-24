package com.example.final_project_android

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project_android.Model.ApiPropertyItem
import com.example.final_project_android.Modules.Properties.LoginActivity
import com.example.final_project_android.R
import com.example.final_project_android.api.ApiInterface
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private var navController: NavController? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>
    private lateinit var mAuth: FirebaseAuth
    val BASE_URL = "https://re.ofir.dev/"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("TAG", "main has called!")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment: NavHostFragment? =
            supportFragmentManager.findFragmentById(R.id.navHostMain) as? NavHostFragment
        navController = navHostFragment?.navController
        // navController?.let { NavigationUI.setupActionBarWithNavController(this, it) }

        val bottomNavigationView: BottomNavigationView =
            findViewById(R.id.mainActivityBottomNavigationView)
        navController?.let { NavigationUI.setupWithNavController(bottomNavigationView, it) }

        mAuth = FirebaseAuth.getInstance()

        Log.i("TAG", "main has finished!")

    }

    override fun onStart() {
        Log.i("TAG", "main on start was called!")

        super.onStart()
        val user = mAuth.currentUser
        if (user == null) {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        super.onCreateOptionsMenu(menu)
//        menuInflater.inflate(R.menu.menu, menu)
//        return true
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                navController?.navigateUp()
                true
            }

            else -> navController?.let { NavigationUI.onNavDestinationSelected(item, it) }
                ?: super.onOptionsItemSelected(item)

        }
    }
}
