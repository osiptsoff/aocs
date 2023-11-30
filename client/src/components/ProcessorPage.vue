<script setup lang="ts">

import CodeControls from "./auxiliary/CodeControls.vue";
import RegistersViewer from "./auxiliary/RegistersViewer.vue";
import MemoryViewer from "./auxiliary/MemoryViewer.vue";
</script>

<template>
  <v-sheet class="mx-10 my-5 pa-5 rounded-lg">
    <v-row>
      <v-col cols="7">
        <v-row>
          <CodeControls>

          </CodeControls>
        </v-row>

        <v-spacer>
        </v-spacer>

        <v-row class="mt-10">
          <RegistersViewer>

          </RegistersViewer>
        </v-row>
      </v-col>

      <v-col cols="5">
        <MemoryViewer>

        </MemoryViewer>
      </v-col>
    </v-row>
  </v-sheet>
</template>

<style scoped>

</style>