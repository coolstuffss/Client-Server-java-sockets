/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package golzio_kikaj_client;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefano Golzio Seriano Kukaj
 */
public class ComunicazioneClient {
    //attributi
    public static final String SERVER_NAME = "127.0.0.1"; //attributi di default per l'esecuzione del programma su localhost
    public static final int SERVER_PORT = 6789; //attributi di default per l'esecuzione del programma su localhost
    private Socket ClientSocket;
    private DataOutputStream outVersoServer;
    private BufferedReader inDalServer;
    
    //you can run the function with the default values connetti(SERVER_NAME, SERVER_PORT)
    //or with custom values connetti("192.168.0.1, 8080) 
    public Socket connetti(String serverName, int serverPort) {
        System.out.println("Connessione al Server...");
        try {
            ClientSocket = new Socket(serverName, serverPort);
            outVersoServer = new DataOutputStream((ClientSocket.getOutputStream()));
            inDalServer = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
        }catch(UnknownHostException e) {
            System.out.println("Unknown Host please retry");
        }catch(Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione");
            System.exit(1);
        }
        
        return ClientSocket;
    }
    
    public String ricevi() {
        String risposta = "";
        try {
            risposta = inDalServer.readLine();
        } catch (IOException ex) {
            Logger.getLogger(ComunicazioneClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return risposta;
    }
    
    public String trasmetti(String numero) {
        try {
            // invio la stringa data in input dall'utente al server
            outVersoServer.writeBytes(numero + "\n");
        }
            catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione con il server");
            System.exit(1);
        }
        
        return numero;
    }
    
    public void chiudi(){
        try {
            // chiudo la connessione
            ClientSocket.close();
            inDalServer.close();
            outVersoServer.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione con il server");
        }
    }
    
}
