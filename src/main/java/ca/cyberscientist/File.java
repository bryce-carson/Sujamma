package ca.cyberscientist;

import java.util.ArrayList;

public abstract class File extends java.io.File {
    public ArrayList<Record> records;
    public RecordReader recordReader;
    public int bytes;

    public File(String pathname) {
        super(pathname);
    }
}