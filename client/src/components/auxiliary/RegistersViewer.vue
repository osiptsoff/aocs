<script setup lang="ts">
  import { useRegisterStore } from "../../store/registerStore.ts";

  const registerStore = useRegisterStore();

  const registers = registerStore.registers;
  const flags = registerStore.flags;
</script>

<template>
  <v-card class="flex-fill">
    <v-card-title class="text-center">
      Регистры
    </v-card-title>
    <v-card-text>
      <v-row>
        <v-col cols="6">
            <v-container class="text-center">
              Целочисленные
            </v-container>
          <v-table height="20vmax" fixed-header>
            <thead>
            <tr>
              <th>Номер</th>
              <th>Значение</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="(val, idx) in registers.intRegs">
              <td>{{ idx.toString(16) }}</td>
              <td>{{ (val >>> 0).toString(16) }}</td>
            </tr>
            </tbody>
          </v-table>
        </v-col>

        <v-col cols="6">
          <v-container class="text-center">
            С плав. точкой
          </v-container>
          <v-table height="20vmax" fixed-header>
            <thead>
            <tr>
              <th>Номер</th>
              <th>Значение</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="(idx, val) in registers.floatRegs">
              <td>{{ (val >>> 0).toString(16) }}</td>
              <td>{{ idx.toString(16) }}</td>
            </tr>
            </tbody>
          </v-table>
        </v-col>
      </v-row>

      <v-row>
        <v-col>
          <v-container class="text-center">
            Программный счётчик
          </v-container>
          <v-container class="text-center">
            {{ registers.programCounter.toString(16) }}
          </v-container>
        </v-col>

        <v-col>
          <v-container class="text-center">
            Флаги
          </v-container>
          <v-table>
            <thead>
              <tr>
                <th v-for="(val, name) in flags">
                  {{ name }}
                </th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td v-for="(val, name) in flags">
                  {{ val }}
                </td>
              </tr>
            </tbody>
          </v-table>
        </v-col>
      </v-row>
    </v-card-text>
  </v-card>
</template>

<style scoped>

</style>