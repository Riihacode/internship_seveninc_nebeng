import api from "./api";
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
    console.log("Login error:", error.response?.status, error.response?.data);
    throw error;
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
    throw error;
  }
};
