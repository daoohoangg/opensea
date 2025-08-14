import axios from 'axios';

const api = axios.create({
  baseURL: import.meta.env.VITE_BASE_URL,
  withCredentials: true 
});

api.interceptors.response.use(
  response => response,
  async error => {
    const originalRequest = error.config;

    if (error.response?.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;

      try {
        // Gọi API refresh token (refreshToken lấy từ cookie HTTP-Only)
        const res = await api.post('/v1/auth/refresh');
        const newAccessToken = res.data?.result?.accessToken;

        if (newAccessToken) {
          localStorage.setItem('access_token', newAccessToken);
          originalRequest.headers['Authorization'] = `Bearer ${newAccessToken}`;
          return api(originalRequest);
        }
      } catch (refreshError) {
        console.error('Refresh token failed', refreshError);
        // Logout nếu refresh fail
        localStorage.removeItem('access_token');
        localStorage.removeItem('wallet_address');
      }
    }
    return Promise.reject(error);
  }
);

export default api;
