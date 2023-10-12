package com.developer404.notes

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class adapter :
    RecyclerView.Adapter<adapter.holder> {

    val context: Context
    lateinit var cursor:Cursor
    lateinit var db:DBHelper
    lateinit var face:myInterface

    constructor(context: Context, face:myInterface) : super() {
        db = DBHelper(context)
        cursor = db.fetch()
        this.face=face
        this.context = context
    }

    class holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.item_title)
        val content = itemView.findViewById<TextView>(R.id.item_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_rv,parent,false)
        return holder(view)
    }

    override fun getItemCount(): Int {
       return cursor.count
    }

    override fun onBindViewHolder(holder: holder, position: Int) {
        cursor.moveToPosition(position)
        holder.title.text = cursor.getString(1)
        holder.content.text = cursor.getString(2)

        holder.itemView.setOnClickListener(View.OnClickListener {
            cursor.moveToPosition(position)
            val intent = Intent(context,expand::class.java)
            intent.putExtra("from",cursor.getString(0))
            context.startActivity(intent)
        })
        holder.itemView.setOnLongClickListener(View.OnLongClickListener {
            db.deleteNotes(cursor.getString(0))
            Toast.makeText(context,"Deleted",Toast.LENGTH_LONG).show()
            face.onPressed()
            return@OnLongClickListener true
        })
    }
}