package com.example.android_sudoku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import com.example.android_sudoku.databinding.ActivityGameBinding
import com.example.gridviewtest.Sudoku_field_adapter

class GameActivity : AppCompatActivity() {
    lateinit var binding: ActivityGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var grid: GridView = binding.grid
        var arrayList: ArrayList<Sudoku_Field_model> = setData(1)
        var adapter = Sudoku_field_adapter(this, arrayList)
        grid.adapter = adapter

        grid.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(
                this, "x = "
                        + arrayList[position].x_index + ", y = "
                        + arrayList[position].y_index + " in Block "
                        + arrayList[position].field_index, Toast.LENGTH_SHORT
            ).show()
        }
        Toast.makeText(this, grid.count, Toast.LENGTH_SHORT)

        var Redo = binding.redo
        Redo.setOnClickListener {
            Toast.makeText(this, "Not yet implemented", Toast.LENGTH_SHORT).show()
        }
        var Undo = binding.redo
        Undo.setOnClickListener {
            Toast.makeText(this, "Not yet implemented", Toast.LENGTH_SHORT).show()
        }
        var Reset = binding.redo
        Reset.setOnClickListener {
            Toast.makeText(this, "Not yet implemented", Toast.LENGTH_SHORT).show()
        }
        var Check = binding.redo
        Check.setOnClickListener {
            Toast.makeText(this, "Not yet implemented", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setData(bLock_Int: Int): ArrayList<Sudoku_Field_model> {
        var arrayList: ArrayList<Sudoku_Field_model> = ArrayList()
        for (x in 0..8) {
            for (y in 0..8) {
                arrayList.add(Sudoku_Field_model(x, y, arrayList.size + 1, checkBV(x, y), null))
            }
        }
        return arrayList
    }

    fun checkBV(x: Int, y: Int): Int {
        var Block_Value = 0
        when (x) {
            in 0..2 -> when (y) {
                in 0..2 -> Block_Value = 1
                in 3..5 -> Block_Value = 2
                else -> Block_Value = 3
            }
            in 3..5 -> when (y) {
                in 0..2 -> Block_Value = 4
                in 3..5 -> Block_Value = 5
                else -> Block_Value = 6
            }
            else -> when (y) {
                in 0..2 -> Block_Value = 7
                in 3..5 -> Block_Value = 8
                else -> Block_Value = 9
            }
        }
        return Block_Value
    }
    fun Check_Block(block: Sudoku_Field_model){
        when(block.TTV?.text){

        }
    }
}