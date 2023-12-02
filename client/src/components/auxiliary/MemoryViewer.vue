<script setup lang="ts">
  import {useMemoryStore} from "../../store/memoryStore.ts";
  import {ref} from "vue";
  import ErrorComponent from "../ErrorComponent.vue";

  const memoryStore = useMemoryStore();

  const memory = memoryStore.memory;
  const sizeKb = memoryStore._size;

  const successfulQuery = ref<boolean>(true);
  const errorMessage = ref<string>('');
  function onMemSelect(kbnum: number, toggle: () => void ) : void {
    toggle();

    memoryStore.queryMemory(kbnum)
        .then( () => {
            successfulQuery.value = true;
        })
        .catch( error => {
          const response = error.response;

          successfulQuery.value = false;

          if(response == undefined) {
            errorMessage.value =  'Сервер недоступен';
          } else {
            errorMessage.value = 'Не удалось получить память.';
        }
    })
  }

</script>

<template>
  <v-card>
    <v-card-title class="text-center fill-height">
      Память
    </v-card-title>
    <v-card-text>
          <v-slide-group show-arrows mandatory>
            <v-slide-group-item
                v-for="num in parseInt(sizeKb)"
                :key="num - 1"
                v-slot="{ isSelected, toggle }">

              <v-card @click="onMemSelect((num - 1) * 1024, toggle)" class="mx-5 my-2">
                <v-card-text :class="isSelected ? 'text-green' : ''">
                  {{ 'Килобайт ' + (num - 1) }}
                </v-card-text>
              </v-card>
            </v-slide-group-item>
          </v-slide-group>

        <v-fade-transition>
          <v-table height="40vmax" v-if="successfulQuery" fixed-header>
            <thead>
            <tr>
              <th>Адрес</th>
              <th>Значение</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="(value, addr) in memory">
              <td>{{ Number(addr).toString(16) }}</td>
              <td>{{ Number(value >>> 0).toString(16) }}</td>
            </tr>
            </tbody>
          </v-table>

          <ErrorComponent :error-message="errorMessage" v-else>

          </ErrorComponent>
        </v-fade-transition>


    </v-card-text>
  </v-card>
</template>

<style scoped>

</style>