package Server;

import Interfaces.Message;
import Interfaces.MessageList;
import Interfaces.MessageType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class ListMessage implements Message, MessageList {

    List<FileForList> fileForList;

    Path path;


    public ListMessage(String serverDir) throws IOException {

        log.debug("формирование листа");
        File[] dir = new File(serverDir).listFiles();
        fileForList = new ArrayList<>();

        path = new File(serverDir).toPath();

        if (new File(serverDir).mkdir()) {log.debug("Сервер директория создана");}
            else {log.debug("Сервер директория существует");}

        for (File file : dir) {
            fileForList.add(new FileForList(file));
            log.debug("эллемент добавлен в лист");
        }
            log.debug("лист готов");


    }

    @Override
    public MessageType getType() {
        return MessageType.LIST;
    }

    @Override
    public List getList() {
        return fileForList;
    }

    @Override
    public Path getPath() {
        return path;
    }
}
