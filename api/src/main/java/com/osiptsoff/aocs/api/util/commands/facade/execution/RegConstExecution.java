package com.osiptsoff.aocs.api.util.commands.facade.execution;

import com.osiptsoff.aocs.api.model.registers.Registers;
import com.osiptsoff.aocs.api.util.commands.Command;
import com.osiptsoff.aocs.api.util.commands.RegConstCommand;

/**
 * <p>{@link ExecutionChainLink} implementation for {@link RegConstCommand}.</p>
 * @author Nikita Osiptsov
 */
public final class RegConstExecution extends ExecutionChainLink {
    @Override
    protected boolean tryExecute(Command command, Object[] args) {
        if(command instanceof RegConstCommand) {
            ((RegConstCommand) command).execute( (Registers)args[0] );
            return true;
        }
        return false;
    }
}
