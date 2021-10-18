package duarte.diego.financeapp.uikit.core

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAsyncAdapter<T, V: BaseViewHolder<T>, B>: ListAdapter<T, V>(DiffCallback<T>()) {

    abstract fun createViewHolderInstance(view: AdapterAsyncLayout<B>, viewType: Int, ): V
    abstract val bindingInflater: (View) -> B
    abstract val layoutId: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): V {
        val itemView = AdapterAsyncLayout(parent.context, bindingInflater)
        itemView.inflateAsync(layoutId)

        return createViewHolderInstance(itemView, viewType)
    }

    override fun onBindViewHolder(holder: V, position: Int) {
        (holder.itemView as AsyncLayout).invokeWhenInflated {

            val animation: Animation = AnimationUtils.loadAnimation(
                holder.itemView.context, android.R.anim.fade_in)
            holder.itemView.startAnimation(animation)

            holder.bind(getItem(position))
        }
    }

    inner class AdapterAsyncLayout<B>(context: Context, private val bindingInflater: (View) -> B): AsyncLayout(context) {
        var binding: B? = null

        override fun createDataBindingView(view: View): View {
            binding = bindingInflater.invoke(view)
            return view.rootView
        }
    }

    class DiffCallback<T> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem


        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem

    }

}

abstract class BaseViewHolder<T>(v: View) : RecyclerView.ViewHolder(v) {

    abstract fun bind(item: T)

}
