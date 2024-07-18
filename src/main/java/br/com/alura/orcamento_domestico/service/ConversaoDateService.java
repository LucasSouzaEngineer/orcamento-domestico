package br.com.alura.orcamento_domestico.service;

import br.com.alura.orcamento_domestico.dto.AnoMesDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ConversaoDateService {

    public LocalDate obterInicioMes(AnoMesDTO anoMes){
        return LocalDate.of(anoMes.anoMes().getYear(), anoMes.anoMes().getMonth(), 1);
    }

    public LocalDate obterFimMes(AnoMesDTO anoMes){
        return LocalDate.of(anoMes.anoMes().getYear(), anoMes.anoMes().getMonth(), anoMes.anoMes().lengthOfMonth());
    }
}
