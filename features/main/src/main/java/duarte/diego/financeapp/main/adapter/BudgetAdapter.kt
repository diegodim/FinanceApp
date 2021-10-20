package duarte.diego.financeapp.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.*
import duarte.diego.financeapp.domain.model.Budget
import duarte.diego.financeapp.main.R
import duarte.diego.financeapp.main.databinding.ItemBudgetBinding
import duarte.diego.financeapp.main.databinding.ItemBudgetHeaderBinding
import duarte.diego.financeapp.uikit.core.AsyncLayout
import duarte.diego.financeapp.uikit.core.BaseViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class BudgetAdapter : ListAdapter<Any, ViewHolder>(DiffCallback) {


    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderAndSubmitList(list: List<Budget>?) {
        adapterScope.launch {
            val items = when (list) {
                null -> listOf(String())
                else -> listOf(String()) + list
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when(viewType){
        ITEM_VIEW_TYPE_HEADER -> {
            val itemView = HeaderViewHolder.HeaderAsyncLayout(parent.context)
            itemView.inflateAsync(R.layout.item_budget_header)
            HeaderViewHolder(itemView)
        }
        ITEM_VIEW_TYPE_ITEM ->{
            val itemView = BudgetViewHolder.BudgetAsyncLayout(parent.context)
            itemView.inflateAsync(R.layout.item_budget)
            BudgetViewHolder(itemView)
        }
        else -> throw IllegalArgumentException("Invalid view type")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder.itemView as AsyncLayout).invokeWhenInflated {
            val animation: Animation = AnimationUtils.loadAnimation(
                holder.itemView.context, android.R.anim.fade_in)
            holder.itemView.startAnimation(animation)

            when(holder){
                is BudgetViewHolder -> holder.bind(getItem(position) as Budget)
                is HeaderViewHolder -> holder.bind(getItem(position) as String)
                else -> throw IllegalArgumentException()
            }
        }
    }


    class BudgetViewHolder(private val view: BudgetAsyncLayout) :
        BaseViewHolder<Budget>(view) {

        override fun bind(item: Budget) {

            view.binding.apply {

            }
        }
        class BudgetAsyncLayout(context: Context): AsyncLayout(context) {
            lateinit var binding: ItemBudgetBinding

            override fun createDataBindingView(view: View): View {
                binding = ItemBudgetBinding.bind(view)
                return view.rootView
            }
        }
    }

    class HeaderViewHolder(private val view: HeaderAsyncLayout) :
        BaseViewHolder<String>(view) {

        override fun bind(item: String) {

            view.binding.apply {

            }
        }
        class HeaderAsyncLayout(context: Context): AsyncLayout(context) {
            lateinit var binding: ItemBudgetHeaderBinding

            override fun createDataBindingView(view: View): View {
                binding = ItemBudgetHeaderBinding.bind(view)
                return view.rootView
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is String -> ITEM_VIEW_TYPE_HEADER
            is Budget -> ITEM_VIEW_TYPE_ITEM
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }

    companion object {
        private const val ITEM_VIEW_TYPE_HEADER = 0
        private const val ITEM_VIEW_TYPE_ITEM = 1
    }


    object DiffCallback : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = oldItem == newItem


        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = oldItem == newItem

    }


}