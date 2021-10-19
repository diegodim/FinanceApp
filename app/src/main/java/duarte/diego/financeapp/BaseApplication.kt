package duarte.diego.financeapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import duarte.diego.financeapp.di.dataLocalModule
import duarte.diego.financeapp.di.dataModule
import duarte.diego.financeapp.di.domainModule
import duarte.diego.financeapp.di.intent.intentModule
import duarte.diego.financeapp.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        startKoin {
            modules(
                intentModule +
                listOf(
                    dataModule,
                    dataLocalModule,
                    domainModule,
                    presentationModule
                )
            ).androidContext(applicationContext)
        }
    }
}