package io.bsu.mmf.helpme.presenter.contact

//import io.bsu.mmf.helpme.data.entity.SosContact
//import io.bsu.mmf.helpme.data.entity.local.Contact
//import io.bsu.mmf.helpme.domain.usecase.FetchContactUseCase
//import io.bsu.mmf.helpme.domain.usecase.contacts.SaveContactUseCase
//
//import io.bsu.mmf.helpme.view.contact.ConfigureContactView
//import kotlinx.coroutines.launch
//import moxy.InjectViewState
//
//
//@InjectViewState
//class ConfigureContactActivityPresenter (
//    private val fetchContact: FetchContactUseCase,
//    private val saveContactUseCase: SaveContactUseCase
//) : BasePresenter<ConfigureContactView>() {
//    fun fetchContactInfo() {
//        val sosContact: SosContact? = fetchContact.execute()
//        viewState?.setData(sosContact?.contactInfo, sosContact?.contactName)
//    }
//
//    fun saveData(contactInfo: String, contactName: String, address: String) {
//        if (contactInfo.isEmpty())
//            viewState?.showContactInfoEmptyError()
//        else {
//            localRepositoryScope.launch {
//                saveContactUseCase(Contact(
//                    name = contactName,
//                    phoneNumber = contactInfo,
//                    address = address,
//                        message = "",
//                    isPriorityContact = false
//                ))
//            }
//
//          //  saveContact.execute(SosContact(contactInfo, contactName))
//            viewState?.nextScreen()
//        }
//    }
//}