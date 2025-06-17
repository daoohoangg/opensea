<template>
  <div>
    <h2 class="text-xl font-semibold mb-4">Biểu đồ giá NFT</h2>
    <Line v-if="chartData" :data="chartData" :options="chartOptions" />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { Line } from 'vue-chartjs';
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  LineElement,
  CategoryScale,
  LinearScale,
  PointElement
} from 'chart.js';
import { getNFTSalesHistory } from '@/services/opensea';

ChartJS.register(
  Title,
  Tooltip,
  Legend,
  LineElement,
  CategoryScale,
  LinearScale,
  PointElement
);

const chartData = ref(null);

const chartOptions = {
  responsive: true,
  plugins: {
    legend: { display: false },
    title: { display: true, text: 'NFT Price History (ETH)' }
  }
};

// Thay bằng contract & token ID thật
const contractAddress = '0x...';
const tokenId = '1';

onMounted(async () => {
  const history = await getNFTSalesHistory(contractAddress, tokenId);

  chartData.value = {
    labels: history.map(item => item.date.toLocaleDateString()),
    datasets: [
      {
        label: 'Giá (ETH)',
        data: history.map(item => item.price),
        borderColor: 'rgb(75, 192, 192)',
        fill: false,
        tension: 0.3
      }
    ]
  };
});
</script>
