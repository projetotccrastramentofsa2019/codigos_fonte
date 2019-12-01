package silva.danilo.appprojetotcc.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class LocalizacaoDto {

    private BigDecimal lat;
    private BigDecimal lng;
    private String dataRegistro;

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

    public String retornarDataFormatada()
    {
        StringBuilder str = new StringBuilder();

        if(dataRegistro != null) {

            String[] vstr = dataRegistro.split("T");
            Integer ano, mes, dia, hora, minuto, segundo;

            String[] vdata = vstr[0].split("-");
            String[] vhora = vstr[1].split(":");

            ano = Integer.parseInt(vdata[0]);
            mes = Integer.parseInt(vdata[1]);
            dia = Integer.parseInt(vdata[2]);
            hora = Integer.parseInt(vhora[0]);
            minuto = Integer.parseInt(vhora[1]);
            segundo = Integer.parseInt(vhora[2]);


            Calendar calendar = new GregorianCalendar(ano, mes, dia, hora, minuto, segundo);
            Calendar agora = Calendar.getInstance();

            long diffInMillies = Math.abs(agora.getTimeInMillis() - calendar.getTimeInMillis());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            if(diff > 0)
            {
                return dia + "/" + mes + "/" + ano + "/" + " " + hora + ":" + minuto + ":" + segundo;
            }
            else
            {
                return hora + ":" + minuto + ":" + segundo;
            }

        }

        return str.toString();
    }
}
