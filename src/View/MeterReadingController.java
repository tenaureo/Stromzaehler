package View;

import Control.Processing;
import Model.ESL;
import Model.SDAT;
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
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
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
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author Vator AG
 * @version 1.0
 * @since 2020-September-30
 */

public class MeterReadingController implements Initializable {

    @FXML private ImageView logo;
    @FXML private CheckBox bezugh;
    @FXML private CheckBox bezugn;
    @FXML private CheckBox einspeisungh;
    @FXML private CheckBox einspeisungn;
    @FXML private DatePicker anfang;
    @FXML private DatePicker ende;
    @FXML private CategoryAxis x;
    @FXML private  NumberAxis y;
    @FXML private LineChart<?, ?> chart;

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
        return new Image(new FileInputStream("files/images/logo.PNG"));
    }

    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, formatter);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Map<String, ESL> eslMap = Processing.PROCESSING.getEslMap();
        bezugh.setSelected(true);
        bezugn.setSelected(true);
        einspeisungh.setSelected(true);
        einspeisungn.setSelected(true);

        try {
            anfang.setValue(LOCAL_DATE("2018-12-05"));
            ende.setValue(LOCAL_DATE("2019-09-01"));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        XYChart.Series series = new XYChart.Series();
        series.setName("Zählerstand");

        for (ESL e : eslMap.values()) {
            series.getData().add(new XYChart.Data(e.getTimeID().toString(), e.getValue("high")));
        }

        x.setLabel("Zeit");
        y.setLabel("Zählerstand");

        chart.getData().addAll(series);
        chart.setTitle("Zählerstanddiagramm");

        try {
            logo.setImage(getImage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}