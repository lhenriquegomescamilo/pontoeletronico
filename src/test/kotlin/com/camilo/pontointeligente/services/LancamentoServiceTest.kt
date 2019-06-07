package com.camilo.pontointeligente.services

import com.camilo.pontointeligente.documents.Lancamento
import com.camilo.pontointeligente.enums.TipoEnum
import com.camilo.pontointeligente.repositories.LancamentoRepository
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mockito.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*


@RunWith(SpringRunner::class)
@SpringBootTest
class LancamentoServiceTest {

    @MockBean
    private val lancamentoRepository: LancamentoRepository? = null

    @Autowired
    val lancamentoService: LancamentoService? = null

    private val ID = "1"

    @Before
    @Throws(Exception::class)
    fun setUp() {
        given(lancamentoRepository?.save(any(Lancamento::class.java))).willReturn(lancamento())
        given(lancamentoRepository?.findOne(ID)).willReturn(lancamento())
        given(lancamentoRepository?.findByFuncionarioId(ID, PageRequest(1, 10)))
                .willReturn(PageImpl(listOf(lancamento())))

    }

    @Test
    fun deve_salvar_um_lancamento() {
        val lancamento = lancamentoService?.persistir(lancamento())
        assertNotNull(lancamento)

    }

    @Test
    fun deve_buscar_por_id() {
        val lancamento = lancamentoService?.buscarPorId(ID)
        assertNotNull(lancamento)
    }

    @Test
    fun
            deve_buscar_todos_lancamento_por_funcionario_id() {
        val pageLancamentos = lancamentoService?.buscarPorFuncionarioId(ID, PageRequest(1, 10))
        assertNotNull(pageLancamentos)
        assertNotNull(pageLancamentos?.first())
    }

    private fun lancamento(): Lancamento {
        return Lancamento(Date(), TipoEnum.INICIO_ALMOCO, "01", "descricao teste", "81:81", "1")
    }

}