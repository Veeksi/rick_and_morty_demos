package com.example.jetpackapp.navigation

import android.graphics.drawable.Icon
import com.example.jetpackapp.R

sealed class BottomNavItem(var Route: String, var icon: Int, var title: String) {
    object Characters : BottomNavItem("characters", R.drawable.ic_home, "Characters")
    object Demos : BottomNavItem("demos", R.drawable.ic_episodes, "Demos")
}
