package com.osiptsoff.aocs.api.util.commands;
import com.osiptsoff.aocs.api.model.registers.*;
import com.osiptsoff.aocs.api.memory.*;

public abstract class RegConstCommand extends Command {
    protected int R0;
    protected int R1;
    protected int DISP;
    protected RegConstCommand(int opcode, int R0, int R1, int DISP) {
        super(opcode);
        this.R0 = R0;
        this.R1 = R1;
        this.DISP = DISP;
    }
    public abstract void execute(Registers registers);
}
