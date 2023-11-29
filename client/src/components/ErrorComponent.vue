<script setup lang="ts">
  defineProps<{
    errorMessage: string,
  }>();

</script>

<template>
  <v-sheet class="mx-10 my-5 pa-5 rounded-lg">
    <v-card>
      <v-card-title class="text-center text-uppercase">
        {{ 'Произошла ошибка' }}
      </v-card-title>
      <v-card-text class="text-grey-lighten-1">
        {{ errorMessage }}
      </v-card-text>
    </v-card>
  </v-sheet>
</template>

<style scoped>
</style>