package Controllers;

import Models.DBConnect;
import Models.MobilePhone;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TableOfPhonesViewController implements Initializable {

    @FXML private TableView<MobilePhone> tableView;
    @FXML private TableColumn<MobilePhone, String> makeColumn;
    @FXML private TableColumn<MobilePhone, String> modelColumn;
    @FXML private TableColumn<MobilePhone, String> osColumn;
    @FXML private TableColumn<MobilePhone, Double> screenSizeColumn;
    @FXML private TableColumn<MobilePhone, Double> memoryColumn;
    @FXML private TableColumn<MobilePhone, Double> frontCameraColumn;
    @FXML private TableColumn<MobilePhone, Double> rearCameraColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //configure the TableView and TableColumn elements
        makeColumn.setCellValueFactory(new PropertyValueFactory<MobilePhone, String>("make"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<MobilePhone, String>("model"));
        osColumn.setCellValueFactory(new PropertyValueFactory<MobilePhone, String>("model"));
        screenSizeColumn.setCellValueFactory(new PropertyValueFactory<MobilePhone, Double>("screenSize"));
        memoryColumn.setCellValueFactory(new PropertyValueFactory<MobilePhone, Double>("memory"));
        frontCameraColumn.setCellValueFactory(new PropertyValueFactory<MobilePhone, Double>("frontCameraRes"));
        rearCameraColumn.setCellValueFactory(new PropertyValueFactory<MobilePhone, Double>("rearCameraRes"));

        //to add items to the TableView object, use the method "getItems()" to have the
        //TableView return it's ObservableList, then add a new list to it
        try {
            tableView.getItems().addAll(DBConnect.getPhones());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void selectPhoneFromTableButtonPushed(ActionEvent event) throws IOException {
        MobilePhone phone = tableView.getSelectionModel().getSelectedItem();
        if (phone != null)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../Views/EditPhoneView.fxml"));
            Parent root = loader.load();

            Scene tableViewScene = new Scene(root);

            //access the controller and call a method
            EditPhoneViewController controller = loader.getController();
            controller.loadPhone(phone);

            //This line gets the Stage information
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setScene(tableViewScene);
            stage.show();
        }
    }
}
