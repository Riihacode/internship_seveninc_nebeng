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

const refundService = {
  getAll: async ({ page = 1, search = "", status = "" }) => {
    try {
      const params = new URLSearchParams();

      params.append("page", page);

      if (search) params.append("search", search);
      if (status) params.append("status", status);

      const res = await api.get(`/api/admin/refunds?${params.toString()}`);
      return res.data;
    } catch (error) {
      handleError(error);
    }
  },

  getById: async (type, id) => {
    try {
      const res = await api.get(`/api/admin/refunds/${type}/${id}`);
      console.log("Data refund dari service", res);
      return res.data;
    } catch (error) {
      handleError(error);
    }
  },
};

export default refundService;
