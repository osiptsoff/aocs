package com.osiptsoff.aocs.api.util.commands;

public abstract class Command {
    protected final int opcode;
    protected Command(int opcode) {
        this.opcode = opcode; 
    }
    protected int serialize(int R0, int R1, int R2, int DISP) {
        return (opcode << 25 | R0 << 19 | R1 << 13 | R2 << 7 | DISP);
    }
    protected int serialize(int R0, int R1, int DISP) {
        return (opcode << 25 | R0 << 19 |  R1 << 13 | DISP);
    }
    protected int serialize(int R0, int DISP) {
        return (opcode << 25 | R0 << 19 | DISP);
    }
    protected int serialize(int DISP) {
        return (opcode << 25 | DISP);
    }
    abstract public int serialize();
}
