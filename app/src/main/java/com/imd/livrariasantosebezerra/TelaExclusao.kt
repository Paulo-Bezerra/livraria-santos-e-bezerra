package com.imd.livrariasantosebezerra

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.imd.livrariasantosebezerra.banco.Banco
import com.imd.livrariasantosebezerra.databinding.ActivityTelaExclusaoBinding

class TelaExclusao : AppCompatActivity() {

  private lateinit var binding: ActivityTelaExclusaoBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityTelaExclusaoBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val etIsbn = binding.etIsbn
    val btExcluir = binding.tvExcluirLivro

    val banco = Banco(this)

    binding.imgBuscarLivro.setOnClickListener {
      val isbn = etIsbn.text.toString()

      if (isbn.isNotEmpty()) {
        val livro = banco.buscarLivro(isbn)

        if (livro.isbn.isNotEmpty()) {
          val tvTitulo = binding.tvTitulo
          val tvEditora = binding.tvEditora
          val tvAno = binding.tvAno
          val ivLivro = binding.ivLivro

          tvTitulo.text = livro.titulo
          tvEditora.text = livro.editora
          tvAno.text = livro.ano

          Glide
            .with(this)
            .load(livro.url)
            .into(ivLivro)

          binding.constraintLayoutExclusao.visibility = View.VISIBLE

        } else {
          Toast.makeText(this, getString(R.string.livro_n_o_encontrado), Toast.LENGTH_SHORT).show()
          binding.constraintLayoutExclusao.visibility = View.GONE
        }
      } else {
        Toast.makeText(this, getString(R.string.digite_o_isbn), Toast.LENGTH_SHORT).show()
        binding.constraintLayoutExclusao.visibility = View.GONE
      }
    }

    btExcluir.setOnClickListener {
      val isbn = etIsbn.text.toString()
      val resultado = banco.deletarLivro(isbn)
      if (resultado) {
        Toast.makeText(this, getString(R.string.livro_exclu_do_com_sucesso), Toast.LENGTH_SHORT)
          .show()
        finish()
      } else {
        Toast.makeText(
          this,
          getString(R.string.n_o_foi_poss_vel_excluir_o_livro), Toast.LENGTH_SHORT
        ).show()
      }
    }
  }
}