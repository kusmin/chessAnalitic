package br.com.chess.dto.estatisticas;

import br.com.chess.UtilData;

import java.util.Date;

public class BaseEstatisticasDto {
    private Long rating;

    private long date;

    private String dataFormatada;

    public BaseEstatisticasDto() {
        // Construtor padrao
    }

    public BaseEstatisticasDto(Long rating, Date data) {
        this.rating = rating;
        this.dataFormatada = UtilData.formatoDataCompleta().format(data);
    }


    public String getDataFormatada() {
        return dataFormatada;
    }

    public void setDataFormatada(String dataFormatada) {
        this.dataFormatada = dataFormatada;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
