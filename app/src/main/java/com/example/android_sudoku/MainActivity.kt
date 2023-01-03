package com.example.android_sudoku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.android_sudoku.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // the binding variable is used to bind the xml file with the kt file
    // is better than fin view by id
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view  = binding.root
        setContentView(view)
        //set up a shared preferences file if it doesnt exist already
        var Shared_Prefs = getSharedPreferences(getString(R.string.Shred_Prefs_Name), MODE_PRIVATE)
        if(!Shared_Prefs.contains(getString(R.string.shared_prefs_validity))){
            Toast.makeText(this
                ,Shared_Prefs.getBoolean(getString(R.string.shared_prefs_validity),false).toString()
                ,Toast.LENGTH_SHORT).show()
            Set_Up_Shared_Prefs()
        }
       else{
            Toast.makeText(this
                ,"Shared prefs file exists"
                ,Toast.LENGTH_SHORT).show()
       }


        // add a little mascot animation later (might cancel) # check sticky note ?
        // get reference to x button
        val NewGameBtn:Button = binding.NewGameBtn

        // lambda expressin also this tells the button what happens when it's clicked
        // might add a hover dialouge later ?? also needs mascot
        NewGameBtn.setOnClickListener {
            // intents are responsible for changing scenes and opening different activities
            val i = Intent(this,GameActivity::class.java)
            startActivity(i)

        }
        val ResumeGameBtn:Button = binding.ResumeGameBtn
        ResumeGameBtn.setOnClickListener {
            val i = Intent(this,GameActivity::class.java)
            // addextra(data) ? for the save game data
            startActivity(i)

        }
        val SettingsBtn:Button = binding.SettingsBtn
        SettingsBtn.setOnClickListener {
            val i = Intent(this,SettingsActivity::class.java)
            startActivity(i)

        }
    }
    fun Set_Up_Shared_Prefs()
    {
        var Shared_Prefs = getSharedPreferences(getString(R.string.Shred_Prefs_Name), MODE_PRIVATE)
        var editor = Shared_Prefs.edit()
        editor.putBoolean(getString(R.string.shared_prefs_validity),true)
        editor.putString(getString(R.string.shared_prefs_theme_field),getString(R.string.Red))
        editor.putString(getString(R.string.shared_prefs_difficulty_field),getString(R.string.Begienner))
        editor.putBoolean(getString(R.string.shared_prefs_volume_toggle),true)
        editor.putBoolean(getString(R.string.shared_prefs_music_toggle),true)
        editor.commit()
        Log.d("initialized", Shared_Prefs.getBoolean(getString(R.string.shared_prefs_validity),false).toString())
        Log.d("Theme", Shared_Prefs.getString(getString(R.string.shared_prefs_theme_field),"none").toString())
        Log.d("Difficulty", Shared_Prefs.getString(getString(R.string.shared_prefs_difficulty_field),"null").toString())
        Log.d("Volume", Shared_Prefs.getBoolean(getString(R.string.shared_prefs_volume_toggle),false).toString())
        Log.d("Music", Shared_Prefs.getBoolean(getString(R.string.shared_prefs_music_toggle),false).toString())

    }

}