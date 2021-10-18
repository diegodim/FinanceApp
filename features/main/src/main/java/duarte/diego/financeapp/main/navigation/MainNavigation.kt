package duarte.diego.financeapp.main.navigation

import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView

interface MainNavigation {


    fun setupBottomNavigation(
        bottomNavigationView: BottomNavigationView,
        navController: NavController
    )
}