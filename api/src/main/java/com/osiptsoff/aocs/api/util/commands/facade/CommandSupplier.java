package com.osiptsoff.aocs.api.util.commands.facade;

import com.osiptsoff.aocs.api.util.commands.Command;

@FunctionalInterface
public interface CommandSupplier {
    public Command instantiate(Object[] args);
}
