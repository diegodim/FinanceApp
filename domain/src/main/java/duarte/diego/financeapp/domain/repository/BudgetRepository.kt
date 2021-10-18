package duarte.diego.financeapp.domain.repository

import duarte.diego.financeapp.domain.model.Budget
import kotlinx.coroutines.flow.Flow

interface BudgetRepository {
    fun getBudgetList(): Flow<List<Budget>>
    fun insertBudget(budget: Budget): Flow<Unit>
}