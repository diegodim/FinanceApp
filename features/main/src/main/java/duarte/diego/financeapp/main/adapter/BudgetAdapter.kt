package duarte.diego.financeapp.main.adapter

import android.view.View
import duarte.diego.financeapp.domain.model.Budget
import duarte.diego.financeapp.main.R
import duarte.diego.financeapp.main.databinding.ItemBudgetBinding
import duarte.diego.financeapp.uikit.core.BaseAdapter
import duarte.diego.financeapp.uikit.core.BaseViewHolder

class BudgetAdapter: BaseAdapter<Budget, BudgetAdapter.BudgetViewHolder, ItemBudgetBinding>(){
    override fun createViewHolderInstance(
        view: AdapterAsyncLayout<ItemBudgetBinding>,
        viewType: Int
    ): BudgetViewHolder {
        TODO("Not yet implemented")
    }

    override val bindingInflater: (View) -> ItemBudgetBinding = { ItemBudgetBinding.bind(it) }

    override val layoutId: Int = R.layout.item_budget

    inner class BudgetViewHolder(private val view: AdapterAsyncLayout<ItemBudgetBinding>):
        BaseViewHolder<Budget>(view) {

        override fun bind(item: Budget) {

            view.binding?.apply {

            }
        }
    }
}