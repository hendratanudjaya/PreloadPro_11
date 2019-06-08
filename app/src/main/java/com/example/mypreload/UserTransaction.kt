package com.example.mypreload

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException
import com.example.mypreload.myDB.userDB

class UserTransaction(context: Context) {
    private val myDBHelper = myDBHelper(context)

    private val dbwirte = myDBHelper.writableDatabase
    fun viewAllName():List<String>{
        val nameList:ArrayList<String> = ArrayList<String>()
        val selectQuery = "SELECT  ${userDB.userTable.COLUMN_NAME}" +
                " FROM ${userDB.userTable.TABLE_USER}"
        val db = myDBHelper.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var userName: String
        if (cursor.moveToFirst()) {
            do {
                userName = cursor.getString(cursor.getColumnIndex(userDB.userTable.COLUMN_NAME))
                nameList.add(userName)
            } while (cursor.moveToNext())
        }
        return nameList
    }

    fun addUser(user: User):Long{
        val db = myDBHelper.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(userDB.userTable.COLUMN_NAME, user.nama)
        contentValues.put(userDB.userTable.COLUMN_EMAIL, user.email)
        contentValues.put(userDB.userTable.COLUMN_PHONE,user.no_hp )
        val success = db.insert(userDB.userTable.TABLE_USER,
            null, contentValues)
        db.close()
        return success
    }
    fun beginUserTransaction()
    {
        dbwirte.beginTransaction()
    }
    fun successUserTransaction()
    {
        dbwirte.setTransactionSuccessful()
    }
    fun endUserTransaction()
    {
        dbwirte.endTransaction()
    }
    fun addUserTransaction(user: User):Unit{
        val sqlString = "INSERT INTO ${userDB.userTable.TABLE_USER} " +
                "(${userDB.userTable.COLUMN_NAME}" +
                ",${userDB.userTable.COLUMN_EMAIL}" +
                ",${userDB.userTable.COLUMN_PHONE}) VALUES (?,?,?)"
        val myStatement = dbwirte.compileStatement(sqlString)
        myStatement.bindString(1,user.nama)
        myStatement.bindString(2,user.email)
        myStatement.bindString(3,user.no_hp)
        myStatement.execute()
        myStatement.clearBindings()
    }

    fun deleteAll() {
       val sqlString = "DELETE TABLE ${userDB.userTable.TABLE_USER}"
    }
}






