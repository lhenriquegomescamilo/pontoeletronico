package com.camilo.pontointeligente

import com.camilo.pontointeligente.documents.Empresa
import com.camilo.pontointeligente.documents.Funcionario
import com.camilo.pontointeligente.enums.PerfilEnum
import com.camilo.pontointeligente.repositories.EmpresaRepository
import com.camilo.pontointeligente.repositories.FuncionarioRepository
import com.camilo.pontointeligente.utils.SenhaUtils
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class PontointeligenteApplication(
        val empresaRepository: EmpresaRepository,
        val funcionarioRepository: FuncionarioRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        empresaRepository.deleteAll()
        funcionarioRepository.deleteAll()

        val empresa = empresaRepository.save(Empresa("Empresa", "10443887000146"))

        funcionarioRepository.save(Funcionario(
                "Admin",
                "admin@empresa.com",
                SenhaUtils().gerarBcrypt("123456"),
                "25708317000",
                PerfilEnum.ROLE_ADMIN,
                empresa.id!!
        ))


        val funcionario = funcionarioRepository.save(Funcionario(
                "Funcionario",
                "funcionario@empresa.com",
                SenhaUtils().gerarBcrypt("123456"),
                "44325441557",
                PerfilEnum.ROLE_USUARIO,
                empresa.id))

        println("Empresa ID: ${funcionario.id}")
        println("Administrador ID: ${funcionario.id}")
        println("Funcionario ID: ${funcionario.id}")

    }
}

fun main(args: Array<String>) {
    SpringApplication.run(PontointeligenteApplication::class.java, *args)
}
