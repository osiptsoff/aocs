package com.osiptsoff.aocs.api.controller;

import com.osiptsoff.aocs.api.model.communication.response.MemoryAllocatedResponse;
import com.osiptsoff.aocs.api.model.communication.response.MemoryResponse;
import com.osiptsoff.aocs.api.service.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memory")
public class MemoryController {
    private final MemoryService memoryService;

    @Autowired
    public MemoryController(MemoryService memoryService) {
        this.memoryService = memoryService;
    }

    @GetMapping()
    public MemoryResponse readKb(@RequestHeader(required = true) String id, @RequestHeader(defaultValue = "0") int addr) {
        addr -= addr % 1024;
        int[] mem = memoryService.readOneKb(id, addr);

        MemoryResponse response = new MemoryResponse();

        for(int i = 0; i < mem.length; i++)
            response.put(Integer.toHexString(addr + i * 4), Integer.toHexString(mem[i]));

        return response;
    }

    @GetMapping("/allocation")
    public MemoryAllocatedResponse allocate(@RequestHeader(required = true) int size) {
        MemoryAllocatedResponse response = new MemoryAllocatedResponse();

        response.id = memoryService.allocateMemory(size);

        return response;
    }

    @PostMapping("/allocation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deallocate(@RequestHeader(required = true) String id) {
        memoryService.deallocateMemory(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public String indexOutOfBoundsExceptionHandler() {
        return "Requested amount of memory is not available.";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgumentExceptionHandler() {
        return "Requested memory area does not exist.";
    }
}
