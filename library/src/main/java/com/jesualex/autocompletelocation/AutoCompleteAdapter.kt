package com.jesualex.autocompletelocation

import android.content.Context
import android.graphics.Typeface
import android.text.style.CharacterStyle
import android.text.style.StyleSpan
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

import com.google.android.libraries.places.api.model.AutocompletePrediction

import java.util.ArrayList

class AutoCompleteAdapter(context: Context) : ArrayAdapter<AutocompletePrediction>(
        context, android.R.layout.simple_expandable_list_item_2, android.R.id.text1
) {
    internal var resultList: List<AutocompletePrediction> = ArrayList()
        set(mResultList) {
            field = mResultList
            notifyDataSetChanged()
        }

    override fun getCount(): Int {
        return resultList.size
    }

    override fun getItem(position: Int): AutocompletePrediction? {
        return resultList[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val row = super.getView(position, convertView, parent)
        val item = getItem(position)

        if (item != null) {
            val textView1 = row.findViewById<TextView>(android.R.id.text1)
            val textView2 = row.findViewById<TextView>(android.R.id.text2)
            textView1.text = item.getPrimaryText(STYLE_BOLD)
            textView2.text = item.getSecondaryText(STYLE_BOLD)
        }

        return row
    }

    companion object {
        private val STYLE_BOLD = StyleSpan(Typeface.BOLD)
    }
}
