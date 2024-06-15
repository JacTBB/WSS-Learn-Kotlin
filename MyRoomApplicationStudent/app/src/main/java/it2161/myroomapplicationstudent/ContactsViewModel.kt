package it2161.myroomapplicationstudent

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

//TODO 10:
//  - Update ContactsViewModel to implement ViewModel

class ContactsViewModel(val repo: ContactsRepository): ViewModel()  {
    //TODO 11:
    // Create a list of contacts
    // - Use LiveData to cache all contacts
    val allContacts: LiveData<List<Contacts>> = repo.allContacts.asLiveData()

    //TODO 12 :
    // - Launch a new coroutine to insert the contact
    fun insert(contactItem: Contacts) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(contactItem)
    }

    //TODO 13 :
    // - Launch a new coroutine to remove the contact
    fun remove(contactItem: Contacts) = viewModelScope.launch(Dispatchers.IO) {
        repo.delete(contactItem)
    }
}

//TODO 14:
// - Implement ContactsViewModelFactory to create ContactsViewModel using the ContactsRepository

class ContactsViewModelFactory(private val repo : ContactsRepository): ViewModelProvider.Factory {
     override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ContactsViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}