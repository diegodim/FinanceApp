package duarte.diego.financeapp.data_local.mapper



import duarte.diego.financeapp.data_local.core.BaseLocalMapper
import duarte.diego.financeapp.data_local.model.BudgetEntity
import duarte.diego.financeapp.domain.model.Budget

object BudgetEntityMapper: BaseLocalMapper<BudgetEntity, Budget> {

    override fun toLocal(domain: Budget) = BudgetEntity (
        id = domain.id,
        title = domain.title,
        value = domain.value,
        type = domain.type,
        category = domain.category
    )

    override fun fromLocal(local: BudgetEntity) = Budget (
        id = local.id,
        title = local.title,
        value = local.value,
        type = local.type,
        category = local.category
    )

}