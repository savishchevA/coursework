package io.bsu.mmf.helpme.common.usecase.contact

import androidx.lifecycle.LiveData
import androidx.paging.*
import io.bsu.mmf.helpme.common.repository.ContactsLocalRepository
import io.bsu.mmf.helpme.data.entity.local.Contact

class GetAllSourceUseCase (
    private val contactsLocalRepository: ContactsLocalRepository
) {

    operator fun invoke(): DataSource.Factory<Int, Contact>  {

        return contactsLocalRepository.getAllSource()
    }

}