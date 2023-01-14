package com.example.gridviewtest

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.android_sudoku.R
import com.example.android_sudoku.Sudoku_Field_model

class Sudoku_field_adapter(var context:Context,var arrayList:ArrayList<Sudoku_Field_model>): BaseAdapter() {
    override fun getCount() = arrayList.size

    override fun getItem(position: Int)= arrayList.get(position)

    override fun getItemId(postion: Int) = postion.toLong()

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        var view: View = View.inflate(context, R.layout.sudoku_zone,null)
        var text:TextView = view.findViewById(R.id.zone_value)
        var bg:ImageView = view.findViewById(R.id.zone_bg)
        var list_item:Sudoku_Field_model = arrayList.get(position)
        list_item.TTV = text
        text.text = list_item.value.toString()

        return view
    }
}