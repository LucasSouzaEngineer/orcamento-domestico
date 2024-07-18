package br.com.alura.orcamento_domestico.dto.resumoMesDTO;

import java.time.Month;
import java.time.YearMonth;

public record SolicitacaoResumoMesDTO(
        YearMonth anoMes
) {
}
