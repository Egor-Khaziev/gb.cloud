package Client;

import Interfaces.Message;
import Server.ListMessage;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import Client.Panel.*;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;


@Slf4j
public class ClientConnect implements Initializable  {

    private Socket socket;


    private static ObjectInputStream is;
    private static ObjectOutputStream os;

    public static ObjectOutputStream getOs() {
        return os;
    }

    public static ObjectInputStream getIs() {
        return is;
    }

    public ClientConnect(String localhost, int port) {
        try {
            socket = new Socket(localhost, port);
        } catch (IOException e) {
            log.error("socket incorrect");
        }
    }
    public ClientConnect(){
        try {
            socket = new Socket("localhost", 8189);
        } catch (IOException e) {
            log.error("socket incorrect");
        }
    }


    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            os = new ObjectOutputStream(socket.getOutputStream());
            is = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        os.writeObject(new ListRequest());
        os.flush();
    }


    Thread readThread = new Thread(() -> {
        try {
            while (true) {
                Message message = (Message) is.readObject();
                switch (message.getType()) {
                    case LIST:
                        ListMessage list = (ListMessage) message;
                        Platform.runLater(() -> {
                            // соеденить с ServerPanel в ControllerMainPanel
                            listView.getItems().clear();
                            listView.getItems()
                                    .addAll(list.getFiles());
                        });
                        break;
                }

            }
        } catch (Exception e) {
            log.error("e=", e);
        }
    });
            readThread.setDaemon(true);
            readThread.start();
} catch (Exception e) {
        log.error("e=", e);
        }
}


