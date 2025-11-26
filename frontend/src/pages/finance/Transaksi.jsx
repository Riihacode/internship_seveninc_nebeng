import Layout from "../../components/Layout.jsx";
import { useOrders } from "../../hooks/useOrders.js";
import MemoTable from "../../components/MemoTable.jsx";
import SearchBar from "../../components/SearchBar.jsx";
import dayjs from "dayjs";
import { useNavigate } from "react-router-dom";
import { useCallback, useMemo, useState } from "react";

export default function Transaksi() {
  const [searchText, setSearchText] = useState("");
  const [filterStatus, setFilterStatus] = useState("");
  const { orders, isLoadingList, error, meta, links, fetchOrders } = useOrders({
    search: searchText,
    status: filterStatus,
  });
  const navigate = useNavigate();

  const filterOptions = [
    { label: "Pending", value: "pending" },
    { label: "Diterima", value: "diterima" },
    { label: "Ditolak", value: "ditolak" },
  ];

  const filteredOrders = useMemo(() => {
    return orders.filter((row) => {
      const codeOrderMatch = row.booking_code
        .toLowerCase()
        .includes(searchText.toLowerCase());

      const customerMatch = row.customer_name
        .toLowerCase()
        .includes(searchText.toLowerCase());

      const driverMatch = row.driver_name
        .toLowerCase()
        .includes(searchText.toLowerCase());

      return codeOrderMatch || customerMatch || driverMatch;
    });
  }, [orders, searchText]);

  const formatTanggal = useCallback(
    (tanggal) => (tanggal ? dayjs(tanggal).format("DD MMMM YYYY") : "-"),
    []
  );

  // render columns
  const renderTanggal = useCallback(
    (row) => formatTanggal(row.created_at),
    [formatTanggal]
  );

  function renderStatus(status) {
    const val = String(status).toLowerCase();

    if (val === "pending") {
      return (
        <span className="inline-flex items-center gap-x-1.5 rounded-full bg-yellow-100 px-2 py-1 text-xs font-medium text-yellow-800">
          Pending
        </span>
      );
    }

    if (val === "diterima") {
      return (
        <span className="inline-flex items-center gap-x-1.5 rounded-full bg-green-100 px-2 py-1 text-xs font-medium text-green-800">
          Diterima
        </span>
      );
    }
    if (val === "ditolak") {
      return (
        <span className="inline-flex items-center gap-x-1.5 rounded-full bg-red-100 px-2 py-1 text-xs font-medium text-red-800">
          Ditolak
        </span>
      );
    }

    return (
      <span className="inline-flex items-center gap-x-1.5 rounded-full bg-gray-100 px-2 py-1 text-xs font-medium text-gray-800">
        Tidak Diketahui
      </span>
    );
  }

  const renderAction = useCallback(
    (row) => (
      <button
        onClick={() =>
          navigate(`/orders/${row.booking_type}/${row.booking_id}`)
        }
        className="text-green-600 hover:text-blue-green dark:text-green-400 dark:hover:text-green-300 font-semibold bg-green-200 rounded-2xl px-2 py-1 text-xs"
      >
        Detail
      </button>
    ),
    [navigate]
  );

  // COLUMNS
  const columns = useMemo(
    () => [
      {
        label: "NO.",
        align: "center",
        render: (_, i) => i + 1,
      },
      {
        label: "Tanggal",
        render: renderTanggal,
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
        label: "NO. Transaksi",
        key: "transaction_code",
      },
      {
        label: "NO. Orderan",
        key: "booking_code",
      },
      { label: "Status", render: (row) => renderStatus(row.status) },
      {
        label: "Aksi",
        align: "center",
        render: renderAction,
      },
    ],
    [renderAction, renderTanggal]
  );

  return (
    <Layout>
      <SearchBar
        searchText={searchText}
        onSearchChange={setSearchText}
        filterOptions={filterOptions}
        onFilterChange={setFilterStatus}
      />
      <div className="bg-white rounded-2xl mt-4 min-h-7/9">
        <MemoTable
          columns={columns}
          data={filteredOrders}
          loading={isLoadingList}
          error={error}
        />
      </div>
      {/* Pagination */}
      {!isLoadingList && filteredOrders.length > 0 && (
        <div className="mt-4">
          <button
            disabled={!links.prev_page_url}
            onClick={() => fetchOrders(meta.current_page - 1)}
            className={`px-3 py-1 rounded-lg text-xs ${
              links.prev_page_url
                ? "bg-green-500 text-white hover:bg-green-600"
                : "bg-gray-200 text-gray-400 cursor-not-allowed"
            }`}
          >
            &laquo;
          </button>
          <span className="text-xs mx-2">
            Halaman {meta.current_page} dari {meta.last_page}
          </span>
          <button
            disabled={!links.next_page_url}
            onClick={() => fetchOrders(meta.current_page + 1)}
            className={`px-3 py-1 rounded-lg text-xs ${
              links.next_page_url
                ? "bg-green-500 text-white hover:bg-green-600"
                : "bg-gray-200 text-gray-400 cursor-not-allowed"
            }`}
          >
            &raquo;
          </button>
        </div>
      )}
    </Layout>
  );
}
