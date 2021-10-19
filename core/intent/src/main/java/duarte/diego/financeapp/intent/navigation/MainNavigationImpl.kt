package duarte.diego.financeapp.intent.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import duarte.diego.financeapp.intent.R
import duarte.diego.financeapp.main.navigation.MainNavigation

class MainNavigationImpl (private val fragment: Fragment): MainNavigation {

    override fun setupBottomNavigation(bottomNavigationView: BottomNavigationView, navController: NavController){
        navController.setGraph(R.navigation.main_navigation)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

}