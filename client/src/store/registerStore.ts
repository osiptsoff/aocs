import {defineStore} from "pinia";
import {ref} from "vue";

class Registers {
    intRegs: Array<number>;
    floatRegs: Array<number>;
    programCounter: number;
    programCounterInterrupt: number;
    flags: number;
    flagsInterrupt: number;

    constructor() {
        this.intRegs = Array(32).fill(0);
        this.floatRegs = Array(32).fill(0);

        this.programCounter = 0;
        this.programCounterInterrupt = 0;
        this.flags = 0;
        this.flagsInterrupt = 0;
    }
}

const useRegisterStore = defineStore('registers', () => {
    const registers = ref<Registers> (new Registers());

    const flags = ref<{}>({
        U: registers.value.flags & ( 1 << 0 ),
        T: registers.value.flags & ( 1 << 1 ),
        I: registers.value.flags & ( 1 << 2 ),
        O: registers.value.flags & ( 1 << 3 ),
        S: registers.value.flags & ( 1 << 4 ),
        C: registers.value.flags & ( 1 << 5 ),
        Z: registers.value.flags & ( 1 << 6 ),
        '-': registers.value.flags & ( 1 << 7 ),
    });

    function setRegs(regs: Registers) : void  {
        registers.value.intRegs = regs.intRegs;
        registers.value.floatRegs = regs.floatRegs;
        registers.value.programCounter = regs.programCounter;
        registers.value.programCounterInterrupt = regs.programCounterInterrupt;
        registers.value.flags = regs.flags;

        flags.value.U =  ( registers.value.flags & ( 1 << 0 ) ) > 0 ? 1 : 0;
        flags.value.T = ( registers.value.flags & ( 1 << 1 ) ) > 0 ? 1 : 0;
        flags.value.I = ( registers.value.flags & ( 1 << 2 ) ) > 0 ? 1 : 0;
        flags.value.O = ( registers.value.flags & ( 1 << 3 ) ) > 0 ? 1 : 0;
        flags.value.S = ( registers.value.flags & ( 1 << 4 ) ) > 0 ? 1 : 0;
        flags.value.C = ( registers.value.flags & ( 1 << 5 ) ) > 0 ? 1 : 0;
        flags.value.Z = ( registers.value.flags & ( 1 << 6 ) ) > 0 ? 1 : 0;
        flags.value['-'] = ( registers.value.flags & ( 1 << 7 ) ) > 0 ? 1 : 0;

        registers.value.flagsInterrupt = regs.flagsInterrupt;
    }

    function $reset() : void {
        setRegs(new Registers());
    }

    return {registers, flags, setRegs, $reset}
});

export { useRegisterStore, Registers };