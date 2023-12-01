package com.osiptsoff.aocs.api.util.commands.facade;

import com.osiptsoff.aocs.api.util.commands.Command;
import com.osiptsoff.aocs.api.util.commands.RegMemCommand;
import com.osiptsoff.aocs.api.util.commands.RegRegCommand;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.ADD;
import com.osiptsoff.aocs.api.util.commands.facade.execution.ExecutionChain;
import org.springframework.jmx.access.InvocationFailureException;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>This class provides facade for command execution.</p
 * <p>It allows to work with {@link Command} instances without direct instantiation and parameters passing.</p>>
 * @author Nikita Osiptsov
 */
public class CommandExecutor {
    private final Map<String, CommandSupplier> commands;
    private ExecutionChain executionChain;

    public CommandExecutor() {
        commands = new HashMap<>();
    }

    /**
     * <p>Sets {@link ExecutionChain} that will be used by this instance.</p>
     * <p>Given chain specifies commands of which type
     * (type example - {@link RegRegCommand}) can be executed.</p>
     * @param executionChain chain of supported command types.
     * @return this instance.
     * @throws NullPointerException if {@code executionChain} is {@code null}.
     */
    public CommandExecutor useChain(ExecutionChain executionChain) throws NullPointerException {
        if(executionChain == null)
            throw new NullPointerException("Chain must not be null.");

        this.executionChain = executionChain;

        return this;
    }

    /**
     * <p>Enables support for given {@link Command} (command example - {@link ADD}) if it's
     * type is defined in {@link ExecutionChain} of this instance.</p>
     * @param name name of command (e.g. 'add').
     * @param supplier {@link CommandSupplier} responsible for instantiation of desired command.
     * @return this instance.
     * @throws NullPointerException if one of arguments is {@code null}.
     */
    public CommandExecutor registerCommand(String name, CommandSupplier supplier) throws NullPointerException {
        if(name == null || supplier == null)
            throw new NullPointerException();

        commands.put(name, supplier);

        return this;
    }

    /**
     * <p>Executes given {@link Command} if its type and it itself are supported by this instance.</p>
     * @param name name of command previously registered by call of {@link #registerCommand},
     * @param executionArgs array of arguments required for command's {@code execute()} method,
     * @param instantiationArgs array of arguments required for command's instantiation.
     * @throws NullPointerException if given {@code name} is {@code null},
     * @throws InvocationFailureException if command with given {@code name} is not registered by {@link #registerCommand},
     * or {@link RegMemCommand} was given a wrong memory id,
     * @throws UnsupportedOperationException if command is given {@code name} is registered, but it's type is not,
     * @throws ClassCastException if one of given {@code instantiationArgs} is of inappropriate type,
     * @throws IndexOutOfBoundsException if {@link RegMemCommand} was given an inappropriate address.
     * is not supported by underlying {@link ExecutionChain}.
     */
    public void execute(String name, Object[] executionArgs, Object[] instantiationArgs) throws NullPointerException,
            InvocationFailureException, IllegalArgumentException, UnsupportedOperationException,
            ClassCastException, IndexOutOfBoundsException {
        if(name == null)
            throw new NullPointerException("Name must not be null.");
        if(commands.get(name) == null)
            throw new InvocationFailureException("Command is not registered.");

        Command command = commands.get(name).instantiate(instantiationArgs);

        executionChain.execute(command, executionArgs);
    }

    /**
     *
     * @param name name of command previously registered by call of {@link #registerCommand},
     * @param instantiationArgs array of arguments required for command's instantiation.
     * @return instance of specified {@link Command}.
     * @throws NullPointerException if given {@code name} is {@code null},
     * @throws InvocationFailureException if command with given {@code name} is not registered by {@link #registerCommand},
     * @throws UnsupportedOperationException if command is given {@code name} is registered, but it's type is not,
     * @throws ClassCastException if one of given {@code instantiationArgs} is of inappropriate type.
     */
    public Command instantiate(String name, Object[] instantiationArgs) throws NullPointerException,
            InvocationFailureException, UnsupportedOperationException, ClassCastException {
        if(name == null)
            throw new NullPointerException("Name must not be null.");
        if(commands.get(name) == null)
            throw new InvocationFailureException("Command is not registered.");

        return commands.get(name).instantiate(instantiationArgs);
    }

}
