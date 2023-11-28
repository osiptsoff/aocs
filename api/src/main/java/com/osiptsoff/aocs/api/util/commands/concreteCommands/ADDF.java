package com.osiptsoff.aocs.api.util.commands.concreteCommands;

import com.osiptsoff.aocs.api.model.registers.Flag;
import com.osiptsoff.aocs.api.model.registers.Registers;
import com.osiptsoff.aocs.api.util.commands.*;

public class ADDF extends RegRegCommand {
    public ADDF(int R0, int R1, int R2) {
        super(0b11001, R0, R1, R2);
    }
    @Override
    public int serialize() {
        return serialize(R0, R1, R2, 0);
    }
    /**
     * <p>Addition of floating-point numbers.</p>
     * <p>Pseudocode: {@code <RF0> ← <RF1> + <RF2>}</p>
     * <p>Flags: Z, C, S, O: +; I, T, U: -</p>
     * <p>Opcode: 0011001</p>
     * @author Artem Ivanov
     */
    @Override
    public void execute(Registers registers) {
        float result = (float) registers.getFloatRegister(R1) + (float) registers.getFloatRegister(R2);
        registers.setFloatRegister(R0, result);

        registers.setFlag(Flag.ZeroResult, registers.getFloatRegister(R0) == 0);
        registers.setFlag(Flag.Carry, result > Integer.MAX_VALUE || result < Integer.MIN_VALUE);
        registers.setFlag(Flag.Sign, registers.getFloatRegister(R0) < 0);
        registers.setFlag(Flag.Overflow, ((registers.getFloatRegister(R1) > 0 && registers.getFloatRegister(R2) > 0 && registers.getFloatRegister(R0) < 0)
                || (registers.getFloatRegister(R1) < 0 && registers.getFloatRegister(R2) < 0 && registers.getFloatRegister(R0) > 0)));
    }
}
