package com.osiptsoff.aocs.api.util.commands.concreteCommands;

import com.osiptsoff.aocs.api.model.registers.Flag;
import com.osiptsoff.aocs.api.model.registers.Registers;
import com.osiptsoff.aocs.api.util.commands.*;

public class DIV extends RegRegCommand {
    public DIV(int R0, int R1, int R2) {
        super(0b1111, R0, R1, R2);
    }
    @Override
    public int serialize() {
        return serialize(R0, R1, R2, 0);
    }
    /**
     * <p>Division of signed integers.</p>
     * <p>Pseudocode: {@code <R0> ← <R1> / <R2>}</p>
     * <p>Flags: Z, C, S: +; O, I, T, U: -</p>
     * <p>Opcode: 0001111</p>
     * @author Artem Ivanov
     */
    @Override
    public void execute(Registers registers) {
        // Check for division by zero
        if (registers.getIntRegister(R2) == 0) {
            // Set overflow flag and exit the command
            registers.setFlag(Flag.Overflow, true);
            return;
        }
        long result = (long) registers.getIntRegister(R1) / (long) registers.getIntRegister(R2);
        registers.setIntRegister(R0, (int) result);

        registers.setFlag(Flag.ZeroResult, registers.getIntRegister(R0) == 0);
        registers.setFlag(Flag.Carry, result > Integer.MAX_VALUE || result < Integer.MIN_VALUE);
        registers.setFlag(Flag.Sign, registers.getIntRegister(R0) < 0);
    }
}
