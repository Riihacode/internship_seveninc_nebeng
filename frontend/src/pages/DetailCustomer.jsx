import { useParams } from "react-router-dom";
import Layout from "../components/Layout";
import { useCustomers } from "../hooks/useCustomers";
import Input from "../components/Input";
import dayjs from "dayjs";

export default function DetailCustomer() {
  const { id } = useParams();
  const { customers, loading, error } = useCustomers();
  const formatTanggal = (tanggal) =>
    tanggal ? dayjs(tanggal).format("DD MMMM YYYY") : "-";

  const cst = Array.isArray(customers)
    ? customers?.find((d) => d.id === parseInt(id))
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
      ) : !cst ? (
        <h1 className="text-center py-4 text-gray-500 dark:text-neutral-400">
          Tidak ada data
        </h1>
      ) : (
        <div className="bg-white rounded-2xl min-h-screen p-3">
          <div className="bg-gray-200 min-h-fit w-full rounded-2xl flex justify-items-center">
            <div className="m-5">
              <img
                className="rounded-full"
                src={cst.profile_img || "https://placehold.co/200x200"}
                alt="SIM"
              />
            </div>
            <div className="flex">
              <h1 className="p-5 m-3 font-semibold">{cst.full_name}</h1>
            </div>
          </div>
          <h1 className="m-3 font-bold">Informasi Pribadi</h1>
          <div className="m-3 flex justify-baseline w-full">
            {/* Sisi Kiri */}
            <div className="flex flex-col min-w-lg">
              <Input label="ID Customer" value={cst.id} />
              <Input label="Nama Lengkap" value={cst.full_name} />
              <Input label="Username" value={cst.user?.username} />
            </div>
            {/* Sisi Kanan */}
            <div className="flex flex-col">
              <Input label="Email" value={cst.user?.email} />
              <Input
                label="Tanggal Bergabung"
                value={formatTanggal(cst.user?.created_at)}
              />
              <Input label="No.Tlp" value={cst.telephone} />
            </div>
          </div>
          <br />
          <h1 className="m-3 font-bold">Informasi KTP</h1>
          <div className="m-3 flex justify-baseline w-full">
            {/* Sisi Kiri */}
            <div className="flex flex-col min-w-lg">
              <Input
                label="Nama Lengkap Sesuai KTP"
                value={cst.id_card_fullname}
              />
              <Input
                label="Tanggal Lahir"
                value={formatTanggal(cst.id_card_birthdate)}
              />
            </div>
            {/* Sisi Kanan */}
            <div className="flex flex-col">
              <Input label="NIK" value={cst.id_card_number} />
            </div>
          </div>
          <br />
          <div className="w-fi m-3 rounded-2xl min-h-fit flex">
            <div className="rounded-2xl p-3 flex-col">
              <p className="font-semibold text-center">Foto Wajah</p>
              <img
                src={cst.face_img || "https://placehold.co/300x200"}
                alt="face img"
              />
            </div>
            <div className="rounded-2xl p-3 flex-col">
              <p className="font-semibold text-center">Foto Wajah Dengan KTP</p>
              <img
                src={cst.face_with_id_image || "https://placehold.co/300x200"}
                alt="face with ktp"
              />
            </div>
            <div className="rounded-2xl p-3 flex-col">
              <p className="font-semibold text-center">Foto KTP</p>
              <img
                src={cst.id_card_img || "https://placehold.co/300x200"}
                alt="ktp"
              />
            </div>
          </div>
        </div>
      )}
    </Layout>
  );
}
