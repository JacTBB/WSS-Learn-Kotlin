package it2161.myroomapplicationstudent

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

//TODO 5:
// - Implement the data access object (DAO)
// - Add in the methods to retrieve all contacts, insert and delete of contacts
// - Add in the annotations for each functions

//TODO 6:
// - Make use of Flow from Coroutines to observe data changes to the contacts list.
@Dao
interface ContactsDao{
    @Query("Select * from contacts_table")
    fun retrieveAllContacts(): Flow<List<Contacts>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(newContacts: Contacts)

    @Delete
    fun delete(delContact: Contacts)
}
