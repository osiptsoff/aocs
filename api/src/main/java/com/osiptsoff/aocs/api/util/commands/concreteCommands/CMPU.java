package com.osiptsoff.aocs.api.util.commands.concreteCommands;

import com.osiptsoff.aocs.api.model.registers.Flag;
import com.osiptsoff.aocs.api.model.registers.Registers;
import com.osiptsoff.aocs.api.util.commands.*;

public class CMPU extends RegRegCommand {
    public CMPU(int R0, int R1, int R2) {
        super(0b10110, R0, R1, R2);
    }
    @Override
    public int serialize() {
        return serialize(R0, R1, R2, 0);
    }
    /**
     * <p>Comparison of unsigned integers.</p>
     * <p>Pseudocode: {@code <R0> ← <R1> > <R2> ? 1 : 0}</p>
     * <p>Flags: Z, C, S, O: +; I, T, U: -</p>
     * <p>Opcode: 0010110</p>
     * @author Artem Ivanov
     */
    @Override
    public void execute(Registers registers) {
        if (Integer.toUnsignedLong(registers.getIntRegister(R1)) > Integer.toUnsignedLong(registers.getIntRegister(R2))) {
            registers.setIntRegister(R0, (int) 1);
        } else {
            registers.setIntRegister(R0, (int) 0);
        }

        registers.setFlag(Flag.ZeroResult, registers.getIntRegister(R0) == 0);
        registers.setFlag(Flag.Carry, registers.getIntRegister(R0) > Integer.MAX_VALUE || registers.getIntRegister(R0) < Integer.MIN_VALUE);
        registers.setFlag(Flag.Sign, registers.getIntRegister(R0) < 0);
    }
}
