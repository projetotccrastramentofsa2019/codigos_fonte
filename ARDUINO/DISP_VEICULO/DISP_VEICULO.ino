#include <SoftwareSerial.h>
#include <TinyGPS.h>
#include <string.h>

#define NUMERO_API "COLOQUE O NÚMERO AQUI!!!!!!!!!!!!!!!!!!!"


#define ID_VEICULO 1
#define ID_USUARIO 1

//CONFIG GSM
SoftwareSerial serialGSM(10, 11); // RX, TX

//CONFIG GPS
TinyGPS gps;
#define GPS_RX 4
#define GPS_TX 3
#define GPS_Serial_Baud 9600
SoftwareSerial gpsSerial(GPS_RX, GPS_TX);

float lat, lon;
char msg[50];

bool temSMS = false;

//void leGSM();
void enviaSMS(String telefone, String mensagem);
void configuraGSM();

void setup() {
  Serial.begin(9600);
  serialGSM.begin(9600);

  configuraGSM();


 gpsSerial.begin(GPS_Serial_Baud);
  
}

void loop() {

   while(gpsSerial.available())
    {
      if(gps.encode(gpsSerial.read()))  
      {
          gps.f_get_position(&lat, &lon);

          char strLat[20];
          char strLng[20];

           dtostrf(lat, 6, 6, strLat);
           dtostrf(lon, 6, 6, strLng);
          
          sprintf(msg, "%d#%d#%s#%s##",ID_USUARIO, ID_VEICULO, strLat, strLng);

          enviaSMS(NUMERO_API, msg);

          delay(60000);
        //  Serial.println(msg);

          
      }
    }
}

void enviaSMS(String telefone, String mensagem) {
  serialGSM.print("AT+CMGS=\"" + telefone + "\"\n");
  serialGSM.print(mensagem + "\n");
  serialGSM.print((char)26);
  Serial.println("ENVIOU SMS " + mensagem); 
}

void configuraGSM() {
   serialGSM.print("AT+CMGF=1\n;AT+CNMI=2,2,0,0,0\n;ATX4\n;AT+COLP=1\n"); 
//  serialGSM.print("CNMI=2,2,0,0,0\n"); 
}
