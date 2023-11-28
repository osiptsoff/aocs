package com.osiptsoff.aocs.api.util.commands.facade.execution;

import com.osiptsoff.aocs.api.model.registers.Registers;
import com.osiptsoff.aocs.api.util.commands.Command;
import com.osiptsoff.aocs.api.util.commands.RegRegCommand;

/**
 * <p>{@link ExecutionChainLink} implementation for {@link RegRegCommand}.</p>
 * @author Nikita Osiptsov
 */
public final class RegRegExecution extends ExecutionChainLink {
    @Override
    protected boolean tryExecute(Command command, Object[] args) {
        if (command instanceof RegRegCommand) {
            ((RegRegCommand) command).execute( (Registers) args[0]);
            return true;
        }

        return false;
    }
}
