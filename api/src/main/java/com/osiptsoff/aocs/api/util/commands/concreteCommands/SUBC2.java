package com.osiptsoff.aocs.api.util.commands.concreteCommands;

import com.osiptsoff.aocs.api.model.registers.Flag;
import com.osiptsoff.aocs.api.model.registers.Registers;
import com.osiptsoff.aocs.api.util.commands.*;

public class SUBC2 extends RegConstCommand {
    public SUBC2(int R0, int R1, int DISP) {
        super(0b1001, R0, R1, DISP);
    }
    @Override
    public int serialize() {
        return serialize(R0, R1, DISP);
    }
    /**
     * <p>Subtraction of a signed integer from a constant.</p>
     * <p>Pseudocode: {@code <R0> ← const - <R1>}</p>
     * <p>Flags: Z, C, S, O: +; I, T, U: -</p>
     * <p>Opcode: 0001001</p>
     * @author Artem Ivanov
     */
    @Override
    public void execute(Registers registers) {
        long result = (long) registers.getIntRegister(DISP) - (long) registers.getIntRegister(R1);
        registers.setIntRegister(R0, (int) result);

        registers.setFlag(Flag.ZeroResult, registers.getIntRegister(R0) == 0);
        registers.setFlag(Flag.Carry, result > Integer.MAX_VALUE || result < Integer.MIN_VALUE);
        registers.setFlag(Flag.Sign, registers.getIntRegister(R0) < 0);
        registers.setFlag(Flag.Overflow, ((registers.getIntRegister(R1) > 0 && registers.getIntRegister(DISP) > 0 && registers.getIntRegister(R0) < 0)
                || (registers.getIntRegister(R1) < 0 && registers.getIntRegister(DISP) < 0 && registers.getIntRegister(R0) > 0)));
    }
}
