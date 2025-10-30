import Layout from "../components/Layout";
import SearchBar from "../components/SearchBar";
import { useDrivers } from "../hooks/useDrivers";
import Table from "../components/Table";
import { useNavigate } from "react-router-dom";

export default function Mitra() {
  const { drivers, error, loading } = useDrivers();
  const navigate = useNavigate();

  const columns = [
    { label: "No", align: "center", render: (_, i) => i + 1 },
    { label: "Nama Mitra", key: "full_name" },
    { label: "Email", render: (d) => d.user.email },
    { label: "NO. TLP", key: "telephone" },
    { label: "Rating", align: "center", key: "average_rating" },
    {
      label: "Status",
      render: (row) =>
        row.id_card_verified === 1 &&
        row.driver_icense_verified === 1 &&
        row.police_clearance_verified === 1 ? (
          <span className="inline-flex items-center gap-x-1.5 rounded-full bg-green-100 px-2 py-1 text-xs font-medium text-green-800">
            <svg
              className="h-1.5 w-1.5 fill-green-500"
              viewBox="0 0 8 8"
              aria-hidden="true"
            >
              <circle cx="4" cy="4" r="3" />
            </svg>
            Terverifikasi
          </span>
        ) : row.id_card_verified === 1 ||
          row.driver_icense_verified === 1 ||
          row.police_clearance_verified === 1 ? (
          <span className="inline-flex items-center gap-x-1.5 rounded-full bg-red-100 px-2 py-1 text-xs font-medium text-red-800">
            <svg
              className="h-1.5 w-1.5 fill-amber-500"
              viewBox="0 0 8 8"
              aria-hidden="true"
            >
              <circle cx="4" cy="4" r="3" />
            </svg>
            Pengajuan
          </span>
        ) : (
          <span className="inline-flex items-center gap-x-1.5 rounded-full bg-red-100 px-2 py-1 text-xs font-medium text-red-800">
            <svg
              className="h-1.5 w-1.5 fill-red-500"
              viewBox="0 0 8 8"
              aria-hidden="true"
            >
              <circle cx="4" cy="4" r="3" />
            </svg>
            Terblokir
          </span>
        ),
    },
    {
      label: "Aksi",
      align: "center",
      render: (row) => (
        <button
          onClick={() => navigate(`/mitra/${row.id}`)}
          className="text-green-600 hover:text-blue-green dark:text-green-400 dark:hover:text-green-300 font-semibold"
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
          data={drivers}
          loading={loading}
          error={error}
        />
      </div>
    </Layout>
  );
}
