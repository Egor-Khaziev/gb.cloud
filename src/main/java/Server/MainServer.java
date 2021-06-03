package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainServer {

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(8189);
        log.debug("Server started");
        log.debug("Server waiting commands");
        while (true) {
            try {
                Socket socket = server.accept();
                log.debug("Client accepted");
                ServerHandler handler = new ServerHandler(socket);
                new Thread(handler).start();
            } catch (Exception e) {
                log.error("Connection was broken");
            }
        }
    }
}
