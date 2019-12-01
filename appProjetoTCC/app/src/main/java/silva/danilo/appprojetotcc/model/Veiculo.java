package silva.danilo.appprojetotcc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import silva.danilo.appprojetotcc.model.form.LocalizacaoVeiculo;

public class Veiculo implements Serializable {

    private Integer id;
    private String descricao;
    private String placa;
    private String marca;
    private String modelo;
    private String cor;
    private Integer ano;
    private Date dataCadastro;
    private String caminhoFoto;

    private LocalizacaoVeiculo ultimaLocalizacao;

    public Veiculo(){}

    public Veiculo(VeiculoJson veiculoJson)
    {
        setId(veiculoJson.getId());
        setDescricao(veiculoJson.getDescricao());
        setPlaca(veiculoJson.getPlaca());
        setMarca(veiculoJson.getMarca());
        setModelo(veiculoJson.getModelo());

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao != null ? descricao : "[SEM DESCRIÇÃO]";
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca == null ? "[NÃO CADASTRADO]" : marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {

        return modelo == null ? "[NÃO CADASTRADO]" : modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalizacaoVeiculo getUltimaLocalizacao() {

        if(this.ultimaLocalizacao == null)
            ultimaLocalizacao = new LocalizacaoVeiculo(BigDecimal.ZERO, BigDecimal.ZERO);

        return ultimaLocalizacao;
    }

    public void setUltimaLocalizacao(LocalizacaoVeiculo ultimaLocalizacao) {
        this.ultimaLocalizacao = ultimaLocalizacao;
    }

    public Integer getAno() {
        return ano == null ? 0 : ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor == null ? "[NÃO CADASTRADO]" : cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
