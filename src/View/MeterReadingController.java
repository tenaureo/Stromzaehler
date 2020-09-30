package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Vator
 * @version 1.0
 * @since 2020-September-30
 */

public class MeterReadingController implements Initializable {

    @FXML
    private ImageView logo;

    NumberAxis xAxis = new NumberAxis();
    NumberAxis yAxis = new NumberAxis();

    @FXML private LineChart<Number, Number> chart = new LineChart<Number, Number>(xAxis,yAxis);

    @FXML
    private void onExport(ActionEvent event) throws IOException {

        Parent exportUIParent = FXMLLoader.load(getClass().getResource("FXML/ExportUI.fxml"));
        Scene exportUIscene = new Scene(exportUIParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(exportUIscene);
        window.show();

    }

    @FXML
    private void onConsumption(ActionEvent event) throws IOException {

        Parent consumptionParent = FXMLLoader.load(getClass().getResource("FXML/ConsumptionUI.fxml"));
        Scene consumptionScene = new Scene(consumptionParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(consumptionScene);
        window.show();
    }

    public Image getImage() throws FileNotFoundException {
        Image image = new Image(new FileInputStream("images/logo.PNG"));
        return image;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        chart.setTitle("Title");

        try {
            logo.setImage(getImage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}