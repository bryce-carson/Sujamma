package ca.cyberscientist;

import java.io.IOException;

public class ESM extends File {
    public ESM(String pathname) throws IOException {
        super(pathname);
    }

    RecordReader reader = new RecordReader(this);
}