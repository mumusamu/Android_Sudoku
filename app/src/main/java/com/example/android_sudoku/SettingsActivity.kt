package com.example.android_sudoku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.example.android_sudoku.databinding.ActivityMainBinding
import com.example.android_sudoku.databinding.ActivitySettingsBinding
import com.google.android.material.textfield.TextInputLayout

class SettingsActivity : AppCompatActivity() {
    lateinit var binding:ActivitySettingsBinding
    lateinit var theme_dat:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        val view  = binding.root
        setContentView(view)

        val Themes = listOf(
            getString(R.string.Red)
            ,getString(R.string.Yellow)
            ,getString(R.string.Green)
            ,getString(R.string.Purple)
            ,getString(R.string.Blue)
            ,getString(R.string.Orange))

        val Difficulty_Items = listOf(
            getString(R.string.Begienner)
            ,getString(R.string.Easy)
            ,getString(R.string.Medium)
            ,getString(R.string.Hard)
            ,getString(R.string.Master)
            ,getString(R.string.Legend))

        Create_List_Item(Themes,binding.Theme,binding.ThemeData)
        Create_List_Item(Difficulty_Items,binding.difficulty,binding.diffData)

    }

    fun Create_List_Item(String_List_Items:List<String> , Target_Item:TextInputLayout,ATV:AutoCompleteTextView){
        val adapter = ArrayAdapter(this,R.layout.settings_dropdown_item,String_List_Items)
        (Target_Item.editText as?  AutoCompleteTextView)?.setAdapter(adapter)
        var Shared_Prefs = getSharedPreferences(getString(R.string.Shred_Prefs_Name), MODE_PRIVATE)
        var editor = Shared_Prefs.edit()
        if (ATV == binding.ThemeData){
            ATV.setOnItemClickListener { parent, view, position, id ->
                editor.putString(getString(R.string.shared_prefs_theme_field), ATV.text.toString())
                editor.commit()
                Toast.makeText(this,
                    Shared_Prefs.getString(getString(R.string.shared_prefs_theme_field),"Wrong"),
                    Toast.LENGTH_SHORT).show()
            }
        }
        else if (ATV == binding.diffData){
            ATV.setOnItemClickListener { parent, view, position, id ->
                editor.putString(getString(R.string.shared_prefs_difficulty_field),ATV.text.toString())
                editor.commit()
                Toast.makeText(this,
                    Shared_Prefs.getString(getString(R.string.shared_prefs_difficulty_field),"Wrong"),
                    Toast.LENGTH_SHORT).show()
            }
        }

        }
    }
