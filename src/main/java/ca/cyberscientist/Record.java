package ca.cyberscientist.Sujamma;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.io.Serializable;

import static java.lang.Integer.toHexString;

public class Record implements Serializable {
    public ArrayList<Subrecord> subrecords;
    public Flag[] flags = new Flag[2];
    
    private String name;

    private int size;
    private long offset;

    public Record(String name,
                  long offset,
                  int size,
                  int flag0,
                  int flag1) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.name = name;
        this.size = size;
        this.offset = offset;
        this.flags[0] = enumerateFlags(flag0);
        this.flags[1] = enumerateFlags(flag1);

    }
    
    private Flag enumerateFlags(int flag) {
    	return switch (toHexString(flag)) {
            case "2000": Flag.BLOCKED; break;
            case "400": Flag.PERSISTENT; break;
            case "0": Flag.UNKNOWN; break;
            default:
                throw new IllegalStateException("Unexpected TES3RecordFlag integer value from stream: " + toHexString(flagAB));
        };
    }

    public Record();

	public int getSize() {
		if (null.equals(this.size)) {
			int sum;
			subrecords.forEach(x -> sum += x.getSize());
			return sum;
		} else {
			return this.size;
		}
	}
    
    public int getOffset() {
    	return this.offset;
    }
    
    private void writeObject(java.io.ObjectOutputStream out)
     throws IOException {
     	 ByteArrayOutputStream temporaryStream = new ByteArrayOutputStream();
     	 subrecords.forEach(s -> s.writeObject(temporaryStream));
     	 
     	 out.writeBytes(this.name);
     	 out.writeInt(temporaryStream.size());
     	 out.writeInt((int) this.flags[0]);
     	 out.writeInt((int) this.flags[1]);
     	 temporaryStream.writeTo(out);
     }
     
     private void readObject(java.io.ObjectInputStream in)
     throws IOException, ClassNotFoundException {
     	 byte[] nameBytes = new byte[4];
     	 in.readFully(nameBytes);
     	 this.name = new String(nameBytes, "UTF-8");
     	 this.size = in.readInt();
     	 this.flags[0] = Flag(in.readInt());
     	 this.flags[1] = Flag(in.readInt());
     	 
     	 this.subrecords = new ArrayList<Subrecord>();
     	 
     	 int bytesAvailable = this.size;
     	 // As long as there is at least as many bytes as can specify another subrecord header there should be a sufficient amount of bytes to create a subrecord (at least an empty one).
     	 while (bytesAvailable >= Subrecord.HEADER_SIZE_BYTES) {
     	 	 this.subrecords.add(Subrecord.readObject(in)); // throws if insufficientBytesAvailable
     	 	 bytesAvailable -= Subrecord.HEADER_SIZE_BYTES;
     	 	 bytesAvailable -= this.subrecords.last().getSize();
     	 }
     }
     
     private void readObjectNoData()
     throws ObjectStreamException {
     }
}
