<script setup lang="ts">
  import InformationPrompt from "./components/auxiliary/InformationPrompt.vue";
  import {useInfoStore} from "./store/infoStore.ts";

  const info = useInfoStore().info;
</script>

<template>
  <v-app>
    <v-app-bar>
      <v-row no-gutters>
        <v-col cols="6">
          <v-app-bar-title class="ml-10 text-amber text-center">
            <h2>Процессор</h2>
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
        <transition name="router"
                    mode="out-in">
          <component :is="Component" errorMessage="Ресурс недоступен"></component>
        </transition>
      </router-view>
    </v-main>
  </v-app>
</template>

<style scoped>
  .router-enter-active {
    animation: enter 0.6s;
  }
  .router-leave-active {
    animation: leave 0.3s;
  }
  .router-leave-to {
    position: fixed;
  }

  @keyframes enter {
    from{ transform: translateX(100%); }
    to{ transform: translateX(0); }
  }

  @keyframes leave {
    from{ transform: translateX(0); }
    to{ transform: translateX(-100%); }
  }
</style>
