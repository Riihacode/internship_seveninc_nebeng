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
      const res = await api.get("/api/customers");
      return res.data?.data;
    } catch (error) {
      handleError(error);
    }
  },

  getById: async (id) => {
    try {
      const res = await api.get(`/api/customers/${id}`);
      return res.data;
    } catch (error) {
      handleError(error);
    }
  },

  getByUserId: async (user_id) => {
    try {
      const res = await api.get(`/api/customers/user/${user_id}`);
      return res.data;
    } catch (error) {
      handleError(error);
    }
  },

  create: async (data) => {
    try {
      const res = await api.post("/api/customers", data);
      return res.data;
    } catch (error) {
      handleError(error);
    }
  },

  update: async (id, data) => {
    try {
      const res = await api.put(`/api/customers/${id}`, data);
      return res.data;
    } catch (error) {
      handleError(error);
    }
  },

  verify: async (id) => {
    try {
      const res = await api.patch(`/api/verify/${id}`);
      return res.data;
    } catch (error) {
      handleError(error);
    }
  },

  addCredit: async (id, data) => {
    try {
      const res = await api.patch(`/api/add-credit/${id}`, data);
      return res.data;
    } catch (error) {
      handleError(error);
    }
  },

  deductCredit: async (id, data) => {
    try {
      const res = await api.patch(`/api/add-credit/${id}`, data);
      return res.data;
    } catch (error) {
      handleError(error);
    }
  },

  remove: async (id) => {
    try {
      const res = await api.delete(`/api/customers/${id}`);
      return res.data;
    } catch (error) {
      handleError(error);
    }
  },
};

export default driverService;
