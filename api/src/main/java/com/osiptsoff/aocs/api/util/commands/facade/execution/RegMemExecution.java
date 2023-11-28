package com.osiptsoff.aocs.api.util.commands.facade.execution;

import com.osiptsoff.aocs.api.memory.Memory;
import com.osiptsoff.aocs.api.model.registers.Registers;
import com.osiptsoff.aocs.api.util.commands.Command;
import com.osiptsoff.aocs.api.util.commands.RegMemCommand;

/**
 * <p>{@link ExecutionChainLink} implementation for {@link RegMemCommand}.</p>
 * @author Nikita Osiptsov
 */
public final class RegMemExecution extends ExecutionChainLink {
    @Override
    protected boolean tryExecute(Command command, Object[] args) {
        if(command instanceof RegMemCommand) {
            ((RegMemCommand) command).execute( (Registers)args[0], (Memory)args[1], (String)args[2]);
            return true;
        }
        return false;
    }
}
