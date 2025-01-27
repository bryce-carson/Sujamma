package ca.cyberscientist.Sujamma;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class representing the TES3 record type of TES III: Morrowind ESx files.
 */
public class TES3 extends Record {
    HEDR hedr = (HEDR) subrecords.getFirst();

    public TES3() {
        super(); // NOP
    }
}