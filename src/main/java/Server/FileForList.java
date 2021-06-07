package Server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class FileForList  {

    long len;
    String name;
    boolean DIR;


    public FileForList(File file) throws IOException {
        len = Files.size(file.toPath());
        name = file.getName();
        DIR = Files.isDirectory(file.toPath());

    }




}
