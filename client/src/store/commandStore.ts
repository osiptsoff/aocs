import {defineStore} from "pinia";
import {computed, ref} from "vue";
import {useRegisterStore} from "./registerStore.ts";
import {useMemoryStore} from "./memoryStore.ts";
import axios from "axios";
import {config} from "../config.ts";

class Command {
    commandName: string;
    commandArgs: Array<number>;
}

const useCommandStore = defineStore('command', () => {
    const registerStore = useRegisterStore();
    const memoryStore = useMemoryStore();

    const commands = ref<Array<Command>>([]);

    const currentCommandNumber = computed( () => registerStore.registers.programCounter);
    const currentCommand= computed<Command>( () => {
        let res = commands.value[currentCommandNumber.value]

        if(res === undefined)
            return { commandName: 'Нет команды', commandArgs: []};

        return res;
    } );

    function parseAndSet(commandsRaw: string) : void {
        commands.value = commandsRaw
            .split('\n')
            .filter( row => !/\s\s+/g.test(row))
            .map( row => row.replace(/\s\s+/g, ' ') )
            .map( row => row.split(' ') )
            .map( row => {
                return {
                    commandName: row[0].toLowerCase(),
                    commandArgs: row.slice(1).map(arg => Number.parseInt(arg, 16))
                }
            } );
    }

    async function executeNext() : Promise<boolean> {
        if(currentCommandNumber.value >= commands.value.length )
            return false;

        let command = commands.value[currentCommandNumber.value];

        return axios.post(config.commandExecuteUrl, {
            commandName: command.commandName,
            commandArgs: command.commandArgs,
            registers: registerStore.registers,
        }, {
            headers: {
                Accept: 'application/json',
                id: memoryStore._key,
            }
        })
            .then( ({ data }) => {
                registerStore.setRegs(data);
                return true;
            });
    }

    function $reset() {
        commands.value = [];
        registerStore.registers.programCounter = 0;
    }

    return {commands, currentCommandNumber, currentCommand, parseAndSet, executeNext, $reset}
});

export {useCommandStore};