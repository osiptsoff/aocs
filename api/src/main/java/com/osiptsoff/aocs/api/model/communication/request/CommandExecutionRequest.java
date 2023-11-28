package com.osiptsoff.aocs.api.model.communication.request;

import com.osiptsoff.aocs.api.model.registers.Registers;

public class CommandExecutionRequest {
    public String commandName;
    public Object[] commandArgs;
    public Registers registers;
}
