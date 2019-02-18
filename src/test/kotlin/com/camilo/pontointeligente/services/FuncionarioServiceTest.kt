package com.camilo.pontointeligente.services

import com.camilo.pontointeligente.documents.Funcionario
import com.camilo.pontointeligente.enums.PerfilEnum.*
import com.camilo.pontointeligente.repositories.FuncionarioRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mockito.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest
class FuncionarioServiceTest {

    @MockBean
    private val funcionarioRepository: FuncionarioRepository? = null

    @Autowired
    private val funcionarioService: FuncionarioService? = null

    private val id: String = "1"
    private val email: String = "camilo@camilo.com.br"
    private val cpf: String = "21204676097"


    @Before
    @Throws(Exception::class)
    fun setUp() {
        given(funcionarioRepository?.save(any(Funcionario::class.java))).willReturn(funcionario())
        given(funcionarioRepository?.findOne(id)).willReturn(funcionario())
        given(funcionarioRepository?.findByEmail(email)).willReturn(funcionario())
        given(funcionarioRepository?.findByCpf(cpf)).willReturn(funcionario())

    }

    @Test
    fun deve_cadastrar_um_novo_funcionario() {
        val funcionario = funcionarioService?.persistir(funcionario())
        assertNotNull(funcionario)
    }

    @Test
    fun deve_buscar_funcionario_pelo_id() {
        val funcionario = funcionarioService?.buscarPorId(id)
        assertNotNull(funcionario)
    }

    @Test
    fun deve_buscar_funcionario_pelo_cpf() {
        val funcionario = funcionarioService?.buscarPorCpf(cpf)
        assertNotNull(funcionario)
    }

    @Test
    fun deve_buscar_funcionario_pelo_email() {
        val funcionario = funcionarioService?.buscarPorEmail(email)
        assertNotNull(funcionario)
    }

    private fun funcionario(): Funcionario = Funcionario(
            "Camilo",
            "camilo@camilo.com.br",
            "12345678910",
            "22855435064",
            ROLE_ADMIN,
            "1",
            10.0,
            10.0f,
            10.0f,
            "1"
    )
}