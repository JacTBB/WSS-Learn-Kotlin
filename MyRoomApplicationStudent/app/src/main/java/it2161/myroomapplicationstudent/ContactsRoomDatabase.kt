package it2161.myroomapplicationstudent

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

//TODO 7:
//  - Update the ContactsRoomDatabase class to be abstract and extends RoomDatabase
//  - Annotate the class to be a room database
//  - Define a singleton to prevent having multiple instances of the database opened

@Database(entities = arrayOf(Contacts::class), version=1, exportSchema=false)
  abstract class ContactsRoomDatabase: RoomDatabase() {
    abstract fun contactsDao(): ContactsDao

    companion object {
      @Volatile
      private var INSTANCE: ContactsRoomDatabase? = null
      fun getDatabase(context: Context, scope: CoroutineScope): ContactsRoomDatabase {
//        context.deleteDatabase("contacts_database")
        return INSTANCE?: synchronized(this) {
          val instance = Room.databaseBuilder(context,
            ContactsRoomDatabase::class.java, "contacts_database"
          ).build()
          INSTANCE = instance
          instance
        }
      }
    }
}