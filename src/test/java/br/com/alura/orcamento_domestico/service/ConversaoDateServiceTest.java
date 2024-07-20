package br.com.alura.orcamento_domestico.service;

import br.com.alura.orcamento_domestico.dto.AnoMesDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ConversaoDateServiceTest {
    //@InjectMocks
    @InjectMocks
    private ConversaoDateService conversaoDateService;
    private AnoMesDTO anoMesDTO = new AnoMesDTO(YearMonth.of(2024,1));

    @Test
    void deveriaDevolverInicioDoMes(){
        //arrange
        LocalDate inicioMesEsperado = LocalDate.of(2024, 1, 1);
        //act
        LocalDate inicioMes = conversaoDateService.obterInicioMes(anoMesDTO);
        //assert
        Assertions.assertEquals(inicioMesEsperado, inicioMes);
    }

    @Test
    void deveriaDevolverFimDoMes(){
        //arrange
        LocalDate fimMesEsperado = LocalDate.of(2024, 1, 31);
        //act
        LocalDate fimMes = conversaoDateService.obterFimMes(anoMesDTO);
        //assert
        Assertions.assertEquals(fimMesEsperado, fimMes);
    }

}