package duarte.diego.financeapp.uikit.utils.extensions

import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

fun Fragment.showActionBar() = (activity as? AppCompatActivity)?.supportActionBar?.show()

fun Fragment.addOnBackPressedCallback(owner: LifecycleOwner, onBackPressed: () -> Unit) {
    (requireActivity() as? AppCompatActivity)?.onBackPressedDispatcher?.addCallback(
        owner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        }
    )
}

fun Fragment.setNavigationIcon(id: Int?) {
    (activity as AppCompatActivity?)?.supportActionBar?.apply {
        id?.let { setHomeAsUpIndicator(it) }
        setDisplayHomeAsUpEnabled(id != null)
    }
    showActionBar()
}

fun Fragment.setToolbarTitle(title: String) {
    (requireActivity() as? AppCompatActivity)?.supportActionBar?.title = title
}