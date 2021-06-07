package Server;

import Interfaces.Message;
import Interfaces.MessageType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Data
public class FileObject implements Message {

    private long len;
    private String name;
    private byte[] data;

    public FileObject(Path path) throws IOException {
        len = Files.size(path);
        name = path.getFileName().toString();
        data = Files.readAllBytes(path);
        log.debug("создание фалйа объекта");
    }

    @Override
    public MessageType getType() {
        return MessageType.FILE;
    }
}
