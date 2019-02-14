package com.pontoeletronico.utils

import org.junit.Assert
import org.junit.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class SenhaUtilTest {

    private val SENHA = "123456"

    private val bCryptEncoder = BCryptPasswordEncoder()

    @Test
    fun deve_gerar_hash_da_senha() {
        val hash = SenhaUtil().gerarBcrypt(SENHA)
        Assert.assertTrue(bCryptEncoder.matches(SENHA, hash))
    }

}