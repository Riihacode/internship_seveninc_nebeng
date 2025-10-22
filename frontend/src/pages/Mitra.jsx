import { useNavigate } from "react-router-dom";
import useAuth from "../hooks/useAuth";
import SideBar from "../components/SideBar";

export default function Mitra() {
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
    <>
      <div className="container flex min-h-screen">
        <SideBar />
        <div className="flex-1 flex flex-col items-center justify-center bg-gray-100">
          <h1 className="text-3xl mb-4">
            Selamat datang, {user?.name} 👋 di halaman mitra
          </h1>
          <button
            onClick={handleLogout}
            className="bg-red-500 text-white px-4 py-2 rounded"
          >
            Logout
          </button>
        </div>
      </div>
    </>
  );
}
