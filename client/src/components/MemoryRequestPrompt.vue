<script setup lang="ts">
  import {useMemoryStore} from "../store/memoryStore.ts";
  import {useRouter} from "vue-router";
  import {ref} from "vue";

  const memoryStore = useMemoryStore();
  const router = useRouter();

  const sizeRule = ref(
    input => {
    const pattern = /^[0-9]+$/;
    return pattern.test(input) || 'Введите корректное число.';
  });
  const size: ref<number> = ref(1);
  const valid: ref<boolean> = ref(false);

  async function onSubmit() {
    let err: string | null = null;
    let res = await memoryStore.allocateMemory(size.value)
        .catch( error => {
          const response = error.response;
          if(response == undefined) {
            err = 'Server is unavailable';
          } else {
            err = response.data.message;
        }
    } );

    if (err === null) {
      await router.push({name: 'Processor'});
    } else {
      await router.push({name: 'Error'});
    }
  }

</script>

<template>
  <v-sheet class="mx-10 my-5 pa-5 rounded-lg">
    <v-row justify="center" class="text-grey-lighten-1">
      <v-col cols="6" class="text-center">
        Укажите размер памяти, который вы хотите получить для работы, в килобайтах.
      </v-col>
    </v-row>

    <v-form @submit.prevent="onSubmit" v-model="valid">

      <v-row justify="center">
        <v-col cols="6">
          <v-text-field label="Размер"
                        hint="Введите размер"
                        v-model="size"
                        suffix="Кб"
                        :rules="[sizeRule]">
          </v-text-field>
        </v-col>
      </v-row>

      <v-row>
        <v-col>
          <v-btn :block="true"
                 variant="text"
                 :disabled="!valid"
                 type="submit">
            Продолжить
          </v-btn>
        </v-col>
      </v-row>

    </v-form>
  </v-sheet>
</template>

<style scoped>

</style>