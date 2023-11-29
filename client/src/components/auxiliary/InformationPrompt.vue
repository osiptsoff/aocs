<script setup lang="ts">
  defineProps<{
    title: string,
    text: Array<string>,
  }>();
</script>

<template>
  <v-dialog activator="parent">
    <template v-slot:default="{ isActive }">
      <v-card>
        <v-card-title class="text-uppercase text-center">
          {{ title }}
        </v-card-title>
        <v-card-text>
          <v-list class="text-grey-lighten-1">
            <v-list-item v-for="paragraph in text">
              {{ paragraph }}
            </v-list-item>
          </v-list>
        </v-card-text>

        <v-spacer></v-spacer>
        <v-card-actions>
          <v-btn @click="isActive.value = false" :block="true">
            Закрыть
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<style scoped>

</style>