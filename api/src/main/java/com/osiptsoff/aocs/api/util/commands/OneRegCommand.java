package com.osiptsoff.aocs.api.util.commands;
import com.osiptsoff.aocs.api.model.registers.*;

public abstract class OneRegCommand extends Command {
    protected int R0;
    protected OneRegCommand(int opcode, int R0) {
        super(opcode);
        this.R0 = R0;
    }
    public abstract void execute(Registers registers);
}