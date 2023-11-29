<script setup lang="ts">
  defineProps<{
    message: String,
  }>();

</script>

<template>
  <v-sheet class="mx-10 my-5 pa-5 rounded-lg">
    <v-card>
      <v-card-title>
        {{ 'Произошла ошибка' }}
      </v-card-title>
      <v-card-text>
        {{ message }}
      </v-card-text>
    </v-card>
  </v-sheet>
</template>

<style scoped>
</style>