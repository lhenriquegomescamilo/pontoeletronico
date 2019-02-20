package com.camilo.pontointeligente.dtos

import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.NotEmpty
import org.hibernate.validator.constraints.br.CNPJ
import org.hibernate.validator.constraints.br.CPF

data class CadastroPfDto(
        @get:NotEmpty(message = "Nome não pode ser vazio.")
        @get:Length(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres.")
        val nome: String = "",

        @get:NotEmpty(message = "Emailnão pode ser vazio.")
        @get:Length(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres.")
        @get:Email(message = "E-mail inválido.")
        val email: String = "",

        @get:NotEmpty(message = "Senha não pode ser vazio.")
        val senha: String = "",

        @get:NotEmpty(message = "CPF não pode ser vazio.")
        @get:CPF(message = "CPF inválido ")
        val cpf: String = "",

        @get:NotEmpty(message = "CNPJ não pode ser vazio.")
        @get:CNPJ(message = "CNPJ inválido ")
        val cnpj: String = "",

        @get:NotEmpty(message = "É obrigatório ter uma empresa")
        val empresaId: String = "",

        val valorHora: String? = null,
        val qtdHorasTrabalhoDia: String? = null,
        val qtdHorasTrabalhoAlmoco: String? = null,

        val id: String? = null

)