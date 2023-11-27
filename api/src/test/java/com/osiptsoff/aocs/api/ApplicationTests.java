package com.osiptsoff.aocs.api;

import com.osiptsoff.aocs.api.memory.Memory;
import com.osiptsoff.aocs.api.model.registers.Flag;
import com.osiptsoff.aocs.api.model.registers.Registers;
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

    @Test
    void registersTest() {
        Registers registers = new Registers();

        {
            int expected = 765;
            int regnum = 25;
            registers.setIntRegister(regnum, expected);
            int got = registers.getIntRegister(regnum);

            Assert.isTrue(got == expected, got + " != " + expected);
        }
        {
            float expected = 765;
            int regnum = 25;
            registers.setFloatRegister(regnum, expected);
            float got = registers.getIntRegister(regnum);

            Assert.isTrue(got == expected, got + " != " + expected);
        }
        {
            int expected = 4545;
            registers.setProgramCounter(expected);
            int got = registers.getProgramCounter();

            Assert.isTrue(got == expected, got + " != " + expected);
        }
        {
            boolean expected = true;
            Flag flag = Flag.Overflow;
            registers.setFlag(flag, expected);
            boolean got = registers.getFlag(flag);

            Assert.isTrue(got == expected, got + " != " + expected);

            expected = false;
            registers.setFlag(flag, expected);
            got = registers.getFlag(flag);

            Assert.isTrue(got == expected, got + " != " + expected);
        }
        {
            boolean expected = false;
            Flag flag = Flag.Carry;
            registers.setFlag(flag, expected);
            boolean got = registers.getFlag(flag);

            Assert.isTrue(got == expected, got + " != " + expected);

            expected = true;
            registers.setFlag(flag, expected);
            got = registers.getFlag(flag);

            Assert.isTrue(got == expected, got + " != " + expected);
        }
    }
}
