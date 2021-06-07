package Client;


import lombok.Builder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;


@Builder
public class ServerFileInfo {


    public enum FileType {
        FILE("F"), DIRECTORY("D");

        private String name;

        public String getName() {
            return name;
        }

        FileType(String name) {
            this.name = name;
        }
    }


    boolean DIR;
    private String name;
    private FileType type;
    private long len;
//    private LocalDateTime lastModified;
    private static List<FileForList> fileList;

    public static void setList(List<FileForList> list) {
        fileList = list;
    }


    public static List<FileForList> getList() {
        return fileList;
    }

    public String getFilename() {
        return name;
    }

    public FileType getType() {
        return type;
    }

    public long getSize() {
        return len;
    }


//    public LocalDateTime getLastModified() {
//        return lastModified;
//    }
//
//    public void setLastModified(LocalDateTime lastModified) {
//        this.lastModified = lastModified;
//    }


//    public ServerFileInfo(Path path) {
//        //            this.name = path.getFileName().toString();
////            this.len = Files.size(path);
//        this.type = Files.isDirectory(path) ? FileType.DIRECTORY : FileType.FILE;
//        if (this.type == FileType.DIRECTORY) {
//            this.len = -1L;
//        }
//        // this.lastModified = LocalDateTime.ofInstant(Files.getLastModifiedTime(path).toInstant(), ZoneOffset.ofHours(3));
//    }

    public ServerFileInfo(FileForList file) {

      this.name = file.name;
      this.len = file.len;
        this.type = file.DIR ? FileType.DIRECTORY : FileType.FILE;
//        this.type = Files.isDirectory(path) ? FileType.DIRECTORY : FileType.FILE;
        if (this.type == FileType.DIRECTORY) {
            this.len = -1L;
        }
//      this.lastModified = LocalDateTime.ofInstant(Files.getLastModifiedTime(path).toInstant(), ZoneOffset.ofHours(3));
    }
}
