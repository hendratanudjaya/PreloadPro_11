package com.example.myresolver

import android.net.Uri
import android.provider.BaseColumns

object userDB {
    class userTable: BaseColumns {
        companion object {
            val TABLE_USER = "user"
            val COLUMN_ID: String = "_id"
            val COLUMN_NAME: String = "nama"
            val COLUMN_EMAIL: String = "email"
            val COLUMN_PHONE: String = "phone"
        }
    }
    class myContentProviderURI {
        companion object {
            private val AUTHORITY = "com.example." +
                    "myfristaop.mypreloadcontentprovider" +
                    ".provider.myContentProvider"
            private val USER_TABLE = userDB.userTable.TABLE_USER
            val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$USER_TABLE")
        }

    }
}
