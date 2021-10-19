package duarte.diego.financeapp.di

import duarte.diego.financeapp.data.datasource.local.BudgetLocalDataSource
import duarte.diego.financeapp.data_local.core.DatabaseFactory
import duarte.diego.financeapp.data_local.datasource.BudgetLocalDataSourceImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataLocalModule = module {

    single { DatabaseFactory.createRoomDatabase(androidApplication()) }

    single { DatabaseFactory.provideBudgetDao(get()) }

    single<BudgetLocalDataSource> { BudgetLocalDataSourceImpl(get()) }

}