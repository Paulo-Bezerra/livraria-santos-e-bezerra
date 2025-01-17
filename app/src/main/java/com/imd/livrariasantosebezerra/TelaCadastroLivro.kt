package com.imd.livrariasantosebezerra

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.imd.livrariasantosebezerra.banco.Banco
import com.imd.livrariasantosebezerra.databinding.ActivityTelaCadastroLivroBinding
import com.imd.livrariasantosebezerra.modelo.Livro
import com.imd.livrariasantosebezerra.web.ImagemRepositorio
import kotlinx.coroutines.launch

class TelaCadastroLivro : AppCompatActivity() {
  private lateinit var binding: ActivityTelaCadastroLivroBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityTelaCadastroLivroBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val etTitulo = binding.etTitulo
    val etAutor = binding.etAutor
    val etEditora = binding.etEditora
    val etAno = binding.etAno
    val etIsbn = binding.etIsbn
    val etDescricao = binding.etDescricao
    val etUrl = binding.etUrl
    val btSalvar = binding.tvSalvarLivro

    etIsbn.setOnFocusChangeListener { _, hasFocus ->
      if (!hasFocus && (etIsbn.text.toString().trim().length == 10 || etIsbn.toString()
          .trim().length == 13)
      ) {
        lifecycleScope.launch {
          Toast.makeText(
            this@TelaCadastroLivro,
            getString(R.string.buscando_url_da_imagem), Toast.LENGTH_SHORT
          ).show()
          val url = ImagemRepositorio.buscarImagem(etIsbn.text.toString().trim())
          if (url.isNotEmpty()) {
            etUrl.setText(url)
            Toast.makeText(
              this@TelaCadastroLivro,
              getString(R.string.url_da_imagem_encontrada), Toast.LENGTH_SHORT
            ).show()
          } else {
            Toast.makeText(
              this@TelaCadastroLivro,
              getString(R.string.url_da_imagem_n_o_encontrada), Toast.LENGTH_SHORT
            ).show()
          }
        }
      }
    }

    val banco = Banco(this)

    btSalvar.setOnClickListener {
      val titulo = etTitulo.text.toString().trim()
      val autor = etAutor.text.toString().trim()
      val editora = etEditora.text.toString().trim()
      val ano = etAno.text.toString().trim()
      val isbn = etIsbn.text.toString().trim()
      val descricao = etDescricao.text.toString().trim()
      val url = etUrl.text.toString().trim()

      val livro = Livro(titulo, autor, editora, ano, isbn, descricao, url)

      val resultado = banco.cadastrarLivro(livro)

      if (resultado) {
        Toast.makeText(this, getString(R.string.livro_cadastrado_com_sucesso), Toast.LENGTH_SHORT)
          .show()
        finish()
      } else {
        Toast.makeText(
          this,
          getString(R.string.n_o_foi_poss_vel_cadastrar_o_livro), Toast.LENGTH_SHORT
        ).show()
      }

    }
  }
}