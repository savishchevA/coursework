package io.bsu.mmf.helpme.featuremain.adapter.controller

import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import io.bsu.mmf.helpme.baseAndroid.utils.epoxy.EpoxyModelProperty
import io.bsu.mmf.helpme.data.entity.local.Contact
import io.bsu.mmf.helpme.featuremain.adapter.holder.*
import io.bsu.mmf.helpme.featuremain.events.*
import timber.log.Timber

class ContactsController : EpoxyController() {

    var contactsList by EpoxyModelProperty { emptyList<Contact>() }

    var listener: ContactListEventListener = {}


//    override fun addModels(models: List<EpoxyModel<*>>) {
//        Timber.e("Current models size: ${models.size}")
//        super.addModels(models)
//        addContactModelView {
//            id("Add_new_contact")
//            addContactListener {
//                listener(AddContactEvent)
//            }
//        }
//    }
//
//    override fun buildItemModel(currentPosition: Int, item: Contact?): EpoxyModel<*> {
//
//
//        return if (item != null) {
//            ContactModelViewModel_()
//                .id(item.id)
//                .name(item.name)
//                .phone(item.phoneNumber)
//                .address(item.address)
//                .show()
//
//        } else {
//            ContactModelViewModel_()
//                .id("fdfsfsdfsd")
//                .name("test")
//                .phone("244234")
//                .address("fsdfsd")
//                .show()
//
//        }
//    }
//}
////        if (contact != null) {
////            contactModelView {
////                id(contact.id)
////                name(contact.name)
////                phone(contact.phoneNumber)
////                img(contact.contactImage)
////                address(contact.address)
////                onContactClickListener {
////                    listener(ContactDetailEvent(contact))
////                }
////            }
////        } else {
////            addContactModelView {
////                id("Add_new_contact")
////                addContactListener {
////                    listener(AddContactEvent)
////                }
////            }
////        }
//
//    }
//
    override fun buildModels() {
        contactsList.forEach {contact ->
            contactModelView {
                id(contact.id)
                name(contact.name)
                phone(contact.phoneNumber)
                img(contact.contactImage)
                address(contact.address)
                modelId(contact.id)
                onContactClickListener {
                   // listener(ContactDetailEvent(contact))
                }
            }
        }

        addContactModelView {
            id("Add_new_contact")
            addContactListener {
                listener(AddContactEvent)
            }
        }
    }
}