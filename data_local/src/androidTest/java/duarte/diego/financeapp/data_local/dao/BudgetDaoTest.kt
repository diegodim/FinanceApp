package duarte.diego.financeapp.data_local.dao

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import duarte.diego.financeapp.data_local.core.FinanceDatabase
import duarte.diego.financeapp.data_local.model.BudgetEntity
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class BudgetDaoTest {

    private lateinit var budgetDao: BudgetDao
    private lateinit var db: FinanceDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, FinanceDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        budgetDao = db.budgetDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun saveBudgetWhenSelectListMustReturnAllTheItemsInserted() = runBlocking {
        //given
        val budget = BudgetEntity(value = 2000.00)
        budgetDao.insertBudget(budget)

        //when
        val budgetList = budgetDao.getBudgetList().take(1).toList()[0]

        //then
        budgetList.let{
            assertEquals(it.size, 1)
            assertEquals(it[0].value, 2000.00)
        }



    }

}