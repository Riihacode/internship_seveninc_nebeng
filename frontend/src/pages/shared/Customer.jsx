import Layout from "../../components/Layout";
import { useCustomers } from "../../hooks/useCustomers";
import SearchBar from "../../components/SearchBar";
import Table from "../../components/Table";
import { useNavigate } from "react-router-dom";

export default function Customer() {
  const { customers, error, loading } = useCustomers();
  const navigate = useNavigate();

  const columns = [
    { label: "No", align: "center", render: (_, i) => i + 1 },
    { label: "Nama Customer", key: "full_name" },
    { label: "Email", render: (d) => d.user.email },
    { label: "NO. TLP", key: "telephone" },
    {
      label: "Status",
      render: (row) =>
        row.verified === 1 ? (
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
          onClick={() => navigate(`/customer/${row.id}`)}
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
          data={customers}
          loading={loading}
          error={error}
        />
      </div>
    </Layout>
  );
}
