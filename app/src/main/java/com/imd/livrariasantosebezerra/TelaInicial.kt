package com.imd.livrariasantosebezerra

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.imd.livrariasantosebezerra.databinding.ActivityTelaInicialBinding

class TelaInicial : AppCompatActivity() {
  private lateinit var binding: ActivityTelaInicialBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityTelaInicialBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val btCadastro = binding.btCadastrarLivro
    btCadastro.setOnClickListener {
      startActivity(Intent(this, TelaCadastroLivro::class.java))
    }

    val btListar = binding.btListarLivros
    btListar.setOnClickListener {
      startActivity(Intent(this, TelaListagem::class.java))
    }

    val btExluir = binding.btExcluirLivro
    btExluir.setOnClickListener {
      startActivity(Intent(this, TelaExclusao::class.java))
    }

    val btAlterar = binding.btAlterarLivro
    btAlterar.setOnClickListener {
      startActivity(Intent(this, TelaAlteracao::class.java))
    }

  }
}