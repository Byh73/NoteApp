package com.example.noteapp.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.noteapp.R
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.example.noteapp.utils.PreferenceHelper

class MainActivity : AppCompatActivity() {

    private val preference = PreferenceHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preference.unit(this)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        val navController = navHostFragment?.navController ?: return

        if (!preference.isOnBoardShown) {
            val navOptions = NavOptions.Builder().setPopUpTo(R.id.boardFragment, true).build()
            navController.navigate(R.id.noteFragment, null, navOptions)
        } else {
            preference.isOnBoardShown = false
        }'

         */

        preference.unit(this)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        val navController = navHostFragment?.navController ?: return

        Log.d("ololo", "is first visit = " + preference.isOnBoardShown)
        Log.d("ololo", "is auth = " + preference.isAuth)
        if (!preference.isOnBoardShown && preference.isAuth) {
            val navOptions = NavOptions.Builder().setPopUpTo(R.id.boardFragment, true).build()
            navController.navigate(R.id.googleAuthFragment, null, navOptions)
        } else if (!preference.isAuth) {
            Log.d("ololo", "is auth = " + preference.isAuth)
            val navOptions = NavOptions.Builder().setPopUpTo(R.id.googleAuthFragment, true).build()
            navController.navigate(R.id.noteFragment, null, navOptions)
        }
    }
}