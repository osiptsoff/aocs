package com.osiptsoff.aocs.api.util.commands;
import com.osiptsoff.aocs.api.model.registers.*;
import com.osiptsoff.aocs.api.memory.*;

public abstract class RegMemCommand extends Command {
    protected int R0;
    protected int R1;
    protected RegMemCommand(int opcode, int R0, int R1) {
        super(opcode);
        this.R0 = R0;
        this.R1 = R1;
    }
    public abstract void execute(Registers registers, Memory memory, String id);
}
