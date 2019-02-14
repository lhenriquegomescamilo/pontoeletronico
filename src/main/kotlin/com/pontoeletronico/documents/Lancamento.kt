package com.pontoeletronico.documents

import com.pontoeletronico.enums.TipoEnum
import org.springframework.data.annotation.Id
import java.util.*

class Lancamento(
        val data: Date,
        val tipo: TipoEnum,
        val funcionarioId: String,
        val descricao: String? = "",
        val localizacao: String? = "",
        @Id val id: String? = null

)