module App {
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.web;
    requires java.sql;
    requires mysql.connector.j;
    requires java.desktop;
    opens ma.browser.enset.Presentation;
    opens ma.browser.enset.Presentation.Controllers;
    opens ma.browser.enset.Dao.Entities;
}