package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.eclipse.jdt.internal.compiler.flow.FinallyFlowContext;

import javax.imageio.IIOException;
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

        } catch(FileNotFoundException fe1) {
            System.out.println("File not found, perhaps OpenDS-Launcher is not in OpenDS Root Directory.");
            System.out.println("Creating a new properties file anyway.");
            createFile();
            try {
                FileReader propertiesFile = new FileReader(opendsLoc);
                propertiesBuffer = new BufferedReader(propertiesFile);
            } catch (FileNotFoundException fe2){
                fe2.printStackTrace();
            }
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
            System.out.println("Launching openDS.");
            Runtime run = Runtime.getRuntime();
            Process openDS = run.exec("java -jar ./IoT-OpenDS.jar");
            InputStream in = openDS.getInputStream();
            InputStream err = openDS.getErrorStream();
        } catch(IOException i){
            System.out.println("Unable to launch program.");
        }
        // Then retrieve output

        System.exit(0);
    }

    private void createFile(){
        File pFile = new File("startProperties.properties");
        FileWriter writer = null;
        //Create the file
        try {
            pFile.createNewFile();
            System.out.println("Property file created!");
        }catch (IOException i){
            System.out.println("File already exists.");
        }
         //Write Content
        try {
            writer = new FileWriter(pFile);
            writer.write("width=1920\n");
            writer.write("height=1080\n");
            writer.write("colordepth=24\n");
            writer.write("fullscreen=true\n");
            writer.write("refreshrate=60\n");
            writer.write("vsync=true\n");
            writer.write("showsettingsscreen=false\n");
            writer.write("drivingtask=assets/DrivingTasks/Projects/NormalReactionTest/reactionTest-fixed.xml\n");
            writer.write("drivername=blank\n");
            writer.write("usejoysticks=true\n");
            writer.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

}
