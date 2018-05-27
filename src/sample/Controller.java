package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.input.InputMethodTextRun;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Controller {

    @FXML
    public Button start;

    @FXML
    public Spinner<Object> port;

    @FXML
    public TextArea area;



    public void initialize() {
     Thread server = new Thread(() -> {
         try {
             ServerSocket serverSocket = new ServerSocket(Integer.valueOf(port.getEditor().getText()));
                Socket s = serverSocket.accept();
                area.appendText("Connected to: "+s.getInetAddress().toString()+"\n");
             InputStream in = s.getInputStream();
             Scanner sc = new Scanner(in);
             while(!s.isClosed()){
                 if(sc.hasNext()) area.appendText(sc.next());
             }
             area.appendText("Disconnected!");
         } catch (IOException e) {
             e.printStackTrace();
         }
     });

     start.setOnMouseClicked(event -> server.start());
     System.out.println(area + " "+port);
    }

}
