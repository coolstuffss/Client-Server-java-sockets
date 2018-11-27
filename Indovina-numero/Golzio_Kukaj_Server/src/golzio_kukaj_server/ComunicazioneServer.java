/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package golzio_kukaj_server;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefano Golzio Seriano Kukaj
 */
public class ComunicazioneServer {
    //attributi
    private ServerSocket server = null;
    private Socket client = null;
    private BufferedReader inDalClient;
    private DataOutputStream outVersoClient;
    public final static int SERVER_PORT = 6789;
    
    public Socket attendiRichiesta(int serverPort) {
        try {
            // associo 2 oggetti al socket del client per effettuare la scrittura e la lettura
            
            // creo un server sulla porta che decide l'tente oopure su quella di default (6789)
            server = new ServerSocket(serverPort);
            //Rimane in attesa di un client
            client = server.accept();
            // chiudo il server per inibire altri client
            server.close();
            inDalClient = new BufferedReader(new InputStreamReader (client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
        }
        return client;
    }
    
    public int riceviNumero() {
        int numero = 0;
        try {
            numero = Integer.parseInt(inDalClient.readLine());
        } catch (IOException ex) {
            Logger.getLogger(ComunicazioneServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return numero;
    }
    
    public void comunica(String risultato) {
        try {
            outVersoClient.writeBytes(risultato + "\n");
        }
            catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione con il client");
        }
    }
    
    public void chiudiConnessione() {
        try {
            // termina elaborazione sul server : chiudo la connessione del client
            client.close();
            inDalClient.close();
            outVersoClient.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }   
    }
}
