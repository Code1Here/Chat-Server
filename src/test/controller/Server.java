package test.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    @FXML
    private TextArea Server_Recieving_Window;

    @FXML
    private TextArea Server_Input_Window;



    public void run() {
        {
            try {
                // Create a server socket
                ServerSocket serverSocket = new ServerSocket(8888);
                Server_Input_Window.requestFocus();

                // Listen for a connection request
                Socket socket = serverSocket.accept();
                // Create data input and output streams
                DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

                while (true) {
                    /** [INPUT ACTION] */
                    String Client_Message = inputFromClient.readUTF();
                    // Displaying what was read
                    Platform.runLater(() -> {
                        Server_Recieving_Window.appendText("Message from Client: " + Client_Message + '\n');
                    });



                    /** [OUTPUT ACTION] */
                    Server_Input_Window.setOnKeyPressed(event -> {
                        if (event.getCode().equals(KeyCode.ENTER)) {
                            try {
                                outputToClient.writeUTF(Server_Input_Window.getText().trim());
                                outputToClient.flush();
                                Server_Input_Window.setText("");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}