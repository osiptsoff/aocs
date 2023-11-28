package com.osiptsoff.aocs.api.util.commands.concreteCommands;

import com.osiptsoff.aocs.api.model.registers.Flag;
import com.osiptsoff.aocs.api.model.registers.Registers;
import com.osiptsoff.aocs.api.util.commands.*;

public class TFFI extends OneRegCommand {
    public TFFI(int R0) {
        super(0b11110, R0);
    }
    @Override
    public int serialize() {
        return serialize(R0, 0);
    }
    /**
     * <p>Conversion of float to integer number.</p>
     * <p>Pseudocode: {@code <RF0> ← float(<R0>)}</p>
     * <p>Flags: Z: -; C, S, O: +; I, T, U: -</p>
     * <p>Opcode: 0011110</p>
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
