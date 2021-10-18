package duarte.diego.financeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import duarte.diego.financeapp.intent.safeNavigateUp

class AppActivity : AppCompatActivity() {

    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.appNavHostFragment) as NavHostFragment
        mNavController = navHostFragment.navController

    }

    override fun onSupportNavigateUp() = mNavController.safeNavigateUp()
}