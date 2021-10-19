package duarte.diego.financeapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import duarte.diego.financeapp.di.dataLocalModule
import duarte.diego.financeapp.di.dataModule
import org.koin.core.context.startKoin

@Suppress("unused")
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            modules(

                listOf(
                    dataModule,
                    dataLocalModule
                )
            )
        }
    }
}