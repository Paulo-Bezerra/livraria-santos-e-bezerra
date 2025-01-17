package com.imd.livrariasantosebezerra

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.imd.livrariasantosebezerra.banco.Banco
import com.imd.livrariasantosebezerra.databinding.ActivityRedefinirSenhaBinding

class RedefinirSenha : AppCompatActivity() {

  private lateinit var binding: ActivityRedefinirSenhaBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityRedefinirSenhaBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val etUsuario = binding.etUsuario
    val etSenha = binding.etSenha
    val etConfirmarSenha = binding.etConfirmarSenha
    val btRedefinir = binding.tvRedefinirSenha

    val banco = Banco(this)

    btRedefinir.setOnClickListener {
      val usuario = etUsuario.text.toString().trim()
      val senha = etSenha.text.toString().trim()
      val confirmarSenha = etConfirmarSenha.text.toString().trim()

      if (usuario.isBlank() || senha.isBlank() || confirmarSenha.isBlank()) {
        Toast.makeText(this, getString(R.string.preencha_todos_os_campos), Toast.LENGTH_SHORT)
          .show()
      } else if (senha != confirmarSenha) {
        Toast.makeText(this, getString(R.string.as_senhas_n_o_coincidem), Toast.LENGTH_SHORT).show()
      } else {
        val resultado = banco.redefinirSenha(usuario, senha)
        if (resultado) {
          Toast.makeText(this, getString(R.string.senha_redefinida_com_sucesso), Toast.LENGTH_SHORT)
            .show()
          finish()
        } else {
          Toast.makeText(
            this,
            getString(R.string.n_o_foi_poss_vel_redefinir_a_senha), Toast.LENGTH_SHORT
          ).show()
        }
      }
    }
  }
}