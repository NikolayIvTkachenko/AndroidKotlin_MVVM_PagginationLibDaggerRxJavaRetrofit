package com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rsh_engineering.tkachenkoni.gitviewmanager.R
import kotlinx.android.synthetic.main.lang_row.view.*

/**
 *
 * Created by Nikolay Tkachenko on 27, October, 2020
 *
 */
class LanguageAdapter : RecyclerView.Adapter<LanguageAdapter.LangViewHolder>() {

    var languagesList = ArrayList<String>()
    var languagesMap = HashMap<String, Int>()


    class LangViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var langName = view.tv_lang_name
        var langCount = view.tv_count
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LangViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.lang_row,null)
        return LangViewHolder(view)
    }

    override fun onBindViewHolder(holder: LangViewHolder, position: Int) {
        holder.langName.text = languagesList.get(position)
        holder.langCount.text = languagesMap.get(languagesList.get(position)).toString()
    }

    override fun getItemCount(): Int {
        return languagesList.size
    }

    fun setData(list:ArrayList<String>, mapLang:Map<String, Int>){
        languagesList.clear()
        languagesList.addAll(list)
        languagesMap.clear()
        languagesMap.putAll(mapLang)
    }
}