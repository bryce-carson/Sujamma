package ca.cyberscientist;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public abstract class File extends java.io.File {
    public ArrayList<Record> records;
    public RecordReader recordReader;
    private int bytes;

    public File(String pathname) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(pathname);
        this.recordReader = new RecordReader(this);
        recordReader.readRecordHeader();
    }

    public int getSize() {
        // The size of a file is a filesystem attribute.
    }
}
