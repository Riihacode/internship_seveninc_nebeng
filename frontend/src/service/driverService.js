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

const driverService = {
  getAll: async () => {
    try {
      const res = await api.get("/api/drivers");
      return res.data?.data;
    } catch (error) {
      handleError(error);
    }
  },

  getById: async (id) => {
    try {
      const res = await api.get(`/api/drivers/${id}`);
      return res.data;
    } catch (error) {
      handleError(error);
    }
  },

  create: async (data) => {
    try {
      const res = await api.post("/api/drivers", data);
      return res.data;
    } catch (error) {
      handleError(error);
    }
  },

  update: async (id, data) => {
    try {
      const res = await api.put(`/api/drivers/${id}`, data);
      return res.data;
    } catch (error) {
      handleError(error);
    }
  },

  verify: async (id, data) => {
    try {
      const res = await api.patch(`/api/drivers/${id}/verify`, data);
      return res.data;
    } catch (error) {
      handleError(error);
      return null;
    }
  },

  remove: async (id) => {
    try {
      const res = await api.delete(`/api/drivers/${id}`);
      return res.data;
    } catch (error) {
      handleError(error);
    }
  },
};

export default driverService;
