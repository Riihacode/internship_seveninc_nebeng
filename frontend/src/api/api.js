// halaman konfigurasi axios
import axios from "axios";

const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL || "http://127.0.0.1:8000",
  headers: {
    "Content-Type": "application/json",
    Accept: "application/json",
    "X-Requested-With": "XMLHttpRequest",
  },
});

api.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  console.log("➡️ Sending request to:", config.baseURL + config.url);
  return config;
});

// handle expired token
api.interceptors.response.use(
  (response) => response,
  (error) => {
    console.log("Interceptor error", {
      status: error.response?.status,
      message: error.response?.data?.message,
      errors: error.response?.data?.errors,
      url: error.config?.url,
      method: error.config?.method,
      payload: error.config?.data ? JSON.parse(error.config.data) : null,
    });
    if (error.response && error.response.status === 401) {
      console.warn("Token expired or Unauthorized");
      const currentToken = localStorage.getItem("token");
      if (currentToken) {
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        window.location.href = "/";
      }
    }
    return Promise.reject(error);
  }
);

export default api;
