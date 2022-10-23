package com.example.kotlin1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.kotlin1.models.Auto


class CarAdapter(context: Context, resource: Int, private var autos: List<Auto>) :
    ArrayAdapter<Auto>(context, resource, autos) {

    private var inflater: LayoutInflater = LayoutInflater.from(context);
    private var layout = resource

    override fun getView(position: Int, convertView1: View?, parent: ViewGroup): View {

        val viewHolder: ViewHolder

        var convertView = convertView1
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false)
            viewHolder = ViewHolder(convertView)
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }

        val textView: TextView = convertView!!.findViewById(android.R.id.text1)
        val currentAuto = autos[position]

        viewHolder.capitalView.text = currentAuto.title

        return convertView
    }

    private class ViewHolder(view: View) {

        val capitalView = view.findViewById(android.R.id.text1) as TextView
    }
}