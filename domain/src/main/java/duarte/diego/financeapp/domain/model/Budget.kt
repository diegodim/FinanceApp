package duarte.diego.financeapp.domain.model

import duarte.diego.financeapp.commons.enums.BudgetCategory
import duarte.diego.financeapp.commons.enums.BudgetType

data class Budget(
    var id: Int = 0,
    var title: String = "",
    var value: Number = 0,
    var type: BudgetType = BudgetType.Income,
    var category: BudgetCategory = BudgetCategory.Others
)
