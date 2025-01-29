package ca.cyberscientist.Sujamma;

import java.io.*;
import java.util.ArrayList;

public class Subrecord {
	public static final int HEADER_SIZE_BYTES = 8;
	
	public String name;
	public int size;
	
	private ArrayList<Object> data;

	public Subrecord() {}

    public int getSize() {
        return this.size;
    }
    
    private void writeObject(java.io.ObjectOutputStream out)
     throws IOException {
     	 ByteArrayOutputStream temporaryStream = new ByteArrayOutputStream();
     	 data.forEach(d -> d.writeObject(temporaryStream));
     	 
     	 out.writeBytes(this.name); // low bits only
     	 out.writeInt(temporaryStream.size());
     	 temporaryStream.writeTo(out);
     }
     
     private static void readObject(java.io.ObjectInputStream in)
     throws IOException, ClassNotFoundException {
     	 byte[] nameBytes = new byte[4];
     	 in.readFully(nameBytes);
     	 this.name = new String(nameBytes, "UTF-8");
     	 this.size = in.readInt();
     	 
     	 this.data = new ArrayList<Object>();
     	 
     	 int bytesAvailable = this.size;
     	 // As long as there are bytes available there may be an Object.
     	 while (bytesAvailable > 0) {
     	 	 FileChannel channel = in.getChannel();
     	 	 long positition = channel.position()
     	 	 this.data.add(Object.readObject(in));
     	 	 bytesAvailable -= (int) (position - channel.position());
     	 }
     }
}
