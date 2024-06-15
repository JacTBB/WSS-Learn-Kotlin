package it2161.myroomapplicationstudent


class ContactsRepository(private val contactsDao: ContactsDao) {
//TODO 8:
//  - Update the repository class to hold a list of contacts from DAO.

//TODO 9:
//  - Add in the necessary functions to do insert and delete of contacts using the DAO

    val allContacts = contactsDao.retrieveAllContacts()

    suspend fun insert(contact: Contacts) {
        contactsDao.insert(contact)
    }

    suspend fun delete(contact: Contacts) {
        contactsDao.delete(contact)
    }
}