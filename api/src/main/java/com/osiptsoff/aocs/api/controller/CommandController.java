package com.osiptsoff.aocs.api.controller;

import com.osiptsoff.aocs.api.model.communication.request.CommandExecutionRequest;
import com.osiptsoff.aocs.api.model.communication.request.JsonCommand;
import com.osiptsoff.aocs.api.model.communication.response.TextMessage;
import com.osiptsoff.aocs.api.model.registers.Registers;
import com.osiptsoff.aocs.api.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jmx.access.InvocationFailureException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/command")
public class CommandController {
    private final CommandService commandService;

    @Autowired
    public CommandController(CommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping()
    public Registers executeCommand(@RequestBody(required = true) CommandExecutionRequest request,
                                    @RequestHeader(required = true) String id) {
        Registers registers = request.registers;
        String name = request.commandName;
        Object[] args = request.commandArgs;

        commandService.performCommandExecution(name, id, registers, args);

        return registers;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/store")
    public void storeProgram(@RequestBody(required = true) JsonCommand[] program,
                                    @RequestHeader(required = true) String id) {
        commandService.loadProgram(id, program);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ NullPointerException.class,
            ClassCastException.class,
            IndexOutOfBoundsException.class,
            HttpMessageNotReadableException.class})
    public TextMessage userInputExceptionHandler() {
        return new TextMessage("Wrong arguments");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ InvocationFailureException.class, UnsupportedOperationException.class })
    public TextMessage unsupportedCommandExceptionHandler() {
        return new TextMessage("Command is not supported");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({ MissingRequestHeaderException.class, IllegalArgumentException.class })
    public TextMessage idExceptionHandler() {
        return new TextMessage("Wrong or missing id");
    }
}
