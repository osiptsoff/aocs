package com.osiptsoff.aocs.api.configuration;

import com.osiptsoff.aocs.api.util.commands.concreteCommands.ADD;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.ADDC;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.ADDCU;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.ADDF;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.ADDU;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.CMP;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.CMPC;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.CMPCU;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.CMPF;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.CMPU;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.DIV;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.DIVC1;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.DIVC2;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.DIVCU1;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.DIVCU2;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.DIVF;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.DIVU;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.MULT;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.MULTC;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.MULTCU;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.MULTF;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.MULTU;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.SUB;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.SUBC1;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.SUBC2;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.SUBCU1;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.SUBCU2;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.SUBF;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.SUBU;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.TFFI;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.TFIF;
import com.osiptsoff.aocs.api.util.commands.facade.CommandExecutor;
import com.osiptsoff.aocs.api.util.commands.facade.execution.ExecutionChain;
import com.osiptsoff.aocs.api.util.commands.facade.execution.OneRegExecution;
import com.osiptsoff.aocs.api.util.commands.facade.execution.RegConstExecution;
import com.osiptsoff.aocs.api.util.commands.facade.execution.RegMemExecution;
import com.osiptsoff.aocs.api.util.commands.facade.execution.RegRegExecution;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandExecutorConfiguration {
    @Bean
    public ExecutionChain executionChain() {
        return new ExecutionChain()
                .addLink(new RegRegExecution())
                .addLink(new RegConstExecution())
                .addLink(new RegMemExecution())
                .addLink(new OneRegExecution());
    }

    @Bean
    public CommandExecutor commandExecutor() {
        return new CommandExecutor()
                .useChain(executionChain())
                .registerCommand("add",
                        args -> new ADD( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("addc",
                        args -> new ADDC( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("addcu",
                        args -> new ADDCU( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("addf",
                        args -> new ADDF( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("addu",
                        args -> new ADDU( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("cmp",
                        args -> new CMP( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("cmpc",
                        args -> new CMPC( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("cmpcu",
                        args -> new CMPCU( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("cmpf",
                        args -> new CMPF( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("cmpu",
                        args -> new CMPU( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("div",
                        args -> new DIV( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("divc1",
                        args -> new DIVC1( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("divc2",
                        args -> new DIVC2( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("divcu1",
                        args -> new DIVCU1( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("divcu2",
                        args -> new DIVCU2( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("divf",
                        args -> new DIVF( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("divu",
                        args -> new DIVU( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("mult",
                        args -> new MULT( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("multc",
                        args -> new MULTC( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("multcu",
                        args -> new MULTCU( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("multf",
                        args -> new MULTF( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("multu",
                        args -> new MULTU( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("sub",
                        args -> new SUB( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("subc1",
                        args -> new SUBC1( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("subc2",
                        args -> new SUBC2( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("subcu1",
                        args -> new SUBCU1( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("subcu2",
                        args -> new SUBCU2( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("subf",
                        args -> new SUBF( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("subu",
                        args -> new SUBU( (int)args[0], (int)args[1], (int)args[2] ))
                .registerCommand("tffi",
                        args -> new TFFI( (int)args[0] ))
                .registerCommand("tfif",
                        args -> new TFIF( (int)args[0] ));
    }
}
