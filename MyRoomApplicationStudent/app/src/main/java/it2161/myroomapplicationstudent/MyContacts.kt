package it2161.myroomapplicationstudent

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


class MyContacts() : Application(){
//TODO 15:
// - Create a database and repo instance
// - Make use of the "by lazy" delegation to state that the objects are tbe created when first needed. Instead of at start up.
    val appScope = CoroutineScope(SupervisorJob())
    val db by lazy{ContactsRoomDatabase.getDatabase(this, appScope)}
    val repo by lazy{ContactsRepository(db.contactsDao())}
}