package Client;

import lombok.Builder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Builder
public class FileForList {

    public long len;
    public String name;
    public boolean DIR;

}
