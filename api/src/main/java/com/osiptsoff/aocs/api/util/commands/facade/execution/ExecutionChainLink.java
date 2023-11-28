package com.osiptsoff.aocs.api.util.commands.facade.execution;

import com.osiptsoff.aocs.api.util.commands.Command;

/**
 * <p>Represents one {@link Command}'s type in {@link ExecutionChain}.</p>
 * @author Nikita Osiptsov
 */
public abstract class ExecutionChainLink {
    private ExecutionChainLink next;

    /**
     * <p>Sets {@link ExecutionChainLink} to which command will be passed if this link cannot resolve command.</p>
     * @param next next link.
     */
    protected void setNext(ExecutionChainLink next) {
        this.next = next;
    }

    /**
     * @return {@link ExecutionChainLink} to which command will be passed if this link cannot resolve command.
     */
    public ExecutionChainLink getNext() {
        return next;
    }

    /**
     * <p>This method contains logic for command resolution and execution.</p>
     * @param command to resolve and execute if it can be resolved by this link.
     * @param args arguments for {@link Command}'s {@code execute()} method.
     * @return {@code true} - if command was resolved and successfully executed, {@code false} otherwise.
     */
    protected abstract boolean tryExecute(Command command, Object[] args);

    /**
     * <p>Invokes {@link #tryExecute}. If command was not resolved, invokes {@link #execute} of next chain link.</p>
     * @param command command to resolve and execute if it can be resolved by this link.
     * @param args args arguments for {@link Command}'s {@code execute()} method.
     * @throws UnsupportedOperationException if command was not resolved by this link and next link is {@code null}.
     * It means this {@link ExecutionChainLink} does not support type of given command.
     */
    public void execute(Command command, Object[] args) throws UnsupportedOperationException {
        if(tryExecute(command, args))
            return;

        if(next == null)
            throw new UnsupportedOperationException();

        next.execute(command, args);
    }
}
