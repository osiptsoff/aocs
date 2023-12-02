package com.osiptsoff.aocs.api.service;

import com.osiptsoff.aocs.api.memory.Memory;
import com.osiptsoff.aocs.api.model.communication.request.JsonCommand;
import com.osiptsoff.aocs.api.model.registers.Registers;
import com.osiptsoff.aocs.api.util.MemoryCleaner;
import com.osiptsoff.aocs.api.util.commands.Command;
import com.osiptsoff.aocs.api.util.commands.facade.CommandExecutor;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.access.InvocationFailureException;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class CommandService {
    private final Logger logger;
    private final CommandExecutor commandExecutor;
    private final Memory memory;
    private final MemoryCleaner memoryCleaner;

    @Autowired
    public CommandService(Logger logger, CommandExecutor commandExecutor, Memory memory, MemoryCleaner memoryCleaner) {
        this.logger = logger;
        this.commandExecutor = commandExecutor;
        this.memory = memory;
        this.memoryCleaner = memoryCleaner;
    }

    public void performCommandExecution(String commandName,
                                        String memoryBlockId,
                                        Registers registers,
                                        Object[] instantiationArgs)
            throws NullPointerException, InvocationFailureException,
            IllegalArgumentException, UnsupportedOperationException,
            ClassCastException, IndexOutOfBoundsException {
        try {
            memory.getInt(memoryBlockId, 0);

            logger.info("Got request for command execution: command '"
                    + commandName
                    + "' with args "
                    + Arrays.toString(instantiationArgs)
                    + " from owner of memory with id "
                    + memoryBlockId);
            commandExecutor.execute(commandName, new Object[]{ registers, memory, memoryBlockId }, instantiationArgs);
            registers.setProgramCounter(registers.getProgramCounter() + 4);
            logger.info("Successfully executed '" + commandName + "' command");
            memoryCleaner.mark(memoryBlockId);
        } catch(NullPointerException npe) {
            logger.info("Execution failed: null parameter passed");
            throw npe;
        } catch(InvocationFailureException ife) {
            logger.info("Execution failed: command is not registered");
            throw ife;
        } catch(IllegalArgumentException iae) {
            logger.info("Execution failed: wrong memory id");
            throw iae;
        } catch(UnsupportedOperationException uoe) {
            logger.info("Execution failed: type of command is not supported but command is registered");
            throw uoe;
        } catch(ClassCastException cce) {
            logger.info("Execution failed: cannot cast given command argument to required type");
            throw cce;
        } catch(IndexOutOfBoundsException ibe) {
            logger.info("Execution failed: tried to surpass borders of memory area");
            throw ibe;
        } catch(Exception e) {
            logger.warn("Execution failed: caught unexpected exception of type '"
                    + e.getClass()
                    + "' with message '"
                    + e.getMessage());
            throw e;
        }
    }

    public void loadProgram(String memoryBlockId, JsonCommand[] program) throws NullPointerException, InvocationFailureException,
            IllegalArgumentException, UnsupportedOperationException, ClassCastException, IndexOutOfBoundsException {
        try {
            logger.info("Got request for program storing");
            for (int i = 0; i < program.length; i++) {
                Command instance = commandExecutor.instantiate(program[i].commandName, program[i].commandArgs);
                memory.setInt(memoryBlockId, i * 4, instance.serialize());
            }
            logger.info("Successfully stored program");
            memoryCleaner.mark(memoryBlockId);
        } catch(NullPointerException npe) {
            logger.info("Storing failed: null parameter passed");
            throw npe;
        } catch(InvocationFailureException ife) {
            logger.info("Storing failed: command is not registered");
            throw ife;
        } catch(IllegalArgumentException iae) {
            logger.info("Storing failed: wrong memory id");
            throw iae;
        } catch(UnsupportedOperationException uoe) {
            logger.info("Storing failed: type of command is not supported but command is registered");
            throw uoe;
        } catch(ClassCastException cce) {
            logger.info("Storing failed: cannot cast given command argument to required type");
            throw cce;
        } catch(IndexOutOfBoundsException ibe) {
            logger.info("Storing failed: tried to surpass borders of memory area");
            throw ibe;
        } catch(Exception e) {
        logger.warn("Execution failed: caught unexpected exception of type '"
                + e.getClass()
                + "' with message '"
                + e.getMessage());
        throw e;
    }
    }
}
