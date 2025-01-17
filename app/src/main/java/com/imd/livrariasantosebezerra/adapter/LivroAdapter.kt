package com.imd.livrariasantosebezerra.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.imd.livrariasantosebezerra.R
import com.imd.livrariasantosebezerra.modelo.Livro

class LivroAdapter(val livros: List<Livro>) : RecyclerView.Adapter<LivroViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LivroViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    return LivroViewHolder(layoutInflater.inflate(R.layout.item_livro, parent, false))
  }

  override fun getItemCount(): Int {
    return livros.size
  }

  override fun onBindViewHolder(holder: LivroViewHolder, position: Int) {
    val item = livros[position]
    holder.render(item)
  }
}