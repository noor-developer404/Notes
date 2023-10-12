package com.developer404.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.developer404.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),myInterface {
    lateinit var binding: ActivityMainBinding
    var arr=ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper = DBHelper(this)
//        dbHelper.deleteNotes(1.toString())
//        dbHelper.deleteNotes(2.toString())
//        dbHelper.deleteNotes(3.toString())

//        dbHelper.addNotes("Shoping","book, maggie, fruits, juice")
//        dbHelper.addNotes("names","noor, ayan, jakir, kaif")


        binding.mainRv.layoutManager=GridLayoutManager(this,2)
        binding.mainRv.adapter = adapter(this,this)

        binding.fab.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,expand::class.java)
            intent.putExtra("from","add")
            startActivity(intent)
        })
    }

    override fun onResume() {
        binding.mainRv.adapter = adapter(this,this)
        super.onResume()
    }

    override fun onPressed() {
        onResume()
    }
}