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

const orderService = {
  getAll: async ({ page = 1, search = "", status = "" }) => {
    try {
      const params = new URLSearchParams();

      params.append("page", page);

      if (search) params.append("search", search);
      if (status) params.append("status", status);

      const res = await api.get(`/api/orders?${params.toString()}`);
      // console.log("✅ Response dari /api/orders:", res.data);
      return res.data;
    } catch (error) {
      handleError(error);
    }
  },

  getOrderById: async (type, id) => {
    try {
      const res = await api.get(`/api/orders/${type}/${id}`);
      return res.data;
    } catch (error) {
      handleError(error);
    }
  },

  // getByDriver: async (driver_id) => {
  //   try {
  //     const res = await api.get(`/api/passenger-rides/${driver_id}`);
  //     return res.data;
  //   } catch (error) {
  //     handleError(error);
  //   }
  // },

  // getByStatus: async (status) => {
  //   try {
  //     const res = await api.get(`/api/passenger-rides/${status}`);
  //     return res.data;
  //   } catch (error) {
  //     handleError(error);
  //   }
  // },

  // create: async (data) => {
  //   try {
  //     const res = await api.post("/api/passenger-rides", data);
  //     return res.data;
  //   } catch (error) {
  //     handleError(error);
  //   }
  // },

  // update: async (id, data) => {
  //   try {
  //     const res = await api.put(`/api/passenger-rides/${id}`, data);
  //     return res.data;
  //   } catch (error) {
  //     handleError(error);
  //   }
  // },

  // remove: async (id) => {
  //   try {
  //     const res = await api.delete(`/api/passenger-rides/${id}`);
  //     return res.data;
  //   } catch (error) {
  //     handleError(error);
  //   }
  // },
};

export default orderService;
