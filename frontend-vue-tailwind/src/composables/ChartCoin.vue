<template>
  <div class="flex items-center">
    <h2 class="text-xl font-bold mb-4">Crypto Price Chart</h2>

    <div class="flex gap-4 mb-4">
      <label>
        Coin:
        <select v-model="selectedCoin" class="border p-1 rounded">
          <option v-for="coin in coins" :key="coin.id" :value="coin.id">
            {{ coin.name }}
          </option>
        </select>
      </label>

      <label>
        Time Range:
        <select v-model="selectedDays" class="border p-1 rounded">
          <option v-for="day in dayOptions" :key="day.value" :value="day.value">
            {{ day.label }}
          </option>
        </select>
      </label>
    </div>

    <LineChart
      v-if="chartData"
      :chart-data="chartData"
      :chart-options="chartOptions"
    />
  </div>
</template>

<script setup>
import { ref, watch, onMounted } from 'vue'
import axios from 'axios'
import { Line } from 'vue-chartjs'
import {
  Chart as ChartJS,
  Title, Tooltip, Legend,
  LineElement, PointElement,
  CategoryScale, LinearScale
} from 'chart.js'

ChartJS.register(
  Title, Tooltip, Legend,
  LineElement, PointElement,
  CategoryScale, LinearScale
)

const LineChart = Line

const prices = res.data.prices
console.log(prices)

// Coin options (có thể thêm bất kỳ coin nào được CoinGecko hỗ trợ)
const coins = [
  { id: 'bitcoin', name: 'Bitcoin (BTC)' },
  { id: 'ethereum', name: 'Ethereum (ETH)' },
  { id: 'solana', name: 'Solana (SOL)' },
  { id: 'dogecoin', name: 'Dogecoin (DOGE)' }
]

const dayOptions = [
  { label: '7 days', value: 7 },
  { label: '30 days', value: 30 },
  { label: '90 days', value: 90 }
]

const selectedCoin = ref('bitcoin')
const selectedDays = ref(30)
const chartData = ref(null)

const chartOptions = {
  responsive: true,
  maintainAspectRatio: false,
}

// Gọi API CoinGecko và xử lý dữ liệu
const fetchChartData = async () => {
  chartData.value = null
  try {
    const res = await axios.get(
      `https://api.coingecko.com/api/v3/coins/${selectedCoin.value}/market_chart`,
      {
        params: {
          vs_currency: 'usd',
          days: selectedDays.value
        }
      }
    )

    const prices = res.data.prices

    chartData.value = {
      labels: prices.map(p => new Date(p[0]).toLocaleDateString()),
      datasets: [
        {
          label: `${coins.find(c => c.id === selectedCoin.value).name} Price (USD)`,
          data: prices.map(p => p[1]),
          fill: false,
          borderColor: 'rgb(75, 192, 192)',
          tension: 0.1
        }
      ]
    }
  } catch (err) {
    console.error('Error fetching chart data:', err)
  }
}

// Fetch khi mount và khi coin hoặc thời gian thay đổi
onMounted(fetchChartData)
watch([selectedCoin, selectedDays], fetchChartData)
</script>

<style scoped>
div {
  height: 480px;
}
</style>
