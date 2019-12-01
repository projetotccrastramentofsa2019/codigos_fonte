//SOFTWARE DA PLACA ARDUÍDO - HARDWARE API SMS

#include <SoftwareSerial.h>

SoftwareSerial serialGSM(10, 11); // RX, TX
String mensagemSMS;
String telefoneSMS;
String dataHoraSMS;
String comandoGSM = "";
String ultimoGSM = "";

bool temSMS = false;

void leGSM();
void configuraGSM();

void setup() {
  Serial.begin(9600);
  serialGSM.begin(9600); 
  configuraGSM();

}

void loop() {
  leGSM();

  if (temSMS) {
    delay(100);

    //IMPRIME NA PORTA SERIAL
     Serial.println(mensagemSMS);
     temSMS = false;
     delay(100);
  }
}

void leGSM()
{
  static String textoRec = "";
  static unsigned long delay1 = 0;
  static int count=0;  
  static unsigned char buffer[64];

  if (serialGSM.available()) {            
 
     while(serialGSM.available()) {         
   
        buffer[count++] = serialGSM.read();     
        if(count == 64)break;
     }

     textoRec += (char*)buffer;
     delay1   = millis();
     
     for (int i=0; i<count; i++) {
         buffer[i]=NULL;
     } 
     count = 0;                       
  }


  if ( ((millis() - delay1) > 100) && textoRec != "" ) {

     if ( textoRec.substring(2,7) == "+CMT:" ) {
        temSMS = true;
     }

     if (temSMS) {
            
        telefoneSMS = "";
        dataHoraSMS = "";
        mensagemSMS = "";

        byte linha = 0;  
        byte aspas = 0;
        for (int nL=1; nL < textoRec.length(); nL++) {

            if (textoRec.charAt(nL) == '"') {
               aspas++;
               continue;
            }                        
          
            if ( (linha == 1) && (aspas == 1) ) {
               telefoneSMS += textoRec.charAt(nL);
            }

            if ( (linha == 1) && (aspas == 5) ) {
               dataHoraSMS += textoRec.charAt(nL);
            }

            if ( linha == 2 ) {
               mensagemSMS += textoRec.charAt(nL);
            }

            if (textoRec.substring(nL - 1, nL + 1) == "\r\n") {
               linha++;
            }
        }
     } else {
       comandoGSM = textoRec;
     }
     
     textoRec = "";  
  }     
}

void configuraGSM() {
   serialGSM.print("AT+CMGF=1\n;AT+CNMI=2,2,0,0,0\n;ATX4\n;AT+COLP=1\n"); 
//  serialGSM.print("CNMI=2,2,0,0,0\n"); 
}
