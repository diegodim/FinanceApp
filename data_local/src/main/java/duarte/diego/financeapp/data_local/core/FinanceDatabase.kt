package duarte.diego.financeapp.data_local.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import duarte.diego.financeapp.data_local.dao.BudgetDao
import duarte.diego.financeapp.data_local.model.BudgetEntity

@Database(
    entities = [BudgetEntity::class], version = 1, exportSchema = false
)
@TypeConverters(DataConverter::class)
abstract class FinanceDatabase: RoomDatabase() {

    abstract fun budgetDao(): BudgetDao

}
