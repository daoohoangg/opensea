<template>
  <div
    class="rounded-xl border border-zinc-700 bg-zinc-900 p-3 flex items-center gap-3 w-full max-w-[500px]"
  >
    <img
      :src="item.image"
      alt="logo"
      class="w-10 h-10 rounded-full object-cover"
    />
    <div class="flex-1">
      <div class="text-white font-semibold truncate">
        {{ item.name }}
        <span class="text-zinc-400 font-normal text-sm"> {{ item.symbol }}</span>
      </div>
      <div class="text-xs text-zinc-300">
        {{ item.priceDisplay }}
        <span
          :class="item.change >= 0 ? 'text-green-400' : 'text-red-400'"
          class="ml-1 font-semibold"
        >
          {{ item.change >= 0 ? '+' : '' }}{{ item.change }}%
        </span>
      </div>
    </div>
    <img :src="item.sparkline" class="w-14 h-6" />
  </div>
</template>

<script setup>
defineProps({
  item: Object,
});
</script>
