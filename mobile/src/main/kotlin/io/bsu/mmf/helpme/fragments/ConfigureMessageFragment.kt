package io.bsu.mmf.helpme.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import dagger.Lazy
import io.bsu.mmf.helpme.R
import io.bsu.mmf.helpme.presenter.BasePresenter
import io.bsu.mmf.helpme.presenter.message.ConfigureMessageActivityPresenter
import io.bsu.mmf.helpme.view.message.ConfigureMessageView
import kotlinx.android.synthetic.main.activity_message.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class ConfigureMessageFragment : BaseFragment(), ConfigureMessageView {

    @Inject
    lateinit var daggerPresenter: Lazy<ConfigureMessageActivityPresenter>

    @InjectPresenter
    lateinit var presenter: ConfigureMessageActivityPresenter

    @ProvidePresenter
    fun providePresenter(): ConfigureMessageActivityPresenter = daggerPresenter.get()

    override val layout: Int
        get() = R.layout.activity_message
    override val basePresenter: BasePresenter<*>?
        get() = presenter


    private lateinit var defaultMessage: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        defaultMessage = getString(R.string.configure_message_default)
        presenter.fetchMessage()
        next.setOnClickListener {
            presenter.saveData(message.editText?.text.toString())
        }
    }

    override fun setData(message: String?) {
        this.message.editText?.setText(if (message.isNullOrEmpty()) defaultMessage else message)
    }

    override fun showMessageEmptyError() {
        message.editText?.setText(R.string.configure_message_default)
        Toast.makeText(requireContext(), R.string.configure_message_validation_error, Toast.LENGTH_SHORT).show()
    }

    override fun nextScreen() {

        navController.navigate(R.id.action_configureMessageFragment_to_mainFragment)

        Toast.makeText(requireContext(), R.string.configure_message_done, Toast.LENGTH_SHORT).show()
//
    }
}