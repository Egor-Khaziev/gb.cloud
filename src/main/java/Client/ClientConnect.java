package Client;

import Interfaces.Message;
import Interfaces.MessageList;

import javafx.application.Platform;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;


@Slf4j
public class ClientConnect  {

    private Socket socket;


    private static ObjectInputStream is;
    private static ObjectOutputStream os;

    public static ObjectOutputStream getOs() {
        return os;
    }

    static ClientConnect clientConnect;

    public static ObjectInputStream getIs() {
        return is;
    }

    private ClientConnect(String localhost, int port) {
        try {
            socket = new Socket(localhost, port);
        } catch (IOException e) {
            log.error("socket incorrect");
        }
        initialize();
    }

    private ClientConnect() {
        try {
            socket = new Socket("localhost", 8189);
        } catch (IOException e) {
            log.error("socket incorrect");
        }
        initialize();
    }

    public static ClientConnect getClientConnect(String localhost, int port) {
        if (clientConnect == null){
            clientConnect = new ClientConnect(localhost, port);
        }
        return clientConnect;
    }

    public static ClientConnect getClientConnect() {
        if (clientConnect == null){
            clientConnect = new ClientConnect();
        }
        return clientConnect;
    }


    @SneakyThrows
    public void initialize() {

        try {
            os = new ObjectOutputStream(socket.getOutputStream());
            is = new ObjectInputStream(socket.getInputStream());
            log.debug("соединение установлено");
        } catch (IOException e) {
            e.printStackTrace();
        }

        os.writeObject(new ListRequest());
        log.debug("отправка листреквест");
        os.flush();


        Thread readThread = new Thread(() -> {
            try {
                while (true) {
                    Message message = (Message) is.readObject();
                    log.debug("получено сообщение");
                    switch (message.getType()) {
                        case LIST:
                            log.debug("сообщение - лист");

                            List<FileForList> fileList = ((MessageList) message).getList();
                            ServerFileInfo.setList(fileList);
                            for (FileForList file: fileList
                                 ) {
                                System.out.println(file.toString());
                            }
                            Platform.runLater(() -> {
                                // соеденить с ServerPanel в ControllerMainPanel
//                            listView.getItems().clear();
//                            listView.getItems()
//                                    .addAll(list.getFiles());
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


    }

}



