package com.example.myresolver

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor

class userTransaction(context: Context) {
    private val myContentResolver : ContentResolver
    init {
        myContentResolver = context.contentResolver
    }
    fun viewAllName():List<String>{
        val nameList:ArrayList<String> = ArrayList<String>()
        var cursor: Cursor? = null
        cursor = myContentResolver.query(userDB.myContentProviderURI.CONTENT_URI,null,null,null,null)
        var userName: String
        if (cursor.moveToFirst()) {
            do {
                userName = cursor.getString(cursor
                    .getColumnIndex(userDB.userTable.COLUMN_NAME))
                nameList.add(userName)
            } while (cursor.moveToNext())
        }
        return nameList
    }
}

