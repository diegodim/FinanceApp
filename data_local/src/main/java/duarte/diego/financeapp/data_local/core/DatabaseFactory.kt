package duarte.diego.financeapp.data_local.core

import android.app.Application
import androidx.room.Room
import duarte.diego.financeapp.domain.model.Budget

object DatabaseFactory {

    fun createRoomDatabase(application: Application) =
        Room.databaseBuilder(application,
            FinanceDatabase::class.java, "finance.db")
            .build()

    fun provideBudgetDao(database: FinanceDatabase) = database.budgetDao()

}