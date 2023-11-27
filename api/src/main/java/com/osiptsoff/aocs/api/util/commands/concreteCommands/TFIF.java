package com.osiptsoff.aocs.api.util.commands.concreteCommands;

import com.osiptsoff.aocs.api.model.registers.Flag;
import com.osiptsoff.aocs.api.model.registers.Registers;
import com.osiptsoff.aocs.api.util.commands.*;

public class TFIF extends OneRegCommand {
    public TFIF(int R0) {
        super(0b11101, R0);
    }
    @Override
    public int serialize() {
        return serialize(R0, 0);
    }
    /**
     * <p>Conversion of fixed-point number to integer.</p>
     * <p>Pseudocode: {@code <R0> ← int(<RF0>)}</p>
     * <p>Flags: Z: -; C, S, O: +; I, T, U: -</p>
     * <p>Opcode: 0011101</p>
     * @author Artem Ivanov
     */
    @Override
    public void execute(Registers registers) {
        float floatValue = registers.getFloatRegister(R0);
        int intValue = (int) floatValue;
        registers.setIntRegister(R0, intValue);

        registers.setFlag(Flag.Carry, registers.getIntRegister(R0) > Integer.MAX_VALUE || registers.getIntRegister(R0) < Integer.MIN_VALUE);
        registers.setFlag(Flag.Sign, registers.getIntRegister(R0) < 0);
        registers.setFlag(Flag.Overflow, ((registers.getIntRegister(R0) > 0 && registers.getIntRegister(R0) > 0 && registers.getIntRegister(R0) < 0)
                || (registers.getIntRegister(R0) < 0 && registers.getIntRegister(R0) < 0 && registers.getIntRegister(R0) > 0)));
    }
}
