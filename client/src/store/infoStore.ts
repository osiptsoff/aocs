import { defineStore } from "pinia";
import {ref} from "vue";

const useInfoStore = defineStore('info', () => {
    const info : ref<{
        [name : string] : {
            title : string,
            text : Array<string>
        }
    }>  = ref({});

    info.value['about'] = {
        title: 'О проекте',
        text: [
            'Данное приложение является результатом выполнения курсовой работы по дисциплине "Архитектуры вычислительных систем."',
            'Представляет собой модель взаимодействия с разработанным в первых главах процессором процессором.',
            'Авторы- студенты группы 0305: Иванов Артём, Осипцов Никита, Пуха Анастасия.',
        ]
    };

    info.value['help'] = {
        title: 'Справка',
        text: [
            'Stub',
        ]
    }

    return { info };
});

export { useInfoStore }