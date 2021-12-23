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
import test.controller.Server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer extends Application { /** LAUNCHER */

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/Server.fxml"));
        Parent parent2 = fxmlLoader.load();

        Scene scene2 = new Scene(parent2, 440, 400);
        stage.setScene(scene2);

        Server server = fxmlLoader.getController();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(server);

        stage.show();
    }
}
