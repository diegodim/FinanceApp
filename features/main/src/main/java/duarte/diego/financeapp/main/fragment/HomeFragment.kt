package duarte.diego.financeapp.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import duarte.diego.financeapp.domain.model.Budget
import duarte.diego.financeapp.main.adapter.BudgetAdapter
import duarte.diego.financeapp.main.databinding.FragmentHomeBinding
import duarte.diego.financeapp.uikit.core.BaseFragment
import duarte.diego.financeapp.uikit.utils.delegateproperties.viewInflateBinding


class HomeFragment : BaseFragment() {

    private val binding by viewInflateBinding(FragmentHomeBinding::inflate)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun setupView() {
        super.setupView()
        val budgetAdapter = BudgetAdapter()
        binding.apply {
            homeRecyclerView.apply {
                isNestedScrollingEnabled = false
                adapter = budgetAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
        budgetAdapter.addHeaderAndSubmitList(Budget.FAKE)
    }
}