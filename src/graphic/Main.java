/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import data.Dictionary;
import data.Language;
import data.Link;
import data.Word;
import exception.AlreadyExistException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

/**
 *
 * @author Maxime
 */
public class Main extends Application {

    /**
     * MainFrame.
     */
    private final MainFrame mainFrame = new MainFrame();

    /**
     * The dictionary.
     */
    private static final Dictionary czech = new Dictionary(Language.Czech, Language.French);

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(mainFrame, Config.width, Config.height));
        stage.show();
    }

    class MainFrame extends Group {

        // Begin declare component
        private final TextField search = new TextField();
        private final Label label1 = new Label("Czech"), label2 = new Label("French");
        private final TextField name1 = new TextField(), name2 = new TextField();
        private final Button validate = new Button("Validate");
        // End declare

        MainFrame() {
            // Layout
            label1.setTranslateX(Config.width / 2 - Config.margin - Config.widthTextField);
            label1.setTranslateY(Config.height - Config.marginBottom - 20);
            name1.setPrefWidth(Config.widthTextField);
            name1.setTranslateX(Config.width / 2 - Config.margin - Config.widthTextField);
            name1.setTranslateY(Config.height - Config.marginBottom);
            label2.setTranslateX(Config.width / 2 + Config.margin);
            label2.setTranslateY(Config.height - Config.marginBottom - 20);
            name2.setPrefWidth(Config.widthTextField);
            name2.setTranslateX(Config.width / 2 + Config.margin);
            name2.setTranslateY(Config.height - Config.marginBottom);
            validate.setTranslateX(Config.width / 2 - 30);
            validate.setTranslateY(Config.height - Config.marginBottom / 2);
            // Action
            validate.setOnAction((ActionEvent t) -> {
                Word w1 = new Word(Language.Czech, name1.getText(), " ");
                Word w2 = new Word(Language.French, name2.getText(), " ");
                Link l1 = new Link(w1, w2);
                try {
                    czech.insertNewLink(l1);
                } catch (AlreadyExistException ex) {
                    validate.setStyle("-fx-base: #FF0000");
                } finally {
                    validate.setStyle("-fx-base: #00FF00");
                }
            });
            // Add to the children
            this.getChildren().addAll(label1, label2);
            this.getChildren().addAll(name1, name2);
            this.getChildren().add(validate);
        }
    }

    public static void main(String[] args) {
        czech.init();
        launch(args);
    }
}
