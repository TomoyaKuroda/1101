package Controllers;

import Models.DBConnect;
import Models.MobilePhone;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditPhoneViewController implements Initializable {

    @FXML
    private ChoiceBox<String> makeChoiceBox;

    @FXML
    private TextField modelTextField;

    @FXML
    private ChoiceBox<String> osChoiceBox;



    private MobilePhone activePhone;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * This method receives a MobilPhone object and updates the display with it
     * @param phone
     */
    public void loadPhone(MobilePhone phone)
    {
        activePhone = phone;
        updateView();
    }

    public void updateView()
    {
        
    }
}
