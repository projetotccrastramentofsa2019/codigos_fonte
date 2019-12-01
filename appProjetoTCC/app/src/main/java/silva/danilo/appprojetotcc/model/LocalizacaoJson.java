package silva.danilo.appprojetotcc.model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.math.BigDecimal;

public class LocalizacaoJson implements Serializable {

    private int codigoLocalizacao;
    private BigDecimal lat;
    private BigDecimal lng;
    private String dataRegistro;

    public int getCodigoLocalizacao() {
        return codigoLocalizacao;
    }

    public void setCodigoLocalizacao(int codigoLocalizacao) {
        this.codigoLocalizacao = codigoLocalizacao;
    }


    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(String dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public LatLng getLatLng() {
        return new LatLng(this.getLat().doubleValue(), this.getLng().doubleValue());
    }
}
