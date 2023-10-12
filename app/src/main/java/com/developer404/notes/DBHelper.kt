package com.developer404.notes

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL("CREATE TABLE "+ TABLE + "( id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT )")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    companion object{
        private val DB_NAME = "notes"
        private val DB_VERSION=1
        private val TABLE="all_notes"
    }

    public fun addNotes(title:String, content:String){
        val contentValues = ContentValues()
        contentValues.put("title",title)
        contentValues.put("content",content)
        val db = writableDatabase
        db.insert(TABLE,null,contentValues)
//        db.close()
    }
    public fun deleteNotes(position:String){
        val db = writableDatabase
        db.delete(TABLE,"id=?", arrayOf(position))
//        db.close()
    }
    public fun fetch():Cursor{
        val db = readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE ,null)
    }
    public fun fetchById(note_id:String):Cursor{
        val db = readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE +" WHERE id=="+note_id,null)
    }

    public fun update(title: String,content: String,note_id:String){
        val contentValues = ContentValues()
        contentValues.put("title",title)
        contentValues.put("content",content)
        val db = writableDatabase
        db.update(TABLE,contentValues,"id=?", arrayOf(note_id))
    }
}