package com.osiptsoff.aocs.api.util;

import com.osiptsoff.aocs.api.memory.Memory;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

@Component
public final class MemoryCleaner {
    private final Memory memory;
    private final Logger logger;

    private final Map<String, Long> lastAccess;

    private final Integer cleanTimeoutMinutes;

    private void task() {
        var entries = lastAccess.entrySet();

        for(var entry : entries) {
            long difference = ( System.currentTimeMillis() - entry.getValue() ) / 1000 / 60;

            if(difference >= cleanTimeoutMinutes) {
                memory.deallocate(entry.getKey());
                entries.remove(entry);

                logger.info("Deallocated memory with id " + entry.getKey() + " due to old last access");
            }
        }
    }

    @Autowired
    public MemoryCleaner(Memory memory, Logger logger,
                         @Value("${app.memory.timeoutMin}") Integer cleanTimeoutMinutes) {
        this.memory = memory;
        this.logger = logger;
        this.cleanTimeoutMinutes = cleanTimeoutMinutes;

        lastAccess = new HashMap<>();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
                                      @Override
                                      public void run() {
                                        task();
                                      }
                                  },
                cleanTimeoutMinutes * 60 * 1000,
                cleanTimeoutMinutes * 60 * 1000);
    }

    public void mark(String id) {
        lastAccess.put(id, System.currentTimeMillis());
    }
}
