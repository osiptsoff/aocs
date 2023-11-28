package com.osiptsoff.aocs.api.util.commands.facade.execution;

import com.osiptsoff.aocs.api.model.registers.Registers;
import com.osiptsoff.aocs.api.util.commands.Command;
import com.osiptsoff.aocs.api.util.commands.OneRegCommand;

/**
 * <p>{@link ExecutionChainLink} implementation for {@link OneRegCommand}.</p>
 * @author Nikita Osiptsov
 */
public final class OneRegExecution extends ExecutionChainLink {
    @Override
    protected boolean tryExecute(Command command, Object[] args) {
        if(command instanceof OneRegCommand) {
            ((OneRegCommand) command).execute( (Registers)args[0] );
            return true;
        }

        return false;
    }
}
