package com.camilo.pontointeligente.utils

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * Created by marciosouza on 10/14/17.
 */
class SenhaUtils {

    fun gerarBcrypt(senha: String): String = BCryptPasswordEncoder().encode(senha)

}
