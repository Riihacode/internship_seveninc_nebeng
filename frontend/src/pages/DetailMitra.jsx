import Layout from "../components/Layout";
import { useDrivers } from "../hooks/useDrivers";
import { useParams } from "react-router-dom";
import Input from "../components/Input";

export default function DetailMitra() {
  const { id } = useParams();
  const { drivers, error, loading } = useDrivers();

  const mitra = Array.isArray(drivers)
    ? drivers?.find((d) => d.id === parseInt(id))
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
      ) : !mitra ? (
        <h1 className="text-center py-4 text-gray-500 dark:text-neutral-400">
          Tidak ada data
        </h1>
      ) : (
        <div className="bg-white rounded-2xl min-h-screen p-3">
          <div className="bg-gray-200 min-h-fit w-full rounded-2xl flex">
            <div className="m-5">
              <img
                className="rounded-full"
                src={
                  mitra.driver_license__img || "https://placehold.co/200x200"
                }
                alt="SIM"
              />
            </div>
            <div className=""></div>
          </div>
          <h1 className="m-3 font-bold">Informasi Pribadi</h1>
          <div className="m-3 flex justify-baseline w-full">
            {/* Sisi Kiri */}
            <div className="flex flex-col min-w-lg">
              <Input label="ID Driver" value={mitra.id} />
              <Input label="Nama Lengkap" value={mitra.full_name} />
              <Input label="Username" value={mitra.user?.username} />
              <Input label="Credit Score" value={mitra.credit_score} />
            </div>
            {/* Sisi Kanan */}
            <div className="flex flex-col">
              <Input label="Email" value={mitra.user?.email} />
              <Input label="Tanggal Bergabung" value={mitra.user?.created_at} />
              <Input label="No.Tlp" value={mitra.telephone} />
              <Input label="Rating" value={mitra.average_rating} />
            </div>
          </div>
          <br />
          <h1 className="m-3 font-bold">Informasi KTP</h1>
          <div className="m-3 flex justify-baseline w-full">
            {/* Sisi Kiri */}
            <div className="flex flex-col min-w-lg">
              <Input
                label="Nama Lengkap Sesuai KTP"
                value={mitra.id_card_fullname}
              />
              <Input label="Tanggal Lahir" value={mitra.id_card_birthdate} />
            </div>
            {/* Sisi Kanan */}
            <div className="flex flex-col">
              <Input label="NIK" value={mitra.id_card_number} />
              <Input
                label="Status"
                value={mitra.card_verified == 1 ? "Terverivikasi" : "Terblokir"}
              />
            </div>
          </div>
          <div className="w-fit bg-gray-300 m-3 rounded-2xl min-h-fit">
            <div className="rounded-2xl">
              <img
                src={mitra.id_card_img || "https://placehold.co/300x200"}
                alt="ktp"
              />
            </div>
          </div>
          <h1 className="m-3 font-bold">Informasi SIM</h1>
          <div className="m-3 flex justify-baseline w-full">
            {/* Sisi Kiri */}
            <div className="flex flex-col min-w-lg">
              <Input label="No SIM" value={mitra.driver_license_number} />
              <Input label="Tipe SIM" value={mitra.driver_license_type} />
            </div>
            {/* Sisi Kanan */}
            <div className="flex flex-col">
              <Input
                label="Batas Aktif SIM"
                value={mitra.driver_license_expired}
              />
              <Input
                label="Verivikasi"
                value={
                  mitra.driver_license_verified == 1
                    ? "Terverivikasi"
                    : "Terblokir"
                }
              />
            </div>
          </div>
          <div className="w-fit bg-gray-300 m-3 rounded-2xl min-h-fit">
            <div className="rounded-2xl">
              <img
                src={
                  mitra.driver_license__img || "https://placehold.co/300x200"
                }
                alt="SIM"
              />
            </div>
          </div>
        </div>
      )}
    </Layout>
  );
}
