package ca.cyberscientist;

public enum TES3RecordFlag {
    BLOCKED (0x0000_2000),
    PERSISTENT (0x0000_0400),
    UNKNOWN (0x0000_0000);

    TES3RecordFlag(int flag) {
    }
}
