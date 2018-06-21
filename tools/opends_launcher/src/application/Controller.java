package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.io.*;
import java.util.ResourceBundle;
import java.util.*;

public class Controller {

    private String opendsLoc = "startProperties.properties";

    @FXML
    private Button startButton;
    @FXML
    private TextField driverNameEntry;

    //no args constructor
    public Controller(){

    }

    @FXML
    private void launchOpenDS(ActionEvent e){
        String driverNameStr;
        String line;
        StringBuffer inputBuffer = null;
        BufferedReader propertiesBuffer = null;
        FileOutputStream fileOut = null;
        driverNameStr = driverNameEntry.getText();
        try {
            inputBuffer = new StringBuffer();
            FileReader propertiesFile = new FileReader(opendsLoc);
            propertiesBuffer = new BufferedReader(propertiesFile);

        } catch(FileNotFoundException f) {
            System.out.println("File not found, perhaps OpenDS-Launcher is not in OpenDS Root Directory.");
        }
        try {
            while ((line = propertiesBuffer.readLine()) != null){
                inputBuffer.append(line);
                inputBuffer.append("\n");
            }
        } catch(IOException i) {
            System.out.println("Unable to read file.");
        } catch(NullPointerException n) {
            n.printStackTrace();
            System.out.println("Unable to create buffers, out of memory.");
        }
        //Close file out.
        try {
            propertiesBuffer.close();
        } catch(IOException i){
            System.out.println("Unable to close file.");
        }
        //Check input
        //System.out.println(inputBuffer);
        //Now replace string
        String inputStr = inputBuffer.toString();
        inputStr = inputStr.replaceAll("drivername=.*\n", "drivername="+driverNameEntry.getText()+"\n");
        try {
            fileOut = new FileOutputStream(opendsLoc);
            fileOut.write(inputStr.getBytes());
            fileOut.close();
        } catch(FileNotFoundException f){
            System.out.println("File not found, perhaps OpenDS-Launcher is not in OpenDS Root Directory.");
        } catch(IOException i) {
            System.out.println("Unable to write new driver name to properties file.");
        }
        try {
            Runtime run = Runtime.getRuntime();
            Process openDS = run.exec("java -jar OpenDS.jar");
        } catch(IOException i){
            System.out.println("Unable to launch program");
        }
        System.exit(0);
    }

}
