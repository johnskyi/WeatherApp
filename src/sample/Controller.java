package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import netscape.javascript.JSObject;
import org.json.JSONObject;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField city;

    @FXML
    private Button getData;

    @FXML
    private Text tempInfo;

    @FXML
    private Text pressure;

    @FXML
    void initialize() {
        getData.setOnAction(actionEvent -> {
            String getUserCity = city.getText().trim();
            if(!getUserCity.equals("")) {
                String outPut = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=82b797b6ebc625032318e16f1b42c0116&units=metric");
                System.out.println(outPut);
                if (!outPut.isEmpty()) {
                    JSONObject obj = new JSONObject(outPut);
                    tempInfo.setText("Температура:" + obj.getJSONObject("main").getDouble("temp"));
                    pressure.setText("Давление:" + obj.getJSONObject("main").getDouble("pressure"));
                }
            }
        });

    }
    private static String getUrlContent(String urlAdress){
        StringBuffer content = new StringBuffer();
        try{
            URL url = new URL(urlAdress);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine())!= null){
                content.append(line +"\n");
            }
            bufferedReader.close();

        } catch (Exception e){
            System.out.println("Такой город не существует!");

        }
        return content.toString();
    }
}
