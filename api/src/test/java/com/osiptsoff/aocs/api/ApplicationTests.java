package com.osiptsoff.aocs.api;

import com.osiptsoff.aocs.api.memory.Memory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

@SpringBootTest
@ContextConfiguration(classes = ApiApplication.class)
public class ApplicationTests {
    @Test
    void contextLoads() {
    }

    @Test
    void memoryTest(@Autowired Memory memory) {
        String id = memory.allocate(128);

        {
            int expected = 55645453;
            memory.setInt(id, 0, expected);
            int got = memory.getInt(id, 0);

            Assert.isTrue(got == expected, got + " != " + expected);
        }
        {
            short expected = 3456;
            memory.setShort(id, 0, expected);
            short got = memory.getShort(id, 0);

            Assert.isTrue(got == expected, got + " != " + expected);
        }
        {
            byte expected = 64;
            memory.setByte(id, 0, expected);
            byte got = memory.getByte(id, 0);

            Assert.isTrue(got == expected, got + " != " + expected);
        }
        {
            short expected = 0x35F0;
            memory.setShort(id, 0, (short)0x80F0);
            memory.setByte(id, 0, (byte)0x35);
            short got = memory.getShort(id, 0);

            Assert.isTrue(got == expected, got + " != " + expected);
        }
        {
            float expected = 564.445f;
            memory.setFloat(id, 0, expected);
            float got = memory.getFloat(id, 0);

            Assert.isTrue(got == expected, got + " != " + expected);
        }

        memory.deallocate(id);
    }
}
