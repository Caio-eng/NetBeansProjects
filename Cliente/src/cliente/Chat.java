package cliente;

import com.sun.glass.events.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import static javax.swing.JOptionPane.*;

public class Chat extends javax.swing.JFrame {

    private String nome;
    private Socket s;
    private BufferedReader br;
    private InputStreamReader isr;
    private boolean rodar;

    //Construtor
    public Chat(String nome) {

        initComponents();
        
        rodar = true;
        this.nome = nome;

        try {
            s = new Socket("127.0.0.1", 5000);
        } catch (Exception e) {

            showMessageDialog(null, "Não se conectou ao servidor");
            System.exit(0);
        }
        
        Thread();

    }
    // recebe as mensagens
    private void Thread() {
        
        Thread t = new Thread(new Runnable() {
            
            String mensagem;
            
            @Override
            public void run() {
                
                try {
                    
                    isr = new InputStreamReader(s.getInputStream());
                    br = new BufferedReader(isr);
                    
                    while((mensagem = br.readLine()) != null) {
                        
                        mensagemRecebida.setText(mensagemRecebida.getText() + mensagem + "\n");
                        
                        if (!rodar) {
                            
                            break;
                            
                        }
                        
                    }
                    
                } catch(IOException e) {
                    
                    showMessageDialog(null, "Erro na conexão com o servidor", "", ERROR_MESSAGE);
                    
                }
               
            }
        });
        
        t.start();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        enviar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        mensagemEnviada = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        mensagemRecebida = new javax.swing.JTextArea();
        sair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        enviar.setText("Enviar");
        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarActionPerformed(evt);
            }
        });

        mensagemEnviada.setColumns(20);
        mensagemEnviada.setRows(5);
        mensagemEnviada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mensagemEnviadaKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(mensagemEnviada);

        mensagemRecebida.setEditable(false);
        mensagemRecebida.setColumns(20);
        mensagemRecebida.setRows(5);
        jScrollPane3.setViewportView(mensagemRecebida);

        sair.setText("Sair");
        sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(enviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sair, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarActionPerformed

        String mensagem = nome + " disse: ";

        try {

            PrintStream ps = new PrintStream(s.getOutputStream());
            mensagem += mensagemEnviada.getText();

            ps.println(mensagem);
            ps.flush();

            mensagemEnviada.setText("");

        } catch (IOException e) {

            showMessageDialog(null, "Não conseguiu enviar a mensagem", "", ERROR_MESSAGE);

        }


    }//GEN-LAST:event_enviarActionPerformed

    private void mensagemEnviadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mensagemEnviadaKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            String mensagem = nome + " disse: ";

            try {

                PrintStream ps = new PrintStream(s.getOutputStream());
                mensagem += mensagemEnviada.getText();

                ps.println(mensagem);
                ps.flush();

                mensagemEnviada.setText("");

            } catch (IOException e) {

                showMessageDialog(null, "Não conseguiu enviar a mensagem", "", ERROR_MESSAGE);

            }
        }

    }//GEN-LAST:event_mensagemEnviadaKeyPressed
    
    //Botão sair
    private void sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairActionPerformed
        
        try {
            
            s.close();
            
            System.exit(0);
            
        } catch(Exception e) {
            
            e.printStackTrace();
            
        }
        
    }//GEN-LAST:event_sairActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton enviar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea mensagemEnviada;
    private javax.swing.JTextArea mensagemRecebida;
    private javax.swing.JButton sair;
    // End of variables declaration//GEN-END:variables
}
