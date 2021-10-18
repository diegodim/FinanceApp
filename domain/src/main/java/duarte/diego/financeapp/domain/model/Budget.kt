package duarte.diego.financeapp.domain.model

import duarte.diego.financeapp.commons.enums.BudgetCategory
import duarte.diego.financeapp.commons.enums.BudgetType

data class Budget(
    var id: Int = 0,
    var title: String = "",
    var value: Number = 0,
    var type: BudgetType = BudgetType.Income,
    var category: BudgetCategory = BudgetCategory.Others
){
    companion object{
        val FAKE by lazy {
            ArrayList<Budget>().apply {
                add(Budget(1, "Pizza", 59.65, BudgetType.Expense, BudgetCategory.Food))
                add(Budget(2, "Luz", 200, BudgetType.Expense, BudgetCategory.Bills))
                add(Budget(3, "Feira", 650.55, BudgetType.Expense, BudgetCategory.Purchases))
                add(Budget(4, "Salário", 2000, BudgetType.Income, BudgetCategory.Salary))
            }
        }
    }
}
