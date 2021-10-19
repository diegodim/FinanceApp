package duarte.diego.financeapp.di

import duarte.diego.financeapp.domain.core.ThreadContextProvider
import org.koin.dsl.module

val domainModule = module {

    single { ThreadContextProvider() }

}