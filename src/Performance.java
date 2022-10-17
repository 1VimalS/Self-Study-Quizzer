//Graphing class
import javafx.application.Application; 
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis; 
import javafx.scene.chart.ScatterChart; 
import javafx.scene.chart.XYChart; 
import javafx.stage.Stage;
public class Performance extends Application {
    private NumberAxis xAxis = new NumberAxis(0, GUI.getDataIndices().size(), 1);
    private NumberAxis yAxis = new NumberAxis(0, 100, 20);
    private ScatterChart<Number, Number> scatterChart = new ScatterChart<Number, Number>(xAxis, yAxis);
    private XYChart.Series series = new XYChart.Series(); 
    private Group root = new Group(scatterChart); 
    private Scene scene = new Scene(root, 600, 400);
    public void start(Stage stage) throws Exception { 
        xAxis.setLabel("Attempts"); 
        yAxis.setLabel("Percentage Correct");
        for (int i = 1; i <= GUI.getDataIndices().size(); i++) { 
            series.getData().add(new XYChart.Data(i, GUI.getData().get(i))); 
        }
        scatterChart.getData().addAll(series);
        stage.setTitle("Performance on Practice Tests"); stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) { 
        Application.launch(args);
    } 
}