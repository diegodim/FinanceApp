package duarte.diego.financeapp.data_local.core

import androidx.room.Database
import androidx.room.RoomDatabase
import duarte.diego.financeapp.data_local.dao.BudgetDao
import duarte.diego.financeapp.data_local.model.BudgetEntity

@Database(
    entities = [BudgetEntity::class], version = 1, exportSchema = false
)
abstract class FinanceDatabase: RoomDatabase() {

    abstract fun budgetDao(): BudgetDao

}
