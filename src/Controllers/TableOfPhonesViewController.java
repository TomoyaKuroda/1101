package Controllers;

import Models.DBConnect;
import Models.MobilePhone;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


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
}
