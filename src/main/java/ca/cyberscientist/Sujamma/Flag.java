package ca.cyberscientist.Sujamma;

public enum Flag {
    BLOCKED (0x2000),
    PERSISTENT (0x0400),
    UNKNOWN (0x0000);

    Flag(int flag) {};
}
