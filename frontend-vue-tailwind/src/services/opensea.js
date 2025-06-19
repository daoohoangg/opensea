import axios from 'axios';

const OPENSEA_API_KEY = process.env.OPENSEA_API_KEY;
const API_URL = 'https://api.opensea.io/api/v2/events';

export async function getNFTSalesHistory(contractAddress, tokenId) {
  try {
    const res = await axios.get(API_URL, {
      headers: {
        'x-api-key': OPENSEA_API_KEY
      },
      params: {
        asset_contract_address: contractAddress,
        token_id: tokenId,
        event_type: 'successful',
        limit: 20
      }
    });

    return res.data.asset_events.map(event => ({
      price: Number(event.payment.amount) / 1e18, // Giả định ETH, cần xử lý token decimals cụ thể hơn
      date: new Date(event.created_date)
    }));
  } catch (err) {
    console.error('Failed to fetch NFT history:', err);
    return [];
  }
}
