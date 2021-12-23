package test.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends Thread {

    // create a socket to connect to the server
    Socket socket;

    {
        try {
            socket = new Socket("localhost", 8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // create an input stream to receive data from the server
    DataOutputStream toServer;

    {
        try {
            toServer = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // create an output stream to send data to the server
    DataInputStream fromServer;

    {
        try {
            fromServer = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private TextArea Client_Recieving_Window;

    @FXML
    private TextArea Client_Input_Window;



    public void run() {
        Client_Input_Window.requestFocus();
        /** Writing Client's input to the Server [OUTPUT ACTION] */
        Client_Input_Window.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                try {
                    toServer.writeUTF(Client_Input_Window.getText().trim());
                    toServer.flush();
                    Client_Input_Window.setText("");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });


        new Thread(() -> {
            /** Reading incoming message [INPUT ACTION] */
            String Server_Message = null;
            while(true) {
                try {
                    Server_Message = fromServer.readUTF();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Displaying what was read
                String finalServer_Message = Server_Message;
                Platform.runLater(() -> {
                    Client_Recieving_Window.appendText("Message from Server: " + finalServer_Message + "\n");
                });
            }
        }).start();
    }
}