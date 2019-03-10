package com.camilo.pontointeligente

import com.camilo.pontointeligente.documents.Empresa
import com.camilo.pontointeligente.documents.Funcionario
import com.camilo.pontointeligente.enums.PerfilEnum.ROLE_USUARIO
import com.camilo.pontointeligente.enums.PerfilEnum.ROLE_ADMIN
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

        val empresa = empresaRepository
                .save(Empresa("Empresa", "26988388000174"))

        val funcionario = funcionarioRepository.save(Funcionario(
                "Funcionario",
                "funcionario@gmail.com.br",
                SenhaUtils().gerarBcrypt("123456"),
                "87281069030",
                ROLE_USUARIO,
                empresa.id!!
        ))

        val admin = funcionarioRepository.save(Funcionario(
                "Administrador",
                "funcionario@gmail.com.br",
                SenhaUtils().gerarBcrypt("123456"),
                "87281069030",
                ROLE_ADMIN,
                empresa.id
        ))

        print("Funcionario ID: ${empresa?.id}")
        print("Admin ID: ${funcionario?.id}")
        print("Funcionario ID: ${admin?.id}")
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(PontointeligenteApplication::class.java, *args)
}