package com.imd.livrariasantosebezerra

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.imd.livrariasantosebezerra.banco.Banco
import com.imd.livrariasantosebezerra.databinding.ActivityTelaLoginBinding

class TelaLogin : AppCompatActivity() {

  private lateinit var binding: ActivityTelaLoginBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityTelaLoginBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val etUsuario = binding.etUsuario
    val etSenha = binding.etSenha

    val banco = Banco(this)

    val btEntrar = binding.btEntrar
    btEntrar.setOnClickListener {
      val usuario = etUsuario.text.toString().trim()
      val senha = etSenha.text.toString().trim()
      val resultado = banco.autenticarUsuario(usuario, senha)
      if (resultado) {
        startActivity(Intent(this, TelaInicial::class.java))
        etSenha.setText("")
      } else {
        Toast.makeText(this, getString(R.string.usu_rio_ou_senha_incorretos), Toast.LENGTH_SHORT)
          .show()
      }


    }

    val btCadastrar = binding.tvNovoUsuarioOuSenha
    btCadastrar.setOnClickListener {
      startActivity(Intent(this, TelaCadastroUsuario::class.java))
    }

    val btRedefir = binding.tvRedefinirSenha
    btRedefir.setOnClickListener {
      startActivity(Intent(this, RedefinirSenha::class.java))
    }


  }

}