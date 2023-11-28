package com.osiptsoff.aocs.api.util.commands.concreteCommands;

import com.osiptsoff.aocs.api.model.registers.Flag;
import com.osiptsoff.aocs.api.model.registers.Registers;
import com.osiptsoff.aocs.api.util.commands.*;

public class ADD extends RegRegCommand {
    public ADD(int R0, int R1, int R2) {
        super(0b1, R0, R1, R2);
    }
    @Override
    public int serialize() {
        return serialize(R0, R1, R2, 0);
    }
    /**
     * <p>Addition of signed integers.</p>
     * <p>Pseudocode: {@code <R0> ← <R1> + <R2>}</p>
     * <p>Flags: Z, C, S, O: +; I, T, U: -</p> 
     * <p>Opcode: 0000001</p>
     * @author Artem Ivanov
     */
    @Override
    public void execute(Registers registers) {
        long result = (long) registers.getIntRegister(R1) + (long) registers.getIntRegister(R2);
        registers.setIntRegister(R0, (int) result);

        registers.setFlag(Flag.ZeroResult, registers.getIntRegister(R0) == 0);
        registers.setFlag(Flag.Carry, result > Integer.MAX_VALUE || result < Integer.MIN_VALUE);
        registers.setFlag(Flag.Sign, registers.getIntRegister(R0) < 0);
        registers.setFlag(Flag.Overflow, ((registers.getIntRegister(R1) > 0 && registers.getIntRegister(R2) > 0 && registers.getIntRegister(R0) < 0)
                || (registers.getIntRegister(R1) < 0 && registers.getIntRegister(R2) < 0 && registers.getIntRegister(R0) > 0)));
    }
}
