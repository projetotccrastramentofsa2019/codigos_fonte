package silva.danilo.appprojetotcc.model.form;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.math.BigDecimal;

public class LocalizacaoVeiculo implements Serializable {

    private BigDecimal lat;
    private BigDecimal lng;

    public LocalizacaoVeiculo(BigDecimal lat, BigDecimal lng)
    {
        this.lat = lat;
        this.lng = lng;
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

    public LatLng getLatLng(){
        return new LatLng(this.getLat().doubleValue(), this.getLng().doubleValue());
    }
}
