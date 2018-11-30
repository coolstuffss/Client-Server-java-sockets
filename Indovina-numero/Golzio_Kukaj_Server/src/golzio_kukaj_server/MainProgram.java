/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package golzio_kukaj_server;

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
        ComunicazioneServer serversocket = new ComunicazioneServer();
        boolean numeroIndovinato = false;
        int tentativi = 5,
            numero = 0,
            i = 0;
        
        serversocket.attendiRichiesta(ComunicazioneServer.SERVER_PORT);
        System.out.println("Client Connesso...");
        
        while(i < tentativi && !numeroIndovinato){
            //numero = (int) Math.floor(Math.random() * 11);
            int ricevuto = serversocket.riceviNumero();
            String s="";
            if(ricevuto == numero){
                serversocket.comunica("0");
                System.out.println("Indovinato");
                numeroIndovinato = true;
            } else {
                if(ricevuto > numero) s="2";//maggiore
                if(ricevuto < numero) s="1";//minore
                serversocket.comunica(s);
                System.out.println("Non indovinato");
                numeroIndovinato = false;
                i++;
            }
        }
        serversocket.chiudiConnessione();
    }
    
}
