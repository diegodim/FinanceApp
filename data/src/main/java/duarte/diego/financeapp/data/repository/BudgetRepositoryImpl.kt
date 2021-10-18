package duarte.diego.financeapp.data.repository

import duarte.diego.financeapp.data.datasource.local.BudgetDataLocalSource
import duarte.diego.financeapp.domain.model.Budget
import duarte.diego.financeapp.domain.repository.BudgetRepository

class BudgetRepositoryImpl(private val budgetDataLocalSource: BudgetDataLocalSource):
    BudgetRepository {

    override fun getBudgetList() = budgetDataLocalSource.getBudgetList()
    override fun insertBudget(budget: Budget) = budgetDataLocalSource.insertBudget(budget)
}