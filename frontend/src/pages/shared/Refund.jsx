import Layout from "../../components/Layout";
import { useNavigate } from "react-router-dom";
import dayjs from "dayjs";
import { useOrders } from "../../hooks/useOrders";
import Table from "../../components/Table";

export default function Refund() {
  const { orders, loading, error } = useOrders();
  const navigate = useNavigate();
  const formatTanggal = (tanggal) =>
    tanggal ? dayjs(tanggal).format("DD MMMM YYYY") : "-";
  const columns = [
    {
      label: "NO ORDER",
      key: "booking_code",
      align: "left",
    },
    {
      label: "CUSTOMER",
      key: "customer_name",
      align: "center",
    },
    {
      label: "TANGGAL",
      render: (row) => (row?.created_at ? formatTanggal(row.created_at) : "-"),
      align: "center",
    },
    {
      label: "LAYANAN",
      render: (d) => {
        if (d.layanan === "umum" || d.layanan === "sendiri") {
          return "Barang";
        } else return d.layanan || "-";
      },
    },
    {
      label: "Aksi",
      align: "center",
      render: (row) => (
        <button
          onClick={() => navigate(`/orders/${row.booking_type}/${row.id}`)}
          className="text-green-600 hover:text-white hover: dark:text-green-400 dark:hover:text-green-300 font-semibold bg-green-200 rounded-2xl px-2 py-1 text-xs transform transition duration-200 hover:scale-105"
        >
          Detail
        </button>
      ),
    },
  ];
  return (
    <Layout>
      <div className="bg-white rounded-2xl w-full min-h-screen p-3">
        <Table
          columns={columns}
          data={orders}
          loading={loading}
          error={error}
        ></Table>
      </div>
    </Layout>
  );
}
