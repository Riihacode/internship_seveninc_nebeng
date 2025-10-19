import { useNavigate } from "react-router-dom";
import useAuth from "../hooks/useAuth";

export default function Home() {
  const { user, logout } = useAuth();
  const navigate = useNavigate();
  const handleLogout = async () => {
    try {
      await logout();
      navigate("/login");
    } catch (error) {
      alert("Logout gagal, coba lagi");
      console.log(error);
    }
  };

  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gray-100">
      <h1 className="text-3xl mb-4">Selamat datang, {user?.name} 👋</h1>
      <button
        onClick={handleLogout}
        className="bg-red-500 text-white px-4 py-2 rounded"
      >
        Logout
      </button>
    </div>
  );
}
