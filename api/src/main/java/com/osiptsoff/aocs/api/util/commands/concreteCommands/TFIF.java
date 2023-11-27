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
     * <p>Conversion of integer number to float.</p>
     * <p>Pseudocode: {@code <R0> ← int(<RF0>)}</p>
     * <p>Flags: Z: -; C, S, O: +; I, T, U: -</p>
     * <p>Opcode: 0011101</p>
     * @author Artem Ivanov
     */
    @Override
    public void execute(Registers registers) {
        int intValue = registers.getIntRegister(R0);
        float floatValue = (float) intValue;
        registers.setFloatRegister(R0, floatValue);

        registers.setFlag(Flag.Carry, registers.getFloatRegister(R0) > Integer.MAX_VALUE || registers.getFloatRegister(R0) < Integer.MIN_VALUE);
        registers.setFlag(Flag.Sign, registers.getFloatRegister(R0) < 0);
        registers.setFlag(Flag.Overflow, ((registers.getFloatRegister(R0) > 0 && registers.getFloatRegister(R0) > 0 && registers.getFloatRegister(R0) < 0)
                || (registers.getFloatRegister(R0) < 0 && registers.getFloatRegister(R0) < 0 && registers.getFloatRegister(R0) > 0)));
    }
}
