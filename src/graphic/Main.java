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
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
        stage.setTitle(czech.getLanguage1().getName() + "-" + czech.getLanguage2().getName() + " translator !");
        stage.show();
    }

    class MainFrame extends Group {

        // Begin declare component
        private final Label labelTitle = new Label(czech.getLanguage1().getName() + "-" + czech.getLanguage2().getName() + " translator !");
        private final Label label0 = new Label("Search a word");
        private final TextField search = new TextField();
        private final Label label1 = new Label(czech.getLanguage1().getName()), label2 = new Label(czech.getLanguage2().getName());
        private final TextField name1 = new TextField(), name2 = new TextField();
        private final Button validate = new Button("Validate the new word");
        private final ListView showResult = new ListView();
        // End declare

        MainFrame() {
            // Layout for title Label
            Font f = new Font("Calibri", 22);
            labelTitle.setFont(f);
            labelTitle.setTranslateX(20);
            labelTitle.setTranslateY(10);
            labelTitle.setTextFill(Color.web("#00DD00"));
            // Layout for search
            label0.setTranslateX(Config.marginSearchLeft);
            label0.setTranslateY(Config.marginSearchTop);
            search.setTranslateX(Config.marginSearchLeft + Config.widthLabelSearch);
            search.setTranslateY(Config.marginSearchTop);
            // Layout for result 
            showResult.setTranslateX(Config.width / 2 - 100);
            showResult.setTranslateY(Config.marginSearchTop + 50);
            showResult.setPrefSize(200, 100);
            // Layout for add
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
            validate.setTranslateX(Config.width / 2 - 80);
            validate.setTranslateY(Config.height - Config.marginBottom / 2);
            // Action
            validate.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    actionValidate();
                }
            });
            search.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    actionSearch();
                }
            });
            /*validate.setOnAction((ActionEvent t) -> {
             actionValidate();
             });
             search.setOnAction((ActionEvent t) -> {
             actionSearch(); 
             });*/
            // Add to the children
            this.getChildren().add(labelTitle);
            this.getChildren().addAll(label0, search, showResult);
            this.getChildren().addAll(label1, label2);
            this.getChildren().addAll(name1, name2);
            this.getChildren().add(validate);
        }

        private void actionValidate() {
            if (!name1.getText().equals("") && !name2.getText().equals("")) {
                Word w1 = new Word(Language.Czech, name1.getText(), " ");
                Word w2 = new Word(Language.French, name2.getText(), " ");
                Link l1 = new Link(w1, w2);
                String style = validate.getStyle();
                try {
                    czech.insertNewLink(l1);

                    validate.setStyle("-fx-base: #00FF00");
                } catch (Exception ex) {
                    validate.setStyle("-fx-base: #FF0000");
                }
            } else {
                validate.setStyle("-fx-base: #FF0000");
            }
        }

        /**
         * Method called when we search a word.
         */
        private void actionSearch() {
            // Get the search word
            String word = search.getText();
            // Search in czech
            ArrayList<Word> result = czech.searchWord(word);
            // Show result
            if (result != null) {
                showResult.setItems(FXCollections.observableList(result));
            }
            //System.out.println(result.getName());
        }

    }

    public static void main(String[] args) {
        czech.init();
        launch(args);
    }
}
