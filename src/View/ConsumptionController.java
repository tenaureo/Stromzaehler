package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * @author Vator
 * @version 1.0
 * @since 2020-September-30
 */

public class ConsumptionController implements Initializable {

    @FXML private ImageView logo;
    @FXML private DatePicker anfang;
    @FXML private DatePicker ende;
    @FXML private LineChart<CategoryAxis, Number> chart;

    @FXML
    private void onExport(ActionEvent event) throws IOException {

        Parent saveUIParent = FXMLLoader.load(getClass().getResource("FXML/ExportUI.fxml"));
        Scene saveUIscene = new Scene(saveUIParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(saveUIscene);
        window.show();

    }

    @FXML
    private void onMeterReading(ActionEvent event) throws IOException {

        Parent meterReadingUIParent = FXMLLoader.load(getClass().getResource("FXML/MeterReadingUI.fxml"));
        Scene meterReadingUIscene = new Scene(meterReadingUIParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(meterReadingUIscene);
        window.show();
    }

    public Image getImage() throws FileNotFoundException {
        Image image = new Image(new FileInputStream("images/logo.PNG"));
        return image;
    }

    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            anfang.setValue(LOCAL_DATE("2019-03-11"));
            ende.setValue(LOCAL_DATE("2019-09-24"));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Zeit");
        NumberAxis yAxis = new NumberAxis(0,30000,100);
        yAxis.setLabel("Verbrauch");

        chart = new LineChart(xAxis,yAxis);
        chart.setTitle("Verbrauchsdiagramm");

        try {
            logo.setImage(getImage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}