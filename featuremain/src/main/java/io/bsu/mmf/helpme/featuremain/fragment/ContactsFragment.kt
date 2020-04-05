package io.bsu.mmf.helpme.featuremain.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import com.airbnb.epoxy.EpoxyTouchHelper
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.baseAndroid.utils.*
import io.bsu.mmf.helpme.featuremain.R
import io.bsu.mmf.helpme.featuremain.adapter.controller.ContactsController
import io.bsu.mmf.helpme.featuremain.adapter.holder.ContactModelViewModel_
import io.bsu.mmf.helpme.featuremain.viewmodel.ContactsViewModel
import kotlinx.android.synthetic.main.fragment_contacts.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class ContactsFragment : BaseFragment(R.layout.fragment_contacts) {

    private val viewModel by inject<ContactsViewModel>()
    private val contactsController = ContactsController()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contactsController.listener = viewModel::onEventClickListener

        recyclerView.setControllerAndBuildModels(contactsController)

        viewModel.allContacts.observe(viewLifecycleOwner, Observer {
            Timber.e("List recieved")
            contactsController.contactsList = it
        })

        viewModel.contactDetailEvent.observeEvent(viewLifecycleOwner) {
            Timber.e("Details event")
        }

        viewModel.addContactEvent.observeEvent(viewLifecycleOwner) {
            Timber.e("Add event")
            navController.navigate(R.id.action_contactsFragment_to_addContactFragment)
        }

        btn_map.setOnClickListener {
            navController.navigate(R.id.action_contactsFragment_to_mapFragment)
        }

//        viewModel.contactsSource.observe(viewLifecycleOwner, Observer {
//            Timber.e("Current paged size: ${it.size}")
//            contactsController.submitList(it)
//        })


                EpoxyTouchHelper.initSwiping(recyclerView)
            .leftAndRight()
            .withTarget(ContactModelViewModel_::class.java)
            .andCallbacks(object : EpoxyTouchHelper.SwipeCallbacks<ContactModelViewModel_>(){

                override fun onSwipeCompleted(
                    model: ContactModelViewModel_?,
                    itemView: View?,
                    position: Int,
                    direction: Int
                ) {
                    when(direction) {
                        ItemTouchHelper.RIGHT -> {
                            navController.navigateDirectionSafe(
                                ContactsFragmentDirections.actionContactsFragmentToChangeContactFragment(
                                    model?.modelId() ?: 0
                                )
                            )
                        }
                        ItemTouchHelper.LEFT -> {
                            viewModel.deleteContact(model?.modelId() ?: 0)
                        }
                    }
                }
            })

    }

}