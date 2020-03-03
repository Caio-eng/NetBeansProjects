package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Servidor {

    public static void main(String[] args) {
        
        String mensagemRecebida;
        
        try {
            
            ServerSocket server = new ServerSocket(5000);
            Socket socket;
            
            while(true) {
                
                socket = server.accept();
                
                InputStreamReader isr = new InputStreamReader(socket.getInputStream()); // recebe os dados
                BufferedReader br = new BufferedReader(isr); //le os dados
                
                while((mensagemRecebida = br.readLine()) != null) {
                    
                    System.out.println(mensagemRecebida);
                }
                
            }
                    
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
    
}
