package com.osiptsoff.aocs.api.memory;

import org.springframework.stereotype.Repository;

/**
 * <p>Representation of machine's memory.</p>
 * @author Nikita Osiptsov
 */
@Repository
public interface Memory {
    /**
     * <p>Allocates memory of given size.</p>
     * @param size size of memory in kilobytes.
     * @return  {@code String} identifier of area if allocation was successful.
     * @throws IndexOutOfBoundsException if no free space of given {@code size} is available.
     */
    String allocate(int size) throws IndexOutOfBoundsException;
    /**
     * <p>Frees memory area.</p>
     * @param identifier identifier of memory area.
     */
    void deallocate(String identifier);

    /**
     * @param identifier identifier of needed memory area,
     * @param address address of needed byte.
     * @return requested byte.
     * @throws IndexOutOfBoundsException if {@code address} exceeds size of area.
     */
    byte getByte(String identifier, int address) throws IndexOutOfBoundsException, IllegalArgumentException;
    /**
     * @param identifier identifier of needed memory area,
     * @param address address of needed short.
     * @return requested short (2 bytes).
     * @throws IndexOutOfBoundsException if {@code address} exceeds size of area,
     * @throws IllegalArgumentException if memory with given {@code identifier} does not exist.
     */
    short getShort(String identifier, int address) throws IndexOutOfBoundsException, IllegalArgumentException;
    /**
     * @param identifier identifier of needed memory area,
     * @param address address of needed int,
     * @return requested int (4 bytes).
     * @throws IndexOutOfBoundsException if {@code address} exceeds size of area,
     * @throws IllegalArgumentException if memory with given {@code identifier} does not exist.
     */
    int getInt(String identifier, int address) throws IndexOutOfBoundsException, IllegalArgumentException;
    /**
     * @param identifier identifier of needed memory area,
     * @param address address of needed float,
     * @return requested float (4 bytes).
     * @throws IndexOutOfBoundsException if {@code address} exceeds size of area,
     * @throws IllegalArgumentException if memory with given {@code identifier} does not exist.
     */
    float getFloat(String identifier, int address) throws IndexOutOfBoundsException, IllegalArgumentException;

    /**
     * <p>Changes the value of memory byte.</p>
     * @param identifier identifier of needed memory area,
     * @param address address of needed byte,
     * @param value new value of byte.
     * @throws IndexOutOfBoundsException if {@code address} exceeds size of area,
     * @throws IllegalArgumentException if memory with given {@code identifier} does not exist.
     */
    void setByte(String identifier, int address, byte value) throws IndexOutOfBoundsException, IllegalArgumentException;
    /**
     * <p>Changes the value of memory short.</p>
     * @param identifier identifier of needed memory area,
     * @param address address of needed short,
     * @param value new value of short (2 bytes).
     * @throws IndexOutOfBoundsException if {@code address} exceeds size of area,
     * @throws IllegalArgumentException if memory with given {@code identifier} does not exist.
     */
    void setShort(String identifier, int address, short value) throws IndexOutOfBoundsException, IllegalArgumentException;
    /**
     * <p>Changes the value of memory int.</p>
     * @param identifier identifier of needed memory area,
     * @param address address of needed int,
     * @param value new value of int (4 bytes).
     * @throws IndexOutOfBoundsException if {@code address} exceeds size of area,
     * @throws IllegalArgumentException if memory with given {@code identifier} does not exist.
     */
    void setInt(String identifier, int address, int value) throws IndexOutOfBoundsException, IllegalArgumentException;
    /**
     * <p>Changes the value of memory float.</p>
     * @param identifier identifier of needed memory area,
     * @param address address of needed float,
     * @param value new value of float (4 bytes).
     * @throws IndexOutOfBoundsException if {@code address} exceeds size of area,
     * @throws IllegalArgumentException if memory with given {@code identifier} does not exist.
     */
    void setFloat(String identifier, int address, float value) throws IndexOutOfBoundsException, IllegalArgumentException;
}
