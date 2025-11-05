import api from "../api/api";

function handleError(error) {
  if (error.response) {
    const msg = error.response.data?.message || "Terjadi kesalahan di server";
    console.error("Server Error", msg);
    throw new Error(msg);
  } else if (error.request) {
    console.error("Tidak ada response dari server");
    throw new Error("Server tidak merespon. Periksa koneksi anda");
  } else {
    console.error("Error:", error.message);
    throw new Error("Teradi kesalahan tidak diketahui");
  }
}

const transactionService = {
  getAll: async () => {
    try {
      const res = await api.get("/api/transactions");
      return res.data?.data;
    } catch (error) {
      handleError(error);
    }
  },

  getById: async (type, id) => {
    try {
      const res = await api.get(`/api/transactions/${type}/${id}`);
      return res.data;
    } catch (error) {
      handleError(error);
    }
  },

  getByBooking: async (type, bookingId) => {
    try {
      const res = await api.get(
        `/api/transactions/${type}/booking/${bookingId}`
      );
      return res.data;
    } catch (error) {
      handleError(error);
    }
  },
};

export default transactionService;
