package com.nyp.sit.mydbapplicationstudent

import android.app.Application
import android.content.Context
import android.database.Cursor
import java.util.ArrayList

//TODO 7 :
// - Modify MyContacts class to extend application
// - Declare the two list objects that will hold the id and contact information
// - Create a static instance of MyContacts using the constructor
class MyContacts() : Application(){

    private var contactList: ArrayList<String> = ArrayList<String>()
    private var contactIdList: ArrayList<Int> = ArrayList<Int>()

    companion object {

        val ourInstance = MyContacts()
    }

//TODO 8: Make use of MyDBAdapter's method to perform
//  - insert,
//  - delete,
//  - retrieval of data.

    fun addToDatabase(entryName: String, entryTel: String, c: Context): Long? {
        val db = MyDBAdapter(c)
        db.open()
        val rowIDofInsertedEntry = db.insertEntry(entryName, entryTel)
        db.close()
        return rowIDofInsertedEntry
    }

    fun deleteFrmDatabase(rowID: Int, c: Context): Boolean {
        val db = MyDBAdapter(c)
        db.open()
        val id = contactIdList[rowID]
        val updateStatus = db.removeEntry(id)
        db.close()
        return updateStatus
    }


    fun retrieveAll(c: Context): List<String> {
        val myCursor: Cursor?
        var myString = ""
        val db = MyDBAdapter(c)
        db.open()
        contactIdList.clear()
        contactList.clear()
        myCursor = db.retrieveAllEntriesCursor()
        if (myCursor != null && myCursor.count > 0) {
            myCursor.moveToFirst()
            do {
                contactIdList.add(myCursor.getInt(db.COLUMN_KEY_ID))
                myString = myCursor.getString(db.COLUMN_NAME_ID) + " - " + myCursor.getString((db.COLUMN_TEL_ID)) + "\n"
                contactList.add(myString)
            } while (myCursor.moveToNext())
        }
        return contactList
    }
}