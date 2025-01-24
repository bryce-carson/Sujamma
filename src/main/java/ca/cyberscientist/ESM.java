package ca.cyberscientist;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ESM extends File {
    public ESM(String pathname) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(pathname);
    }
}