package com.osiptsoff.aocs.api;

import com.osiptsoff.aocs.api.model.registers.Flag;
import com.osiptsoff.aocs.api.model.registers.Registers;

import com.osiptsoff.aocs.api.util.commands.facade.CommandExecutor;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

@SpringBootTest
@ContextConfiguration(classes = ApiApplication.class)
public class CommandsTests {

    @Test
    public void ADDTest(@Autowired CommandExecutor executor) {
        {
            Registers registers = new Registers();

            int expected = 28;
            int got;

            registers.setIntRegister(1, 15);
            registers.setIntRegister(2, 13);
            executor.execute("add", Arrays.array(registers), Arrays.array(0, 1, 2));

            got = registers.getIntRegister(0);

            Assert.isTrue(got == expected, got + " != " + expected);

            expected = 0;
            registers.setIntRegister(1, 28);
            executor.execute("sub", Arrays.array(registers), Arrays.array(2, 0, 1));

            got = registers.getIntRegister(2);

            Assert.isTrue(got == expected, got + " != " + expected);
            Assert.isTrue(registers.getFlag(Flag.ZeroResult), "Zero result flag must be true");
        }
    }
    
}
