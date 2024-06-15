package it2161.myroomapplicationstudent

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//TODO 4:
// - Update the entity class with annotations

@Entity(tableName = "contacts_table")
data class Contacts(
    @PrimaryKey(autoGenerate=true) @ColumnInfo(name="id") val id : Int,
    @ColumnInfo(name="name") val name: String,
    @ColumnInfo(name="num") val num : String,
    @ColumnInfo(name="address") val address : String,
    @ColumnInfo(name="notes") val notes : String
)