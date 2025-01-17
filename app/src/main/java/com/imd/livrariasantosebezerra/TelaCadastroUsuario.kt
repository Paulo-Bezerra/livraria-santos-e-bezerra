package com.imd.livrariasantosebezerra

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.imd.livrariasantosebezerra.banco.Banco
import com.imd.livrariasantosebezerra.databinding.ActivityTelaCadastroUsuarioBinding
import com.imd.livrariasantosebezerra.modelo.Usuario

class TelaCadastroUsuario : AppCompatActivity() {

  private lateinit var binding: ActivityTelaCadastroUsuarioBinding


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityTelaCadastroUsuarioBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val etNome = binding.etNome
    val etUsuario = binding.etUsuario
    val etSenha = binding.etSenha
    val btSalvar = binding.tvSalvarUsuario
    val etConfirmarSenha = binding.etConfirmarSenha

    val banco = Banco(this)

    btSalvar.setOnClickListener {
      val nome = etNome.text.toString().trim()
      val usuario = etUsuario.text.toString().trim()
      val senha = etSenha.text.toString().trim()
      val confirmarSenha = etConfirmarSenha.text.toString().trim()

      if (nome.isBlank() || usuario.isBlank() || senha.isBlank() || confirmarSenha.isBlank()) {
        Toast.makeText(this, getString(R.string.preencha_todos_os_campos), Toast.LENGTH_SHORT)
          .show()
      } else if (senha != confirmarSenha) {
        Toast.makeText(this, getString(R.string.as_senhas_n_o_coincidem), Toast.LENGTH_SHORT).show()
      } else {
        val resultado = banco.cadastrarUsuario(Usuario(nome, usuario, senha))
        if (resultado) {
          Toast.makeText(
            this,
            getString(R.string.usu_rio_cadastrado_com_sucesso),
            Toast.LENGTH_SHORT
          ).show()
          finish()
        } else {
          Toast.makeText(
            this,
            getString(R.string.n_o_foi_poss_vel_cadastrar_esse_usu_rio), Toast.LENGTH_SHORT
          ).show()
        }
      }
    }
  }
}