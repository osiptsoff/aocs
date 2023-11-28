package com.osiptsoff.aocs.api.util.commands.concreteCommands;

import com.osiptsoff.aocs.api.model.registers.Flag;
import com.osiptsoff.aocs.api.model.registers.Registers;
import com.osiptsoff.aocs.api.util.commands.*;

public class CMPF extends RegRegCommand {
    public CMPF(int R0, int R1, int R2) {
        super(0b11111, R0, R1, R2);
    }
    @Override
    public int serialize() {
        return serialize(R0, R1, R2, 0);
    }
    /**
     * <p>Comparison of floating-point numbers.</p>
     * <p>Pseudocode: {@code <RF0> ← <RF1> > <RF2> ? 1 : 0}</p>
     * <p>Flags: Z, C, S, O: +; I, T, U: -</p>
     * <p>Opcode: 0011111</p>
     * @author Artem Ivanov
     */
    @Override
    public void execute(Registers registers) {
        if ((long) registers.getFloatRegister(R1) > (long) registers.getFloatRegister(R2)) {
            registers.setIntRegister(R0, (int) 1);
        } else {
            registers.setIntRegister(R0, (int) 0);
        }

        registers.setFlag(Flag.ZeroResult, registers.getIntRegister(R0) == 0);
        registers.setFlag(Flag.Carry, registers.getIntRegister(R0) > Integer.MAX_VALUE || registers.getIntRegister(R0) < Integer.MIN_VALUE);
        registers.setFlag(Flag.Sign, registers.getIntRegister(R0) < 0);
    }
}
