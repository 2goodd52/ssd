/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.sql.Database;

/**
 *
 * @author Dean
 */
public class UserInterface extends Application {
    
    @Override
    public void start(final Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/layout/window.fxml"));
        
        final Scene scene = new Scene(root);
        
        stage.setTitle("Game Café");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest((final WindowEvent event) -> {
            Database.getSingleton().save();
        });
        
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
