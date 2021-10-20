package duarte.diego.financeapp.uikit.core


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.facebook.shimmer.ShimmerFrameLayout
import duarte.diego.financeapp.uikit.R
import duarte.diego.financeapp.uikit.utils.extensions.gone
import duarte.diego.financeapp.uikit.utils.extensions.visible
import org.koin.core.component.KoinComponent


abstract class BaseFragment : Fragment(), ViewStateListener, KoinComponent {


    private var toolbar: Toolbar? = null

    protected open var shimmerLayout: ShimmerFrameLayout? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvents(viewLifecycleOwner)
        setupToolbar()
        setupView()
    }

    override fun onStateError(error: Throwable) {
        hideLoading()
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_LONG).show()
    }

    override fun onStateLoading(){
        shimmerLayout?.let{
            it.startShimmer()
            it.visible()
        }


    }
    override fun hideLoading() {
        shimmerLayout?.let{
            it.gone()
            it.stopShimmer()
        }
    }

    private fun setupToolbar() {

        toolbar = requireView().findViewById<Toolbar>(R.id.appToolbar)?.also {
            (requireActivity() as? AppCompatActivity)?.apply {
                setSupportActionBar(it)
                setupActionBarWithNavController(requireView().findNavController())
                //setNavigationIcon(R.drawable.ic_arrow_back_primary_24dp)
            }
        }
    }




    protected open fun observeEvents(owner: LifecycleOwner) = Unit

    protected open fun setupView() = Unit

    override fun onDestroyView() {
        super.onDestroyView()
        toolbar = null

    }

}