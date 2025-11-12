import { useParams } from "react-router-dom";
import { useEffect } from "react";
import Layout from "../../components/Layout";
import { useOrders } from "../../hooks/useOrders";
import Input from "../../components/Input";
import dayjs from "dayjs";
import { useTransactions } from "../../hooks/useTransaction";

export default function DetailPesanan() {
  const { id } = useParams();
  const { orders, error, loading: loadingOrders } = useOrders();
  const {
    transaction,
    loading: loadingTransaction,
    fetchTransactionBooking,
  } = useTransactions();
  const formatTanggal = (tanggal) =>
    tanggal ? dayjs(tanggal).format("DD MMMM YYYY - HH:mm") : "-";

  const order = Array.isArray(orders)
    ? orders?.find((d) => d.id === parseInt(id))
    : null;

  useEffect(() => {
    if (order?.id) {
      fetchTransactionBooking(order.booking_type, order.id);
      console.log("🚀 Fetching transaction for:", order.type, order.id);
    }
  }, [order, fetchTransactionBooking]);
  return (
    <Layout>
      {loadingOrders || loadingTransaction ? (
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
      ) : !order && !transaction ? (
        <h1 className="text-center py-4 text-gray-500 dark:text-neutral-400">
          Tidak ada data
        </h1>
      ) : (
        <>
          <div className="bg-white rounded-xl p-3 mb-2 flex justify-between text-black">
            <h1 className="">ID PESANAN :</h1>
            <h1 className="">{order?.booking_code}</h1>
          </div>
          <div className="flex flex-row justify-between w-full gap-4">
            {/* Customer */}
            <div className="bg-white rounded-2xl p-3 my-3 w-full">
              <h1 className="font-bold text-xl text-gray-400 p-3">
                Informasi Customer
              </h1>
              <div className="flex flex-row">
                <div className="p-3">
                  <img
                    className="rounded-sm"
                    src={
                      order?.customer?.profile_img ||
                      "https://placehold.co/200x200"
                    }
                    alt="profile"
                  />
                </div>
                <div className="">
                  <Input
                    label="Nama Lengkap"
                    value={order?.customer_name ?? "-"}
                  />
                  <Input
                    label="Nama Lengkap"
                    value={order?.customer?.telephone ?? "-"}
                  />
                </div>
              </div>
            </div>
            {/* Rincian Perjalanan */}
            <div className="flex flex-row w-full">
              <div className="bg-white rounded-2xl p-3 my-3 w-full">
                <div className="flex flex-col">
                  <h1 className="font-bold text-xl text-gray-400 p-3">
                    Rincian Perjalanan
                  </h1>
                  <div className="flex justify-between p-3">
                    <h2>{formatTanggal(order?.created_at)}</h2>
                  </div>
                  <hr className="mb-2" />
                  <div className="flex justify-between p-3">
                    <div className="flex flex-col">
                      <h1 className="font-semibold text-blue-600">
                        Titik Jemput
                      </h1>
                      <h2 className="font-semibold">{order?.kota_awal}</h2>
                      <h2 className="text-sm">
                        {order?.terminal_keberangkatan}
                      </h2>
                    </div>
                    <div className="flex flex-col">
                      <h1 className="font-semibold text-blue-600">Tujuan</h1>
                      <h2 className="font-semibold">{order?.kota_tujuan}</h2>
                      <h2 className="text-sm">{order?.terminal_tujuan}</h2>
                    </div>
                  </div>
                  <div className="flex"></div>
                </div>
              </div>
            </div>
          </div>
          <div className="flex flex-row justify-between gap-4 w-full">
            {/* Mitra */}
            <div className="bg-white rounded-2xl p-3 w-full">
              <h1 className="font-bold text-xl text-gray-400 p-3">
                Informasi Driver
              </h1>
              <div className="flex flex-row">
                <div className="p-3">
                  <img
                    className="rounded-sm"
                    src={
                      order?.customer?.profile_img ||
                      "https://placehold.co/200x200"
                    }
                    alt="profile"
                  />
                </div>
                <div className="">
                  <Input
                    label="Nama Lengkap"
                    value={order?.driver_name ?? "-"}
                  />
                  <Input label="No Tlp" value={order?.driver_phone ?? "-"} />
                  <Input label="Kendaraan" />
                  <Input label="No Kendaraan" />
                </div>
              </div>
            </div>
            <div className="bg-white p-6 w-full rounded-2xl">
              <h1 className="font-bold text-xl text-gray-400 pb-3">
                Informasi Pembayaran
              </h1>
              <div className="flex flex-col">
                <div className="flex justify-between pb-3">
                  <h1>Tipe Pembayaran</h1>
                  <h1>{transaction?.data?.payment_method.bank_name ?? "-"}</h1>
                </div>
                <div className="flex justify-between pb-3">
                  <h1>Tanggal</h1>
                  <h1>{formatTanggal(transaction?.data?.transaction_date)}</h1>
                </div>
                <hr className="py-3" />
                <div className="flex justify-between pb-3">
                  <h1>ID Pesanan</h1>
                  <h1>{order?.booking_code ?? "-"}</h1>
                </div>
                <div className="flex justify-between pb-3">
                  <h1>No Transaksi</h1>
                  <h1>{transaction?.data?.transaction_code ?? "-"}</h1>
                </div>
                <hr className="py-3" />
                <div className="flex justify-between pb-3">
                  <h1>
                    {order?.booking_type === "Passenger"
                      ? "Biaya Per penebeng"
                      : order?.booking_type === "Goods"
                        ? "Biaya Per kg"
                        : "-"}
                  </h1>
                  <h1>
                    {order?.booking_type === "Passenger"
                      ? (order?.passenger_ride?.price_per_seat ?? "-")
                      : order?.booking_type === "Goods"
                        ? (order?.goods_ride?.price_per_kg ?? "-")
                        : "-"}
                  </h1>
                </div>
                <div className="flex justify-between pb-3">
                  <h1>Sub Total</h1>
                  <h1>Tanggal</h1>
                </div>
                <hr className="py-3" />
                <div className="flex justify-between pb-3">
                  <h1>Total</h1>
                  <h1>{order?.total_price ?? "-"}</h1>
                </div>
              </div>
            </div>
          </div>
        </>
      )}
    </Layout>
  );
}
