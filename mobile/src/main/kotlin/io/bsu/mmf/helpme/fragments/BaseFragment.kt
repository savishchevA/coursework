package io.bsu.mmf.helpme.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import dagger.android.*
import dagger.android.support.AndroidSupportInjection
import io.bsu.mmf.helpme.presenter.BasePresenter
import io.bsu.mmf.helpme.view.BaseView
import moxy.MvpAppCompatFragment
import javax.inject.Inject


abstract class BaseFragment : MvpAppCompatFragment(), HasAndroidInjector, BaseView {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    protected abstract val layout: Int
    protected abstract val basePresenter: BasePresenter<*>?


    protected lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    protected open fun setupToolbar() = Unit

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        setupToolbar()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        basePresenter?.createJob()
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = findNavController()
    }

    override fun onDestroyView() {
        basePresenter?.cancelJob()
        super.onDestroyView()
    }

    override fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}