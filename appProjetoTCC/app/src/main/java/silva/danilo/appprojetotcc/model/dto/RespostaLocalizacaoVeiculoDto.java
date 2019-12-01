package silva.danilo.appprojetotcc.model.dto;

import java.util.List;

public class RespostaLocalizacaoVeiculoDto {

    private Integer numeroTotalDeElementos;
    private Integer numeroDePaginas;
    private Integer indicePagina;

    private List<LocalizacaoDto> itens;

    public Integer getNumeroTotalDeElementos() {
        return numeroTotalDeElementos;
    }

    public void setNumeroTotalDeElementos(Integer numeroTotalDeElementos) {
        this.numeroTotalDeElementos = numeroTotalDeElementos;
    }

    public Integer getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public void setNumeroDePaginas(Integer numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }

    public Integer getIndicePagina() {
        return indicePagina;
    }

    public void setIndicePagina(Integer indicePagina) {
        this.indicePagina = indicePagina;
    }

    public List<LocalizacaoDto> getItens() {
        return itens;
    }

    public void setItens(List<LocalizacaoDto> itens) {
        this.itens = itens;
    }
}
