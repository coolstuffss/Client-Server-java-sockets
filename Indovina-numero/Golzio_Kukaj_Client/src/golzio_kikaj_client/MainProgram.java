/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package golzio_kikaj_client;

import java.util.Scanner;

/**
 *
 * @author Stefano Golzio Seriano Kukaj
 */
public class MainProgram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ComunicazioneClient clientsocket = new ComunicazioneClient();
        Scanner tastiera = new Scanner(System.in);
        boolean uscita = false;
        String risposta;
        
        clientsocket.connetti(ComunicazioneClient.SERVER_NAME, ComunicazioneClient.SERVER_PORT);
        
        do{
            uscita = false;
            
            System.out.println("Inseire un numero: ");
            String numero = tastiera.next();
            clientsocket.trasmetti(numero);
            risposta = clientsocket.ricevi();
            
            switch(risposta){
                case "0": System.out.println("Numero indovinato");
                uscita= true;
                    break;
                case "1": System.out.println("Il numero e' maggiore");
                uscita= false;
                    break;
                case "2": System.out.println("Il numero e' minore");
                uscita= false;
                    break;
                case "3": System.out.println("Numero non ndovinato");
                uscita=false;
                    break;
            }
            
        }while(!uscita);
        
        clientsocket.chiudi();
        
    }
    
}
