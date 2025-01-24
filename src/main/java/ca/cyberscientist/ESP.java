package ca.cyberscientist;

import java.io.IOException;

public class ESP extends File {
    private int fileSize;

    public ESP(String pathname) throws IOException {
        super(pathname);
    }

    public int getFileSize() {
        return fileSize;
    }
}