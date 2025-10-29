import useAuth from "../hooks/useAuth";
import { useLocation } from "react-router-dom";

export default function Header() {
  const { user } = useAuth();
  const location = useLocation();

  const getPageTitle = () => {
    const path = location.pathname;

    if (path === "/mitra") return "Data Mitra";
    if (path.startsWith("/mitra/")) return "Data Detail Mitra";
    if (path === "/mitra") return "Data Customer";
    if (path.startsWith("/customer/")) return "Data Detail Customer";
    if (path.startsWith("/pesanan")) return "Data Pesanan";
    if (path.startsWith("/refund")) return "Data Refund";
    if (path.startsWith("/laporan")) return "Data Laporan";
  };
  return (
    <>
      <header className="bg-gray-100 dark:bg-gray-400 dark:text-white py-3 h-17 text-end w-full border-b border-gray-200 flex items-center justify-between">
        <div className="pl-9">
          <h1 className="text-2xl font-bold">{getPageTitle()}</h1>
        </div>
        <div className="flex items-center">
          <div className="flex items-center space-x-3">
            <img
              src={user?.image || "https://placehold.co/600x400"}
              alt="User Avatar"
              className="w-10 h-10 rounded-full object-cover border border-gray-300"
            />
          </div>
          <div className="flex flex-col p-3">
            <span className="font-semibold flex capitalize">{user?.name}</span>
            <span className="text-xs flex capitalize text-gray-500">
              {user?.user_type}
            </span>
          </div>
        </div>
      </header>
    </>
  );
}
