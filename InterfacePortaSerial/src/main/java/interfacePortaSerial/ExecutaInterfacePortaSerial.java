package interfacePortaSerial;

import java.util.Scanner;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import webClient.ClienteInterface;

public class ExecutaInterfacePortaSerial {
	
	private static StringBuilder buffer = new StringBuilder();
	
	public static void main(String[] args) 
	{
		System.out.println("INICIOU");
		
		final ClienteInterface cliente = new ClienteInterface();
		
		Scanner sc = new Scanner(System.in);
//		SerialPort comPort = SerialPort.getCommPort("COM7");
		SerialPort comPort = SerialPort.getCommPort("ttyACM0");
		comPort.setBaudRate(9600);
		comPort.addDataListener(new SerialPortDataListener() {
			
			public void serialEvent(SerialPortEvent event) {
					
				String strRecebida = new String(event.getReceivedData());
				
				System.out.println("-----------------------------------------");
				System.out.println("\nSERIAL EVENT: ");
				
				System.out.println(strRecebida);
				
				if(strRecebida.isEmpty())
				{
					return;
				}
				buffer.append(strRecebida);
				
				String strBuffer = buffer.toString();
				System.out.println("mensagem concat: ");
				System.out.println(strBuffer);
				
				if(strBuffer.trim().endsWith("##"))
				{
					System.out.println("ENCONTROU FINALIZADOR");
					buffer = new StringBuilder();
					
					String mensagemEnvio = strBuffer.trim().replace("##", "");
					
					System.out.println("mensagem envio:");
					System.out.println(mensagemEnvio);
					cliente.enviarLocalizacaoDoVeiculo(mensagemEnvio);
				}
			}
			
			public int getListeningEvents() {

				return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
			}
		});
		
		comPort.openPort();
		
		
		sc.nextLine();
		
		sc.close();
	}

}
