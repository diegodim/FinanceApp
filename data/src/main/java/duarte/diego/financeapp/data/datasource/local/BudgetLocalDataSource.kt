package duarte.diego.financeapp.data.datasource.local

import duarte.diego.financeapp.domain.model.Budget
import kotlinx.coroutines.flow.Flow

interface BudgetLocalDataSource {
    fun insertBudget(budget: Budget): Flow<Unit>
    fun getBudgetList(): Flow<List<Budget>>
}