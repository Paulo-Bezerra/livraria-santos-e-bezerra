package com.imd.livrariasantosebezerra

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.imd.livrariasantosebezerra.banco.Banco
import com.imd.livrariasantosebezerra.databinding.ActivityTelaDetalhesBinding


class TelaDetalhes : AppCompatActivity() {

  private lateinit var binding: ActivityTelaDetalhesBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityTelaDetalhesBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val banco = Banco(this)

    val isbn = intent.getStringExtra("isbn")!!
    val livro = banco.buscarLivro(isbn)

    val tvTitulo = binding.tvTitulo
    val tvAutor = binding.tvAutor
    val tvEditora = binding.tvEditora
    val tvAno = binding.tvAno
    val tvIsbn = binding.tvIsbn
    val tvDescricao = binding.tvDescricao
    val ivLivro = binding.imgDetalhar

    tvTitulo.text = livro.titulo
    tvAutor.text = livro.autor
    tvEditora.text = livro.editora
    tvAno.text = livro.ano
    tvIsbn.text = isbn
    tvDescricao.text = livro.descricao
    Glide
      .with(this)
      .load(livro.url)
      .into(ivLivro)

  }
}