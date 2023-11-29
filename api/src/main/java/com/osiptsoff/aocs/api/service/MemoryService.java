package com.osiptsoff.aocs.api.service;

import com.osiptsoff.aocs.api.memory.Memory;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemoryService {
    private final Memory memory;
    private final Logger logger;

    @Autowired
    public MemoryService(Memory memory, Logger logger) {
        this.memory = memory;
        this.logger = logger;
    }

    public String allocateMemory(int size) {
        try {
            logger.info("Got request for memory with size of " + size + " Kb");
            String identifier = memory.allocate(size);
            logger.info("Allocated memory with size of " + size + " Kb, id " + identifier);

            return identifier;
        } catch (IndexOutOfBoundsException iae) {
            logger.info("Failed to allocate" + size + " Kb of memory, no free space");
            throw iae;
        } catch (Exception e) {
            logger.warn("Caught unexpected exception of type '"
                    + e.getClass()
                    + "' with message '"
                    + e.getMessage()
                    + "' while allocating "
                    + size
                    + " Kb of memory.");
            throw e;
        }
    }

    public void deallocateMemory(String id) {
        logger.info("Got request for deallocation of memory with id " + id);
        memory.deallocate(id);
        logger.info("Deallocated memory with id " + id);
    }

    public int[] readOneKb(String id, int addr) {
        try {
            logger.info("Got request for reading memory with id " + id + " at address " + addr);
            int[] chunk = new int[256];
            addr -= addr % 1024;

            for(int i = 0; i < 256; i++)
                chunk[i] = memory.getInt(id, addr + i * 4);

            return chunk;
        } catch (Exception e) {
            logger.info("Failed to read memory with id " + id + " at address " + addr);
            throw e;
        }

    }
}
