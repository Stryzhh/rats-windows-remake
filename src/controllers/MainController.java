package controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.StageFunctions;

/**
 * Main
 *
 * @author Dafydd-Rhys Maund (2003900)
 * @author Gareth Wade (1901805)
 */
public class MainController implements Initializable {

    @FXML
    private AnchorPane window;
    @FXML
    private ComboBox<String> selectBiome;
    @FXML
    private JFXButton settings;
    @FXML
    private JFXButton minimize;
    @FXML
    private JFXButton maximise;
    @FXML
    private JFXButton exit;
    @FXML
    private TextField playerName;
    @FXML
    private JFXButton proceed;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onActions();
        setImages();
        selectBiome.getItems().addAll("Default", "Beach", "Christmas");

    }

    private void onActions() {
        proceed.setOnAction(e -> {
            try {
                StageFunctions.changeScene("\\src\\resources\\fxml\\main_menu.fxml", "Game Screen");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        settings.setOnAction(e -> {
            try {
                StageFunctions.openSettings("\\src\\resources\\fxml\\settings.fxml", "Settings");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        minimize.setOnAction(e -> StageFunctions.minimize());
        maximise.setOnAction(e -> StageFunctions.maximise());
        exit.setOnAction(e -> StageFunctions.exit());
    }

    private void setImages() {

    }

}
