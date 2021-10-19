package duarte.diego.financeapp.di

import duarte.diego.financeapp.data.repository.BudgetRepositoryImpl
import duarte.diego.financeapp.domain.repository.BudgetRepository
import org.koin.dsl.module

val dataModule = module {

    single<BudgetRepository> { BudgetRepositoryImpl(get()) }

}