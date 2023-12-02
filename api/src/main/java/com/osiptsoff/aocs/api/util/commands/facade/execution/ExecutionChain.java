package com.osiptsoff.aocs.api.util.commands.facade.execution;

import com.osiptsoff.aocs.api.util.commands.Command;
import com.osiptsoff.aocs.api.util.commands.facade.CommandExecutor;

/**
 * <p>Class used to configure {@link CommandExecutor}.</p>
 * <p>Instances are used to resolve {@link Command}'s type and to call it's {@link #execute} method.</p>
 * @author Nikita Osiptsov
 */
public class ExecutionChain {
    private ExecutionChainLink first;
    private ExecutionChainLink last;

    /**
     * <p>Adds {@link ExecutionChainLink} to this chain.</p>
     * @param link {@link ExecutionChainLink} to add to this chain.
     * @return this instance.
     */
    public ExecutionChain addLink(ExecutionChainLink link) {
        if(first == null) {
            first = link;
            last = link;
        } else {
            last.setNext(link);
            last = link;
        }

        return this;
    }

    /**
     * <p>Gives given command to link in order to resolve and execute.</p>
     * @param command {@link Command} to execute,
     * @param args arguments passed to command's {@code execute()} method.
     * @throws UnsupportedOperationException if given {@link Command}'s type is not supported.
     */
    public void execute(Command command, Object[] args) throws UnsupportedOperationException {
        if(first == null)
            throw new UnsupportedOperationException();

        first.execute(command, args);
    }
}
