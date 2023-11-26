package com.osiptsoff.aocs.api.model.registers;

/**
 * <p>Provides easier communication with flags register.</p>
 * @author Nikita Osiptsov
 */
public enum Flag {
    Superuser(0), // U
    StepByStep(1), // T
    Interrupt(2), // I
    Overflow(3), // O
    Sign(4), // S
    Carry(5), // C
    ZeroResult(6), // Z
    Reserved(7);

    public final int val;

    Flag(int val) {
        this.val = val;
    }
}