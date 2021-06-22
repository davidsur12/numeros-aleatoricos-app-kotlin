package app.numeros.numerosaleatoricos.fragments

import androidx.fragment.app.ListFragment

class headLinesFragement : ListFragment() {

    internal lateinit var callback :OnHeadLineSelectedListener;
    fun setOnHeadLineSelectedListener(callback : OnHeadLineSelectedListener){
        this.callback=callback;
    }
    interface OnHeadLineSelectedListener{
        fun onArticleSelected(viewMenu : Boolean);
    }
}