import api from "./api";

function handleError(error) {
  if (error.response) {
    const data = error.response.data;

    // lempar data lengkap
    return Promise.reject({
      message: data.message || "Terjadi kesalahan di server",
      errors: data.errors || data.error || null,
      status: error.response.status,
    });
  } else if (error.request) {
    return Promise.reject({
      message: "Server tidak merespon. Periksa koneksi anda",
      errors: null,
      status: null,
    });
  } else {
    return Promise.reject({
      message: "Terjadi kesalahan tidak diketahui",
      errors: null,
      status: null,
    });
  }
}

// login
export const loginUser = async ({ userIdentifier, password }) => {
  try {
    const response = await api.post("/api/login", {
      userIdentifier,
      password,
    });
    console.log(response);
    return response.data;
  } catch (error) {
    console.log("Error di service auth: ", error);
    return handleError(error);
  }
};

// logout
export const logoutUser = async () => {
  try {
    const response = await api.post(
      "/api/logout",
      {},
      {
        withCredentials: true,
      }
    );
    return response;
  } catch (error) {
    if (error.response?.status === 401) {
      // Anggap logout sukses, jangan throw
      return { data: { message: "Logged out" } };
    }
    handleError(error);
  }
};
