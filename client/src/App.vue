<script setup lang="ts">
  import InformationPrompt from "./components/auxiliary/InformationPrompt.vue";
  import {useInfoStore} from "./store/InfoStore.ts";

  const info = useInfoStore().info;
</script>

<template>
  <v-app>
    <v-app-bar>
      <v-row no-gutters>
        <v-col cols="6">
          <v-app-bar-title class="ml-10 text-amber text-center">
            <h1>Процессор</h1>
          </v-app-bar-title>
        </v-col>

        <v-col>
          <v-btn :block="true" color="amber-darken-1">
            {{ info['help'].title }}
            <InformationPrompt :title="info['help'].title" :text="info['help'].text"/>
          </v-btn>
        </v-col>

        <v-col>
          <v-btn :block="true" color="amber-darken-1">
            {{ info['about'].title }}
            <InformationPrompt :title="info['about'].title" :text="info['about'].text"/>
          </v-btn>
        </v-col>

      </v-row>
    </v-app-bar>

    <v-main>
      <router-view v-slot="{ Component }">
        <transition>
          <component :is="Component"></component>
        </transition>
      </router-view>
    </v-main>
  </v-app>
</template>

<style scoped>
</style>
