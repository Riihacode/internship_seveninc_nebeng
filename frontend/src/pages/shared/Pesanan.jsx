import Layout from "../../components/Layout.jsx";
import { useOrders } from "../../hooks/useOrders.js";
import Table from "../../components/Table.jsx";
import SearchBar from "../../components/SearchBar.jsx";
import dayjs from "dayjs";
import { useNavigate } from "react-router-dom";

export default function Pesanan() {
  const { orders, loading, error,  } = useOrders();
  const navigate = useNavigate();
  const formatTanggal = (tanggal) =>
    tanggal ? dayjs(tanggal).format("DD MMMM YYYY") : "-";

  const columns = [
    {
      label: "No Pesanan",
      align: "center",
      key: "booking_code",
      //render: (_, i) => i + 1,
    },
    {
      label: "Driver",
      key: "driver_name",
    },
    {
      label: "Customer",
      key: "customer_name",
    },
    {
      label: "Tanggal",
      render: (row) => (row.created_at ? formatTanggal(row.created_at) : "-"),
    },
    // { label: "Layanan", render: (d) => d.layanan },
    {
      label: "Layanan",
      render: (d) => {
        if (d.layanan === "umum" || d.layanan === "sendiri") {
          return "Barang";
        } else return d.layanan || "-";
      },
    },
    { label: "Harga", key: "total_price" },
    { label: "Status", key: "status" },
    {
      label: "Aksi",
      align: "center",
      render: (row) => (
        <button
          onClick={() => navigate(`/orders/${row.booking_type}/${row.id}`)}
          className="text-green-600 hover:text-blue-green dark:text-green-400 dark:hover:text-green-300 font-semibold bg-green-200 rounded-2xl px-2 py-1 text-xs"
        >
          Detail
        </button>
      ),
    },
  ];

  return (
    <Layout>
      <SearchBar />
      <div className="bg-white rounded-2xl mt-4">
        <Table
          columns={columns}
          data={orders}
          loading={loading}
          error={error}
        />
      </div>
    </Layout>
  );
}
