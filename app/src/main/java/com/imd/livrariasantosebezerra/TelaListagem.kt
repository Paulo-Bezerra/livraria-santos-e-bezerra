package com.imd.livrariasantosebezerra

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.imd.livrariasantosebezerra.adapter.LivroAdapter
import com.imd.livrariasantosebezerra.banco.Banco
import com.imd.livrariasantosebezerra.databinding.ActivityTelaListagemBinding
import com.imd.livrariasantosebezerra.modelo.Livro

class TelaListagem : AppCompatActivity() {

  private lateinit var binding: ActivityTelaListagemBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityTelaListagemBinding.inflate(layoutInflater)
    setContentView(binding.root)
    val banco = Banco(this)
    val livros = banco.listarLivros()
    initRecyclerView(livros)
  }

  fun initRecyclerView(livros: List<Livro>) {
    val rvListagem = binding.rvListagem
    rvListagem.layoutManager = LinearLayoutManager(this)
    rvListagem.adapter = LivroAdapter(livros)

    val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
    binding.rvListagem.addItemDecoration(decoration)
  }
}