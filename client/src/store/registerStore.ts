import {defineStore} from "pinia";
import {computed, ref} from "vue";

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
};

const useRegisterStore = defineStore('registers', () => {
    const registers: ref<Registers> = ref(new Registers());

    const flags = computed( () => {
        return {
            U: registers.value.flags & ( 1 << 0 ),
            T: registers.value.flags & ( 1 << 1 ),
            I: registers.value.flags & ( 1 << 2 ),
            O: registers.value.flags & ( 1 << 3 ),
            S: registers.value.flags & ( 1 << 4 ),
            C: registers.value.flags & ( 1 << 5 ),
            Z: registers.value.flags & ( 1 << 6 ),
            '-': registers.value.flags & ( 1 << 7 ),
        };
    } );

    function setRegs(regs: Registers) {
        registers.value = regs;
    }

    return {registers, flags, setRegs}
});

export { useRegisterStore };