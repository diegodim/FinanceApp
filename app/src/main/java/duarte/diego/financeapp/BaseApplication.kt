package duarte.diego.financeapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

@Suppress("unused")
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}