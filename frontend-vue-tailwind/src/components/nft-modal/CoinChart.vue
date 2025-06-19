<template>
  <Line :data="chartData" :options="chartOptions" />
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { Line } from 'vue-chartjs'
import {
  Chart as ChartJS,
  LineElement,
  PointElement,
  LinearScale,
  CategoryScale,
  Title,
  Tooltip,
  Legend
} from 'chart.js'

ChartJS.register(LineElement, PointElement, LinearScale, CategoryScale, Title, Tooltip, Legend)

const chartData = ref({
  labels: [],
  datasets: [{
    label: 'ETH Price (USD)',
    data: [],
    borderColor: 'rgba(255, 99, 132, 1)',
    tension: 0.3
  }]
})

const chartOptions = {
  responsive: true,
  plugins: {
    legend: { position: 'top' },
    title: { display: true, text: 'ETH Price (7 days)' }
  }
}

onMounted(async () => {
  const res = await fetch('https://api.coingecko.com/api/v3/coins/ethereum/market_chart?vs_currency=usd&days=7')
  const json = await res.json()

  const prices = json.prices
  chartData.value.labels = prices.map(item => new Date(item[0]).toLocaleDateString())
  chartData.value.datasets[0].data = prices.map(item => item[1])
})
</script>
