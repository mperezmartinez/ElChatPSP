/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatpspfinal;

/**
 *
 * @author Miguel
 */
public class ChatPSPFinal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new Thread() {
            @Override
            public void run() {
                Servidor servidor = new Servidor();
                while (true) {
                    servidor.escuchar();
                }
            }
        }.start();

        new FrameCliente().setVisible(true);
      
        new FrameCliente().setVisible(true);
        
        
        
        
    }

}
