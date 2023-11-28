package com.osiptsoff.aocs.api.util.commands;
import com.osiptsoff.aocs.api.model.registers.*;

public abstract class RegRegCommand extends Command {
    protected int R0;
    protected int R1;
    protected int R2;
    protected RegRegCommand(int opcode, int R0, int R1, int R2) {
        super(opcode);
        this.R0 = R0;
        this.R1 = R1;
        this.R2 = R2;
    }
    public abstract void execute(Registers registers);
}