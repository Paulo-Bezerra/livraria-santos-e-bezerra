package com.imd.livrariasantosebezerra

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.imd.livrariasantosebezerra.banco.Banco
import com.imd.livrariasantosebezerra.databinding.ActivityTelaAlteracaoBinding
import com.imd.livrariasantosebezerra.modelo.Livro

class TelaAlteracao : AppCompatActivity() {

  private lateinit var binding: ActivityTelaAlteracaoBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityTelaAlteracaoBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val etIsbn = binding.etIsbn
    val btAlterar = binding.tvAlterarLivro

    val banco = Banco(this)

    binding.imgBuscarDetalhes.setOnClickListener {
      val isbn = etIsbn.text.toString()

      if (isbn.isNotEmpty()) {
        val livro = banco.buscarLivro(isbn)

        if (livro.isbn.isNotEmpty()) {
          val etTitulo = binding.etTitulo
          val etAutor = binding.etAutor
          val etEditora = binding.etEditora
          val etAno = binding.etAno
          val etDescricao = binding.etDescricao
          val etUrl = binding.etUrl

          etTitulo.setText(livro.titulo)
          etAutor.setText(livro.autor)
          etEditora.setText(livro.editora)
          etAno.setText(livro.ano)
          etDescricao.setText(livro.descricao)
          etUrl.setText(livro.url)

          binding.llDetalhesOcultos.visibility = View.VISIBLE

          btAlterar.setOnClickListener {
            val livro = Livro(
              etTitulo.text.toString(),
              etAutor.text.toString(),
              etEditora.text.toString(),
              etAno.text.toString(),
              isbn,
              etDescricao.text.toString(),
              etUrl.text.toString()
            )
            val resultado = banco.atualizarLivro(livro)

            if (resultado) {
              Toast.makeText(
                this,
                getString(R.string.livro_atualiado_com_sucesso), Toast.LENGTH_SHORT
              ).show()
              finish()
            } else {
              Toast.makeText(
                this,
                getString(R.string.n_o_foi_poss_vel_atualizar_o_livro), Toast.LENGTH_SHORT
              ).show()
            }
          }

        } else {
          Toast.makeText(this, getString(R.string.livro_n_o_encontrado), Toast.LENGTH_SHORT).show()
          binding.llDetalhesOcultos.visibility = View.GONE
        }
      } else {
        Toast.makeText(this, getString(R.string.digite_o_isbn), Toast.LENGTH_SHORT).show()
        binding.llDetalhesOcultos.visibility = View.GONE
      }
    }

  }
}