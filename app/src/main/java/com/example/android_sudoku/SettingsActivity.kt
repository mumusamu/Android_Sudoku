package com.example.android_sudoku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.example.android_sudoku.databinding.ActivityMainBinding
import com.example.android_sudoku.databinding.ActivitySettingsBinding
import com.google.android.material.textfield.TextInputLayout

class SettingsActivity : AppCompatActivity() {
    lateinit var binding: ActivitySettingsBinding
    lateinit var theme_dat: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var Shared_Prefs = getSharedPreferences(getString(R.string.Shred_Prefs_Name), MODE_PRIVATE)
        var editor = Shared_Prefs.edit()

        val Themes = listOf(
            getString(R.string.Red),
            getString(R.string.Yellow),
            getString(R.string.Green),
            getString(R.string.Purple),
            getString(R.string.Blue),
            getString(R.string.Orange)
        )

        val Difficulty_Items = listOf(
            getString(R.string.Begienner),
            getString(R.string.Easy),
            getString(R.string.Medium),
            getString(R.string.Hard),
            getString(R.string.Master),
            getString(R.string.Legend)
        )

        Create_List_Item(Themes, binding.Theme, binding.ThemeData)
        Create_List_Item(Difficulty_Items, binding.difficulty, binding.diffData)

        binding.volumeToggle.setOnClickListener {
            if (Shared_Prefs.getBoolean(getString(R.string.shared_prefs_volume_toggle), true)) {
                editor.putBoolean(getString(R.string.shared_prefs_volume_toggle), false)
                Toast.makeText(
                    this,
                    "Game Audio is now toggled off"
                    ,Toast.LENGTH_SHORT).show()
            } else {
                editor.putBoolean(getString(R.string.shared_prefs_volume_toggle), true)
                Toast.makeText(
                    this,
                    "Game Audio is now toggled on"
                    ,Toast.LENGTH_SHORT).show()
            }
            editor.commit()
            debugLog()

        }
        binding.musicToggle.setOnClickListener {
            if (Shared_Prefs.getBoolean(getString(R.string.shared_prefs_music_toggle), true)) {
                editor.putBoolean(getString(R.string.shared_prefs_music_toggle), false)
                Toast.makeText(
                    this,
                    "Game Music is now toggled off"
                    ,Toast.LENGTH_SHORT).show()
            } else {
                editor.putBoolean(getString(R.string.shared_prefs_music_toggle), true)
                Toast.makeText(
                    this,
                    "Game Music is now toggled on"
                    ,Toast.LENGTH_SHORT).show()
            }
            editor.commit()
            debugLog()

        }
        binding.Defaults.setOnClickListener {
            editor.putString(getString(R.string.shared_prefs_theme_field), getString(R.string.Red))
            editor.putString(getString(R.string.shared_prefs_difficulty_field), getString(R.string.Begienner))
            editor.putBoolean(getString(R.string.shared_prefs_volume_toggle), true)
            editor.putBoolean(getString(R.string.shared_prefs_music_toggle), true)
            editor.commit()
            debugLog()

        }

    }

    fun Create_List_Item(
        String_List_Items: List<String>,
        Target_Item: TextInputLayout,
        ATV: AutoCompleteTextView
    ) {
        val adapter = ArrayAdapter(this, R.layout.settings_dropdown_item, String_List_Items)
        (Target_Item.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        var Shared_Prefs = getSharedPreferences(getString(R.string.Shred_Prefs_Name), MODE_PRIVATE)
        var editor = Shared_Prefs.edit()
        if (ATV == binding.ThemeData){
            ATV.setOnItemClickListener { parent, view, position, id ->
                editor.putString(getString(R.string.shared_prefs_theme_field), ATV.text.toString())
                editor.commit()
                debugLog()

            }
        }
        else if (ATV == binding.diffData){
            ATV.setOnItemClickListener { parent, view, position, id ->
                editor.putString(getString(R.string.shared_prefs_difficulty_field),ATV.text.toString())
                editor.commit()
                debugLog()

            }
        }

    }
    fun debugLog()
    {
        var Shared_Prefs = getSharedPreferences(getString(R.string.Shred_Prefs_Name), MODE_PRIVATE)
        Log.i("initSettingsView", Shared_Prefs.getBoolean(getString(R.string.shared_prefs_validity),false).toString())
        Log.i("Theme_SettingsView", Shared_Prefs.getString(getString(R.string.shared_prefs_theme_field),"none").toString())
        Log.i("Difficulty_SettingsView", Shared_Prefs.getString(getString(R.string.shared_prefs_difficulty_field),"null").toString())
        Log.i("Volume_SettingsView", Shared_Prefs.getBoolean(getString(R.string.shared_prefs_volume_toggle),false).toString())
        Log.i("Music_SettingsView", Shared_Prefs.getBoolean(getString(R.string.shared_prefs_music_toggle),false).toString())

    }
}
