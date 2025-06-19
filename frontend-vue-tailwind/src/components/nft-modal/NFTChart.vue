<template>
  <Line :data="chartData" :options="chartOptions" />
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { Line } from 'vue-chartjs'
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  LineElement,
  PointElement,
  LinearScale,
  CategoryScale
} from 'chart.js'

ChartJS.register(Title, Tooltip, Legend, LineElement, PointElement, LinearScale, CategoryScale)

const chartData = ref({
  labels: [],
  datasets: [{
    label: 'NFT Price (ETH)',
    data: [],
    borderColor: 'rgba(75, 192, 192, 1)',
    tension: 0.3
  }]
})

const chartOptions = ref({
  responsive: true,
  plugins: {
    legend: {
      position: 'top'
    },
    title: {
      display: true,
      text: 'NFT Price Over Time'
    }
  }
})

onMounted(async () => {
  const res = await fetch('https://api.reservoir.tools/sales/v5?contract=0x...&tokenId=123')
  const json = await res.json()

  const labels = []
  const prices = []

  json.sales.forEach(sale => {
    labels.push(new Date(sale.timestamp).toLocaleDateString())
    prices.push(sale.price.amount.decimal)
  })

  chartData.value.labels = labels
  chartData.value.datasets[0].data = prices
})
</script>
