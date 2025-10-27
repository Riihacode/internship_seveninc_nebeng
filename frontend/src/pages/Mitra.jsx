import Layout from "../components/Layout";
import SearchBar from "../components/SearchBar";
import { useDrivers } from "../hooks/useDrivers";
import Table from "../components/Table";

export default function Mitra() {
  const { drivers, error, loading } = useDrivers();

  const columns = [
    { label: "No", align: "center", render: (_, i) => i + 1 },
    { label: "Nama Mitra", key: "full_name" },
    { label: "Email", render: (d) => d.user.email },
    { label: "No. Telepon", key: "telephone" },
    { label: "Jenis Perubahan", align: "center", render: () => "SIM" },
    {
      label: "Status",
      align: "center",
      render: () => <button>Verifikasi</button>,
    },
    {
      label: "Aksi",
      align: "center",
      render: () => (
        <button
          onClick=""
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
        <div className="flex flex-col">
          <div className="-m-1.5 overflow-x-auto">
            <div className="p-1.5 min-w-full inline-block align-middle">
              <div className="overflow-hidden">
                <Table
                  columns={columns}
                  data={drivers}
                  loading={loading}
                  error={error}
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    </Layout>
  );
}
