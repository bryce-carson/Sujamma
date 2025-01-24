package ca.cyberscientist;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class HEDR extends Subrecord {
    float version;
    int flags;
    String author;
    String description;
    int records;

    public HEDR(DataInputStream inputStream) throws IOException {
        if (inputStream.available() != 300) {
            throw new IOException("Data input stream for HEDR is not 300 bytes!");
        } else {
            version = inputStream.readFloat();
            flags = inputStream.readInt();
            author = Arrays.toString(inputStream.readNBytes(32));
            description = Arrays.toString(inputStream.readNBytes(256));
            records = inputStream.readInt();
        }
    }
}
