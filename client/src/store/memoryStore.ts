import { defineStore } from "pinia";
import {computed, ref} from "vue";
import axios from "axios";
import {config} from "../config.ts";

const useMemoryStore = defineStore('memory', () => {
    const _memory: ref< { [address : string] : string } > = ref();
    const _currentKb: ref<number> = ref();
    const _key: ref<string> = ref();
    const _size: ref<number> = ref();

    const memory = computed( () => _memory );

     function allocateMemory(size: number) :  Promise<boolean | string> {
        return axios.post( config.memAllocateUrl, {}, {
            headers: {
                Accept: 'application/json',
                size: size,
            }
        } )
            .then( ({ data }) => {
                _key.value = data['id'];
                _size.value = size;
                _currentKb.value = 0;
                return true;
            })
            .catch( error => {
                const response = error.response;
                if(response == undefined) {
                    return 'Server is unavailable';
                } else {
                    return response.data.message;
                }
            } )
    }

     function deallocateMemory() : Promise<void> {
        return axios.delete( config.memAllocateUrl, {
            headers: {
                Accept: 'application/json',
                id: _key
            }
        })
            .then( () => {
                this.$reset();
            } );
    }

     function queryMemory(kbnum: number) : Promise<boolean | string> {
       return axios.get(config.memGetUrl, {
            headers: {
                Accept: 'application/json',
                addr: kbnum,
                id: _key.value,
            },
        })
            .then( ({ data }) => {
                _memory.value = data;
                _currentKb.value = kbnum;
                return true;
            } )
            .catch( error => {
                const response = error.response;
                if(response == undefined) {
                    return 'Сервер недоступен';
                } else {
                    return'Не удалось получить память.';
                }
            })
    }

    return { _memory, _currentKb, _key, _size, memory, allocateMemory, deallocateMemory, queryMemory };
});

export { useMemoryStore };