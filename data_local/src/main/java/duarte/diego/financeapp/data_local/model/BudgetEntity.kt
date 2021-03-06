package duarte.diego.financeapp.data_local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import duarte.diego.financeapp.commons.enums.BudgetCategory
import duarte.diego.financeapp.commons.enums.BudgetType
import java.util.*

@Entity(tableName = "budget")
data class BudgetEntity(
    @PrimaryKey
    var id: Int = 0,
    var title: String = "",
    var value: Double = 0.0,
    var type: BudgetType = BudgetType.Income,
    var category: BudgetCategory = BudgetCategory.Others,
    var date: Date = Date()
)
