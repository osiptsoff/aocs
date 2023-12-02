package com.osiptsoff.aocs.api.memory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>Implementation of {@link Memory} which stores machine's memory
 * in address space of Java-process.</p>
 * @author Nikita Osiptsov
 */
@Repository
@Primary
public final class InMemoryMemory implements Memory {
    private final Map<String, ByteBuffer> addressSpace;

    private int freeSpace;

    private void checkArgs(String identifier, int address) throws IllegalArgumentException,
            IndexOutOfBoundsException {
        if(!addressSpace.containsKey(identifier))
            throw new IllegalArgumentException();
        if(addressSpace.get(identifier).limit() < address)
            throw new IndexOutOfBoundsException();
    }

    public InMemoryMemory(@Value("${app.memoryKb}") Integer freeSpace) {
        this.freeSpace = freeSpace;

        addressSpace = new HashMap<>();
    }

    @Override
    public String allocate(int size) throws IndexOutOfBoundsException {
        if(size > freeSpace)
            throw new IllegalArgumentException();

        byte[] memory = new byte[size * 1024];
        freeSpace -= size;

        String identifier = UUID.randomUUID().toString();
        while(addressSpace.containsKey(identifier))
            identifier = UUID.randomUUID().toString();

        addressSpace.put(identifier, ByteBuffer.wrap(memory));

        return identifier;
    }

    @Override
    public void deallocate(String identifier) {
        if(!addressSpace.containsKey(identifier))
            return;

        freeSpace += addressSpace.get(identifier).limit() / 1024;
        addressSpace.remove(identifier);
    }

    @Override
    public byte getByte(String identifier, int address) throws IndexOutOfBoundsException,
            IllegalArgumentException {
        checkArgs(identifier, address);

        return addressSpace.get(identifier).get(address);
    }

    @Override
    public short getShort(String identifier, int address) throws IndexOutOfBoundsException,
            IllegalArgumentException {
        checkArgs(identifier, address);

        return addressSpace.get(identifier).getShort(address);
    }

    @Override
    public int getInt(String identifier, int address) throws IndexOutOfBoundsException,
            IllegalArgumentException {
        checkArgs(identifier, address);

        return addressSpace.get(identifier).getInt(address);
    }

    @Override
    public float getFloat(String identifier, int address) throws IndexOutOfBoundsException,
            IllegalArgumentException {
        checkArgs(identifier, address);

        return addressSpace.get(identifier).getFloat(address);
    }

    @Override
    public void setByte(String identifier, int address, byte value) throws IndexOutOfBoundsException,
            IllegalArgumentException {
        checkArgs(identifier, address);

        addressSpace.get(identifier).put(address, value);
    }

    @Override
    public void setShort(String identifier, int address, short value) throws IndexOutOfBoundsException,
            IllegalArgumentException {
        checkArgs(identifier, address);

        addressSpace.get(identifier).putShort(address, value);
    }

    @Override
    public void setInt(String identifier, int address, int value) throws IndexOutOfBoundsException,
            IllegalArgumentException {
        checkArgs(identifier, address);

        addressSpace.get(identifier).putInt(address, value);
    }

    public void setFloat(String identifier, int address, float value) throws IndexOutOfBoundsException,
            IllegalArgumentException {
        checkArgs(identifier, address);

        addressSpace.get(identifier).putFloat(address, value);
    }
}
