import { useState, useEffect } from "react";
import { logoutUser } from "../api/auth";
import { AuthContext } from "./AuthContext";

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [token, setToken] = useState(null);
  const [loading, setLoading] = useState(true);

  const isTokenValid = (jwtToken) => {
    if (!jwtToken) return false;
    try {
      const payload = JSON.parse(atob(jwtToken.split(".")[1]));
      const isExpired = payload.exp * 1000 < Date.now();
      return !isExpired;
    } catch {
      return false;
    }
  };

  useEffect(() => {
    const initAuth = () => {
      const storedUser = localStorage.getItem("user");
      const storedToken = localStorage.getItem("token");
      if (storedUser && storedToken && isTokenValid(storedToken)) {
        setUser(JSON.parse(storedUser));
        setToken(storedToken);
      } else {
        logout();
      }

      setTimeout(() => setLoading(false), 100);
    };
    initAuth();
  }, []);

  useEffect(() => {
    const handleTokenExpired = () => {
      console.warn("Token Expired");
      logout();
    };

    // Dengarkan event global dari interceptor axios
    window.addEventListener("token-expired", handleTokenExpired);

    return () => {
      window.removeEventListener("token-expired", handleTokenExpired);
    };
  }, []);

  const login = (userData, tokenData) => {
    localStorage.setItem("user", JSON.stringify(userData));
    localStorage.setItem("token", tokenData);
    setUser(userData);
    setToken(tokenData);
  };

  const logout = async () => {
    try {
      await logoutUser();
    } catch (error) {
      console.warn("Logout gagal di server: ", error);
    } finally {
      localStorage.removeItem("user");
      localStorage.removeItem("token");
      setUser(null);
      setToken(null);
    }
  };

  return (
    <AuthContext.Provider
      value={{
        user,
        token,
        login,
        logout,
        loading,
        isAuthenticated: () => isTokenValid(token),
        role: user?.user_type || null,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};
