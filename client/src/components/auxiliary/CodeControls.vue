<script setup lang="ts">
  import {useCommandStore} from "../../store/commandStore.ts";
  import {computed, ref, watch} from "vue";
  import ErrorComponent from "../ErrorComponent.vue";
  import {VFileInput} from "vuetify/components";

  const commandStore = useCommandStore();

  const currentCommand = computed( () => commandStore.currentCommand);

  const started = ref<boolean>(false);
  const errorMsg = ref<string>('');
  const successfulQuery = ref<boolean>(true);
  const input = ref<string>('');

  const files = ref<[File]>([]);
  const fileInputRef = ref<InstanceType<VFileInput>>();

  function onStart() : void {
    started.value = true;
    commandStore.parseAndSet(input.value);
  }

  function onNextStep() : void {
    commandStore
        .executeNext()
        .then( result => {
          if( !result ) {
            started.value = false;
            commandStore.$reset();
          }
          successfulQuery.value = true;
        })
        .catch( error => {
          const response = error.response;

          successfulQuery.value = false;

          if(response == undefined) {
            errorMsg.value =  'Сервер недоступен';
          } else {
            errorMsg.value =  response.data.message;
          }
        });
  }

  function onStop() : void {
    started.value = false;
    successfulQuery.value = true;
    commandStore.$reset();
  }

   function onLoad() : void {
    fileInputRef.value.click()
  }

  watch(files, () => {
    if(files.value[0].size > 1048576) {
      input.value = 'Файл слишком большой (допустим размер до 1 Мб)';
      return;
    }

    files.value[0]
        .text()
        .then( res => {
          input.value = res;
        })
  })
</script>

<template>
  <v-card class="flex-fill">
    <v-card-title class="text-center">
      Код программы
    </v-card-title>
    <v-card-text>
      <v-textarea label="Код"
                  :clearable="true"
                  v-model="input"
                  :disabled="started">
      </v-textarea>

        <v-fade-transition v-if="started" mode="out-in">
          <v-table v-if="successfulQuery" class="mb-5">
            <thead>
            <tr>
              <td>Команда</td>
              <td>Аргументы</td>
            </tr>
            </thead>

            <tbody>
            <tr>
              <td>{{ currentCommand.commandName }}</td>
              <td>{{ currentCommand.commandArgs.map( num => num.toString(16) ).join(' ') }}</td>
            </tr>
            </tbody>
          </v-table>

          <v-container v-else>
            <ErrorComponent :error-message="errorMsg">

            </ErrorComponent>
          </v-container>
        </v-fade-transition>

      <v-row>
        <v-col>
          <v-btn :block="true"
                 :disabled="started"
                 @click="onStart">
            Начать
          </v-btn>
        </v-col>

        <v-col>
          <v-btn :block="true"
                 :disabled="!started || !successfulQuery"
                 @click="onNextStep">
            Следующий шаг
          </v-btn>
        </v-col>

        <v-col>
          <v-btn :block="true"
                 :disabled="!started"
                 @click="onStop">
            Сброс
          </v-btn>
        </v-col>

        <v-col>
          <v-btn :block="true"
                 :disabled="started"
                 @click="onLoad">
            <v-file-input v-show="false"
                          ref="fileInputRef"
                          accept="text/*"
                          v-model="files">
            </v-file-input>
            Загрузить с диска
          </v-btn>
        </v-col>
      </v-row>
    </v-card-text>
  </v-card>
</template>

<style scoped>

</style>