package com.osiptsoff.aocs.api.model.registers;

/**
 * <p>Represents registers of machine.</p>
 * <p>Contains 32 {@code int} registers, 32 {@code float} registers
 * and specific ones: PC, PCI, FLAG, FLAGI.</p>
 * @author Nikita Osiptsov
 */
public class Registers {
    private final int[] intRegs;
    private final float[] floatRegs;
    private int programCounter, programCounterInterrupt;
    private byte flags, flagsInterrupt;

    public Registers() {
        intRegs = new int[32];
        floatRegs = new float[32];
    }

    /**
     * @param num number of integer register.
     * @return value of integer register.
     * @throws IndexOutOfBoundsException if {@code num} is negative or greater than 31.
     */
    public int getIntRegister(int num) throws IndexOutOfBoundsException {
        return intRegs[num];
    }
    /**
     * <p>Stores new value in specified integer register.</p>
     * @param num number of integer register,
     * @param value value of integer register.
     * @throws IndexOutOfBoundsException if {@code num} is negative or greater than 31.
     */
    public void setIntRegister(int num, int value) throws IndexOutOfBoundsException {
        intRegs[num] = value;
    }

    /**
     * @param num number of float register.
     * @return value of float register.
     * @throws IndexOutOfBoundsException if {@code num} is negative or greater than 31.
     */
    public float getFloatRegister(int num) throws IndexOutOfBoundsException {
        return floatRegs[num];
    }
    /**
     * <p>Stores new value in specified float register.</p>
     * @param num number of float register,
     * @param value value of float register.
     * @throws IndexOutOfBoundsException if {@code num} is negative or greater than 31.
     */
    public void setFloatRegister(int num, float value) throws IndexOutOfBoundsException {
        floatRegs[num] = value;
    }

    /**
     * <p>Stores new value to program counter (PC) register.</p>
     * @param value new value.
     */
    public void setProgramCounter(int value) {
        programCounter = value;
    }
    /**
     * @return value of program counter (PC) register.
     */
    public int getProgramCounter() {
        return programCounter;
    }

    /**
     * <p>Assigns specified value to bit of flag (F) register.</p>
     * @param flag flag to change (bit of flag (F) register),
     * @param value new value of flag.
     */
    public void setFlag(Flag flag, boolean value) {
        if( value )
            flags |= (byte)( 1 << flag.val ) ;
        else
            flags &= (byte)( ~( 1 << flag.val ) );
    }
    /**
     * @param flag desired flag (bit of flag (F) register).
     * @return value of flag (bit of flag (F) register).
     */
    public boolean getFlag(Flag flag) {
        return (byte)( flags & ( 1 << flag.val ) ) > 0;
    }

    /**
     * <p>Stores {@code programCounter} (PC) and {@code flags} (F) to their interrupt twins.</p>
     */
    public void saveSpecialRegisters() {
        programCounterInterrupt = programCounter;
        flagsInterrupt = flags;
    }
    /**
     * <p>Stores {@code programCounterInterrupt} (PC) and {@code flagsInterrupt} (F) to their regular twins.</p>
     */
    public void loadSpecialRegisters() {
        programCounter = programCounterInterrupt;
        flags = flagsInterrupt;
    }

    /**
     * <p>This method was written to provide way to serialize this object to JSON.</p>
     * @return integer registers.
     */
    public int[] getIntRegs() {
        return intRegs;
    }

    /**
     * <p>This method was written to provide way to serialize this object to JSON.</p>
     * @return float registers.
     */
    public float[] getFloatRegs() {
        return floatRegs;
    }

    /**
     * <p>This method was written to provide way to serialize this object to JSON.</p>
     * @return program counter interrupt register.
     */
    public int getProgramCounterInterrupt() {
        return programCounterInterrupt;
    }

    /**
     * <p>This method was written to provide way to serialize this object to JSON.</p>
     * @return flags register.
     */
    public byte getFlags() {
        return flags;
    }

    /**
     * <p>This method was written to provide way to serialize this object to JSON.</p>
     * @return flags interrupt register.
     */
    public byte getFlagsInterrupt() {
        return flagsInterrupt;
    }
}