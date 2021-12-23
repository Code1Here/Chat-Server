module FirstJavaFX {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;

    opens test;
    opens test.controller;
}