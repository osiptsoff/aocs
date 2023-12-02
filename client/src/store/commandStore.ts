import {defineStore} from "pinia";
import {computed, ref, UnwrapRef} from "vue";
import {useRegisterStore, Registers} from "./registerStore.ts";
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

    const currentCommandNumber = computed( () => registerStore.registers.programCounter / 4);
    const currentCommand= computed<Command>( () => {
        let res : UnwrapRef<Command> | undefined = commands.value[currentCommandNumber.value]

        if(res === undefined)
            return { commandName: 'Нет команды', commandArgs: []};

        return res;
    } );

    function parseAndSet(commandsRaw: string) : void {
        commands.value = commandsRaw
            .split('\n')
            .filter( row => !/^\s*$/.test(row))
            .filter( row => !row.startsWith('//'))
            .map( row => row.trim())
            .map( row => row.replace(/\s+/g, ' ') )
            .map( row => row.split(' ') )
            .map( row => {
                return {
                    commandName: row[0].toLowerCase(),
                    commandArgs: row.slice(1).map(arg => Number.parseInt(arg, 16))
                }
            } );

        axios.post<void>(config.programStoreUrl, commands.value, {
            headers: {
                Accept: 'application/json',
                id: memoryStore._key,
            }
        })
            .then( () => {} )
            .catch( () => {} );
    }

    async function executeNext() : Promise<boolean> {
        if(currentCommandNumber.value >= commands.value.length )
            return false;

        let command = currentCommand.value;

        return axios.post<Registers>(config.commandExecuteUrl, {
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
        registerStore.$reset();
    }

    return {commands, currentCommandNumber, currentCommand, parseAndSet, executeNext, $reset}
});

export {useCommandStore};