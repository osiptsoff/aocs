package com.osiptsoff.aocs.api.util.commands.concreteCommands;

import com.osiptsoff.aocs.api.model.registers.Flag;
import com.osiptsoff.aocs.api.model.registers.Registers;
import com.osiptsoff.aocs.api.util.commands.*;

public class DIVC1 extends RegConstCommand {
    public DIVC1(int R0, int R1, int DISP) {
        super(0b10001, R0, R1, DISP);
    }
    @Override
    public int serialize() {
        return serialize(R0, R1, DISP);
    }
    /**
     * <p>Division of a constant by a signed integer.</p>
     * <p>Pseudocode: {@code <R0> ← const / <R2>}</p>
     * <p>Flags: Z, C, S: +; O, I, T, U: -</p>
     * <p>Opcode: 0010001</p>
     * @author Artem Ivanov
     */
    @Override
    public void execute(Registers registers) {
        if (registers.getIntRegister(R1) == 0) {
            // Set overflow flag and exit the command
            registers.setFlag(Flag.Overflow, true);
            return;
        }
        long result = (long) registers.getIntRegister(DISP) / (long) registers.getIntRegister(R1);
        registers.setIntRegister(R0, (int) result);

        registers.setFlag(Flag.ZeroResult, registers.getIntRegister(R0) == 0);
        registers.setFlag(Flag.Carry, result > Integer.MAX_VALUE || result < Integer.MIN_VALUE);
        registers.setFlag(Flag.Sign, registers.getIntRegister(R0) < 0);
    }
}
