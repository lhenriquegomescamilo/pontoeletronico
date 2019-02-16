package com.camilo.pontointeligente.repositories

import com.camilo.pontointeligente.documents.Funcionario
import org.springframework.data.mongodb.repository.MongoRepository


interface FuncionarioRepository : MongoRepository<Funcionario, String> {

    fun findByEmail(email: String): Funcionario

    fun findByCpf(cpf: String): Funcionario

}
