package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text pogodnik;

    @FXML
    private TextField city;

    @FXML
    private Button button;

    @FXML
    private Text info;

    @FXML
    private Text t;

    @FXML
    private Text max;

    @FXML
    private Text min;
    private static String getUrlContent(String urlAdress) {
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    @FXML
    void initialize() {
        button.setOnAction(event -> {
            String getUserCity = city.getText().trim();
            if (!getUserCity.equals("")) {
                String outPut = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&units=metric&appid=5dc29bff85e4c8dad92b00df20df4a76");
                System.out.println(outPut);
                if (!outPut.isEmpty()) {
                    JSONObject obj = new JSONObject(outPut);
                    t.setText("Температура: " + obj.getJSONObject("main").getDouble("temp"));
                    max.setText("Максимум: " + obj.getJSONObject("main").getDouble("temp_max"));
                    min.setText("Минимум: " + obj.getJSONObject("main").getDouble("temp_min"));
                }
            }
        });
    }
}

