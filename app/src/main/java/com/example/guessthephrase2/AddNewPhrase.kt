package com.example.guessthephrase2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddNewPhrase: AppCompatActivity() {

    private val dbHelper by lazy { DBHelper(applicationContext) }
    private lateinit var saveButton: Button
    private lateinit var phraseEntry: EditText

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val item=menu?.getItem(0)
        item!!.title="Play Game"
        item.setIcon(R.drawable.game_icon)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.addNewPhrase -> {
                startActivity(Intent(this,MainActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_phrase)

        saveButton= findViewById(R.id.button)
        phraseEntry= findViewById(R.id.PhraseEntry)

        saveButton.setOnClickListener{
            if (phraseEntry.text.isNotBlank()){
                val wrongCode: Long=-1
                val result= dbHelper.addNewPhrase(phraseEntry.text.toString().uppercase())
                if (wrongCode != result)
                    Toast.makeText(this,"Phrase was Saved",Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
                phraseEntry.text.clear()
            }
            else
                Toast.makeText(this,"Please enter Valid Value",Toast.LENGTH_SHORT).show()
        }

    }
}