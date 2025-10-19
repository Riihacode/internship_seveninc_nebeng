import api, { getCsrfCookie } from "./api";

// login
export const loginUser = async ({ userIdentifier, password }) => {
  try {
    await getCsrfCookie();
    const response = await api.post("/api/login", {
      userIdentifier,
      password,
    });
    console.log(response);
    return response;
  } catch (error) {
    console.log(error);
  }
};

// logout
export const logoutUser = async () => {
  try {
    await getCsrfCookie();
    const response = await api.post(
      "api/logout",
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
