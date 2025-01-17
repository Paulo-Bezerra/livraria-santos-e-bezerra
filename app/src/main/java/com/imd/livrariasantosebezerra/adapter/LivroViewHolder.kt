package com.imd.livrariasantosebezerra.adapter

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.imd.livrariasantosebezerra.TelaDetalhes
import com.imd.livrariasantosebezerra.databinding.ItemLivroBinding
import com.imd.livrariasantosebezerra.modelo.Livro

class LivroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
  val binding = ItemLivroBinding.bind(view)

  val ivLivro = binding.ivLivro
  val tvTitulo = binding.tvTitulo
  val tvEditora = binding.tvEditora
  val tvAno = binding.tvAno


  fun render(livro: Livro) {
    tvTitulo.text = livro.titulo
    tvEditora.text = "Editora: ${livro.editora}"
    tvAno.text = "Ano: ${livro.ano}"
    Glide
      .with(ivLivro.context)
      .load(livro.url)
      .into(ivLivro)

    itemView.setOnClickListener {
      val intent = Intent(itemView.context, TelaDetalhes::class.java)
      intent.putExtra("isbn", livro.isbn)
      itemView.context.startActivity(intent)
    }

  }
}