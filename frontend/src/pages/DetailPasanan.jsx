import { useParams } from "react-router-dom";
import Layout from "../components/Layout";
import { useOrders } from "../hooks/useOrders";

export default function DetailPesanan() {
  const { id } = useParams();
  const { orders, error, loading } = useOrders();

  const order = Array.isArray(orders)
    ? orders?.find((d) => d.id === parent(id))
    : null;
  return (
    <Layout>
      {loading ? (
        <svg
          className="w-6 h-6 mx-auto text-gray-500 animate-spin"
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
        >
          <circle
            className="opacity-25"
            cx="12"
            cy="12"
            r="10"
            stroke="currentColor"
            strokeWidth="4"
          ></circle>
          <path
            className="opacity-75"
            fill="currentColor"
            d="M4 12a8 8 0 018-8v4a4 4 0 00-4 4H4z"
          ></path>
        </svg>
      ) : error ? (
        <h1 className="text-center py-4 text-gray-500 dark:text-neutral-400">
          Terjadi Kesalahan saat memuat data
          <br />
          <span className="text-sm text-gray-400">
            {error.message || "Coba lagi nanti"}
          </span>
        </h1>
      ) : !order ? (
        <h1 className="text-center py-4 text-gray-500 dark:text-neutral-400">
          Tidak ada data
        </h1>
      ) : (
        <div className="">
          <p>sdksad</p>
        </div>
      )}
    </Layout>
  );
}
