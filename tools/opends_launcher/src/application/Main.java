package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/LauncherWindow.fxml"));

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = loader.load();
        Controller controller = loader.getController();
        primaryStage.setTitle("OpenDS-Launcher");
        primaryStage.setScene(new Scene(root, 500, 290));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
