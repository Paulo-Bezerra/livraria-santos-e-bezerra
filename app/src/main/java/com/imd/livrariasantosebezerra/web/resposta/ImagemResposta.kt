package com.imd.livrariasantosebezerra.web.resposta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImagemResposta(
  @SerialName("thumbnail_url")
  var url: String = ""
) {
  fun urlImagem() = url.replace("-S.jpg", "-M.jpg")
}