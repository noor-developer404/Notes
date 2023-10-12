package com.developer404.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.TextView
import android.widget.Toast
import com.developer404.notes.databinding.ActivityExpandBinding

class expand : AppCompatActivity() {
    lateinit var binding: ActivityExpandBinding
    lateinit var db: DBHelper
    lateinit var from:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpandBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DBHelper(this)

        from = intent.getStringExtra("from").toString()
        if (from=="add"){
//            Toast.makeText(this,"add",Toast.LENGTH_SHORT).show()

        }else {
            val cursor = db.fetchById(from)
            cursor.moveToFirst()

//            cursor.moveToPosition(from.toInt())

            binding.expandTitle.setText(cursor.getString(1),TextView.BufferType.EDITABLE)
            binding.expandContent.setText(cursor.getString(2),TextView.BufferType.EDITABLE)
//            Toast.makeText(this,from, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        if (from=="add"){
            Toast.makeText(this,"added",Toast.LENGTH_SHORT).show()
            db.addNotes(binding.expandTitle.text.toString(),binding.expandContent.text.toString())
        }else{
            db.update(binding.expandTitle.text.toString(),binding.expandContent.text.toString(),from)
        }
        super.onBackPressed()
    }
}