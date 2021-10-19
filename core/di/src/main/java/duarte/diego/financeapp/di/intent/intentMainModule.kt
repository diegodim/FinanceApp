package duarte.diego.financeapp.di.intent

import androidx.fragment.app.Fragment
import duarte.diego.financeapp.intent.navigation.MainNavigationImpl
import duarte.diego.financeapp.main.navigation.MainNavigation
import org.koin.dsl.module

val intentMainModule  = module{
    factory<MainNavigation>{ (fragment: Fragment) ->
        MainNavigationImpl(fragment)
    }
}