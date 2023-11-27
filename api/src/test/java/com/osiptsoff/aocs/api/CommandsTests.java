package com.osiptsoff.aocs.api;

import com.osiptsoff.aocs.api.memory.Memory;
import com.osiptsoff.aocs.api.model.registers.Flag;
import com.osiptsoff.aocs.api.model.registers.Registers;
import com.osiptsoff.aocs.api.util.commands.concreteCommands.ADD;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

@SpringBootTest
@ContextConfiguration(classes = ApiApplication.class)
public class CommandsTests {

    @Test
    public void ADDTest() {
        Registers registers = new Registers();
        {
            ADD add = new ADD(0, 1, 2);
            int expected = 28;
            registers.setIntRegister(1, 13);
            registers.setIntRegister(2, 15);
            add.execute(registers);
            int got = registers.getIntRegister(0);

            Assert.isTrue(got == expected, got + " != " + expected);
            got = add.serialize();
            expected = 0b0000001_000000_000001_000010_0000000;
            Assert.isTrue(got == expected, got + " != " + expected);
            Assert.isTrue(!registers.getFlag(Flag.ZeroResult), "Zero flag should be set");
            Assert.isTrue(registers.getFlag(Flag.Carry), "Carry flag should be set");
            Assert.isTrue(!registers.getFlag(Flag.Sign), "Sign flag should not be set");
            Assert.isTrue(!registers.getFlag(Flag.Overflow), "Overflow flag should not be set");

            // Тестирование флагов при сложении отрицательного и положительного чисел
            registers.setIntRegister(1, -5);
            registers.setIntRegister(2, 7);
            add.execute(registers);
            got = registers.getIntRegister(0);
            expected = 2;
            Assert.isTrue(got == expected, got + " != " + expected);

            Assert.isTrue(!registers.getFlag(Flag.ZeroResult), "Zero flag should not be set");
            Assert.isTrue(!registers.getFlag(Flag.Carry), "Carry flag should not be set");
            Assert.isTrue(!registers.getFlag(Flag.Sign), "Sign flag should not be set");
            Assert.isTrue(!registers.getFlag(Flag.Overflow), "Overflow flag should not be set");

            // Тестирование флагов при сложении отрицательных чисел
            registers.setIntRegister(1, -15);
            registers.setIntRegister(2, -10);
            add.execute(registers);
            got = registers.getIntRegister(0);
            expected = -25;
            Assert.isTrue(got == expected, got + " != " + expected);

            Assert.isTrue(!registers.getFlag(Flag.ZeroResult), "Zero flag should not be set");
            Assert.isTrue(registers.getFlag(Flag.Carry), "Carry flag should be set");
            Assert.isTrue(registers.getFlag(Flag.Sign), "Sign flag should be set");
            Assert.isTrue(!registers.getFlag(Flag.Overflow), "Overflow flag should not be set");
        }
    }
    
}
