/*

        // Name: Anthony Duran
        // Status: Complete
        // Purpose: Facilitates the communication between two sockets

 */
package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import test.controller.Client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatClient extends Application { /** LAUNCHER */

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/Client.fxml"));
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent, 440, 400);
        stage.setScene(scene);

        Client client = fxmlLoader.getController();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(client);

        stage.show();
    }
}
