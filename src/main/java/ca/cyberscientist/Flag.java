package ca.cyberscientist.Sujamma;

public enum Flag {
    BLOCKED (0x0000_2000),
    PERSISTENT (0x0000_0400),
    UNKNOWN (0x0000_0000);

    Flag(int flag) {};
}
