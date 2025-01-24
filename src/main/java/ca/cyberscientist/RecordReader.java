package ca.cyberscientist;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class RecordReader {
    private final RandomAccessFile randomAccessFile;
    private final int RECORD_HEADER_BYTE_LENGTH = 32;

    RecordReader(File morrowindFile) throws IOException {
        randomAccessFile = new RandomAccessFile(morrowindFile, "r");
    }

    /**
     * @return A subclass of Record according to the type of record specified in the record header.
     * @throws IOException when bytes cannot be properly read.
     * @throws ClassNotFoundException when no subclass of Record according to the specified type is found.
     */
    public Record readRecordHeader() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        byte[] type = new byte[4];
        int size;

        long offset = randomAccessFile.getFilePointer();

        // RECORD_HEADER_BYTE_LENGTH
        randomAccessFile.read(type);
        size = randomAccessFile.readInt();
        int flagAB = randomAccessFile.readInt();
        int flagCD = randomAccessFile.readInt();

        return new Record(Arrays.toString(type), offset, size, flagAB, flagCD);
    }

    public void readSubrecords(Record record) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        byte[] buffer = new byte[record.getSize()];
        randomAccessFile.seek(record.getOffset());
        randomAccessFile.skipBytes(RECORD_HEADER_BYTE_LENGTH); // skip the header
        randomAccessFile.readFully(buffer);

        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(buffer));
        while (inputStream.available() > 0) {
            byte[] type = new byte[4];
            int readBytes = inputStream.read(type);
            if (readBytes == -1) {
                throw new RuntimeException("Couldn't read bytes of subrecord.");
            }
            int size = inputStream.readInt();
            Subrecord subrecord;
            subrecord = new Subrecord(Arrays.toString(type),
                    new DataInputStream(new ByteArrayInputStream(new byte[size])));
            record.subrecords.add(subrecord);
        }
    }
}