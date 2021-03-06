package com.example.testapplication.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.testapplication.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    private var _binding: VB? = null
    val binding get() = _binding!!

    private var toast: Toast? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun getViewBinding(): VB

    internal fun showToast(message: String) {
        if (toast != null) {
            toast?.cancel()
        }
        toast = Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_SHORT
        )
        toast?.show()
    }

    internal fun notify(message: String) =
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
            .setAnchorView(activity?.findViewById(R.id.bottom_nav))
            .show()

    internal fun notifyWithAction(
        @StringRes message: Int,
        @StringRes actionText: Int,
        action: () -> Any
    ) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG)
            .setAnchorView(activity?.findViewById(R.id.bottom_nav))
            .setAction(actionText) { _ -> action.invoke() }
            .show()
    }

    internal fun Fragment.setToolbarBackButton(toolbar: Toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_back)
        setHasOptionsMenu(true)

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}