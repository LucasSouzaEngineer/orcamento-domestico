package br.com.alura.orcamento_domestico.service;

import br.com.alura.orcamento_domestico.dto.despesaDTO.CadastroDespesaDTO;
import br.com.alura.orcamento_domestico.model.CategoriaDespesa;
import br.com.alura.orcamento_domestico.model.Despesa;
import br.com.alura.orcamento_domestico.repository.DespesaRepository;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class DespesaServiceTest {
    @InjectMocks
    private DespesaService despesaService;
    @Mock
    private DespesaRepository despesaRepository;
    @Mock
    private ConversaoDateService conversaoDateService;
    private CadastroDespesaDTO cadastroDespesaDTO;
    @Captor
    private ArgumentCaptor<Despesa> despesaCaptor;

    @Test
    void deveriaSalvarDespesa(){
        //arrange
        cadastroDespesaDTO = new CadastroDespesaDTO("Descrição teste", BigDecimal.ONE, LocalDate.now(), CategoriaDespesa.ALIMENTACAO);
        //act
        despesaService.cadastrarDespesa(cadastroDespesaDTO);
        //assert
        then(despesaRepository).should().save(despesaCaptor.capture());
    }
}