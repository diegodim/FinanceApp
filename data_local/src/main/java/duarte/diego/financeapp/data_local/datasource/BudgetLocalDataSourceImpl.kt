package duarte.diego.financeapp.data_local.datasource

import duarte.diego.financeapp.data.datasource.local.BudgetLocalDataSource
import duarte.diego.financeapp.data_local.dao.BudgetDao
import duarte.diego.financeapp.data_local.mapper.BudgetEntityMapper
import duarte.diego.financeapp.domain.model.Budget
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class BudgetLocalDataSourceImpl(private val dao: BudgetDao): BudgetLocalDataSource {

    override fun getBudgetList() = dao.getBudgetList().map {
        BudgetEntityMapper.fromLocal(it)
    }

    override fun insertBudget(budget: Budget) = flow{
        emit(dao.insertBudget(BudgetEntityMapper.toLocal(budget)))
    }

}