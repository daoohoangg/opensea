import axios from 'axios';

const PINATA_API_KEY = 'your_api_key_here';
const PINATA_SECRET_API_KEY = 'your_secret_api_key_here';

const pinFileToIPFS = async (file) => {
  const url = `https://api.pinata.cloud/pinning/pinFileToIPFS`;
  const formData = new FormData();
  formData.append('file', file);

  const res = await axios.post(url, formData, {
    maxBodyLength: 'Infinity',
    headers: {
      'Content-Type': 'multipart/form-data',
      pinata_api_key: PINATA_API_KEY,
      pinata_secret_api_key: PINATA_SECRET_API_KEY
    }
  });

  return res.data; // sẽ chứa CID
};

const pinJSONToIPFS = async (json) => {
  const url = `https://api.pinata.cloud/pinning/pinJSONToIPFS`;

  const res = await axios.post(url, json, {
    headers: {
      pinata_api_key: PINATA_API_KEY,
      pinata_secret_api_key: PINATA_SECRET_API_KEY,
      'Content-Type': 'application/json'
    }
  });

  return res.data;
};

export { pinFileToIPFS, pinJSONToIPFS };
