import { defineStore } from "pinia";
import {computed, ref} from "vue";
import axios from "axios";
import {config} from "../config.ts";

const useMemoryStore = defineStore('memory', () => {
    const _memory: ref< { [address : number] : number } > = ref();
    const _currentKb: ref<number> = ref();
    const _key: ref<string> = ref();
    const _size: ref<number> = ref();

    const memory = computed(() => _memory );

     function allocateMemory(size: number) :  Promise<boolean> {
        return axios.post<{id : string}>( config.memAllocateUrl, {}, {
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
            });
    }

     function deallocateMemory() : Promise<void> {
        return axios.delete<void>( config.memAllocateUrl, {
            headers: {
                Accept: 'application/json',
                id: _key
            }
        })
            .then( () => {
                this.$reset();
            } );
    }

     function queryMemory(kbnum: number) : Promise<boolean> {
       return axios.get<{ [address : number] : number }>(config.memGetUrl, {
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
            } );
    }

    return { _memory, _currentKb, _key, _size, memory, allocateMemory, deallocateMemory, queryMemory };
});

export { useMemoryStore };