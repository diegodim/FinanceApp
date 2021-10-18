package duarte.diego.financeapp.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import duarte.diego.financeapp.main.databinding.FragmentMainBinding
import duarte.diego.financeapp.main.navigation.MainNavigation
import duarte.diego.financeapp.uikit.core.BaseFragment
import duarte.diego.financeapp.uikit.utils.delegateproperties.navDirections
import duarte.diego.financeapp.uikit.utils.delegateproperties.viewInflateBinding


class MainFragment : BaseFragment() {

    private val binding by viewInflateBinding(FragmentMainBinding::inflate)
    //private val navigation: MainNavigation by navDirections()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root


}