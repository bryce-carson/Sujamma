package ca.cyberscientist;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static java.lang.Integer.toHexString;

public class Record {
    public ArrayList<Subrecord> subrecords;
    public TES3RecordFlag flagAB;
    public TES3RecordFlag flagCD;

    private int size;
    private long offset;

    public Record(String recordType,
                  long offset,
                  int size,
                  int flagAB,
                  int flagCD) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.size = size;
        this.offset = offset;
        switch (toHexString(flagAB)) {
            case "2000": this.flagAB = TES3RecordFlag.BLOCKED; break;
            case "400": this.flagAB = TES3RecordFlag.PERSISTENT; break;
            default:
                throw new IllegalStateException("Unexpected TES3RecordFlag value from stream: " + toHexString(flagAB));
        }
        switch (toHexString(flagCD)) {
            case "2000": this.flagCD = TES3RecordFlag.BLOCKED; break;
            case "400": this.flagCD = TES3RecordFlag.PERSISTENT; break;
            default:
                throw new IllegalStateException("Unexpected TES3RecordFlag value from stream: " + toHexString(flagCD));
        }
        Class.forName(recordType).getConstructor().newInstance();
    }

    public Record() {
    }

    public int getSize() {
        return size;
    }

    public long getOffset() {
        return offset;
    }
}