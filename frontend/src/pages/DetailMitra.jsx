import Layout from "../components/Layout";
import { useDrivers } from "../hooks/useDrivers.js";
import { useParams } from "react-router-dom";
import Input from "../components/Input";
import Swal from "sweetalert2";

export default function DetailMitra() {
  const { id } = useParams();
  const { drivers, error, loading, verifyDriver } = useDrivers();

  const mitra = Array.isArray(drivers)
    ? drivers?.find((d) => d.id === parseInt(id))
    : null;

  const handleVerify = async (type, status) => {
    let reason = null;

    if (status === 0) {
      const result = await Swal.fire({
        title: "Masukkan alasan penolakan",
        input: "text",
        inputPlaceholder: "Tuliskan alasan...",
        showCancelButton: true,
        confirmButtonText: "Kirim",
        cancelButtonText: "Batal",
      });
      if (!result.value) return;
      reason = result.value;
    }

    const res = await verifyDriver(mitra.id, { type, status, reason });
    if (res)
      Swal.fire(
        status === 1 ? "Sukses" : "Ditolak",
        `${type.toUpperCase()} berhasil ${
          status === 1 ? "Diverifikasi" : "diblokir"
        }`,
        "success"
      );
  };

  if (loading)
    return (
      <Layout>
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
      </Layout>
    );

  if (error || !mitra)
    return (
      <Layout>
        <h1 className="text-center py-4 text-gray-500 dark:text-neutral-400">
          {error
            ? "Terjadi Kesalahan saat memuat data"
            : "Data tidak ditemukan"}
          <br />
          <span className="text-sm text-gray-400">
            {error ? error.message : "Coba lagi nanti"}
          </span>
        </h1>
      </Layout>
    );

  return (
    <Layout>
      <div className="bg-white rounded-2xl min-h-screen p-3">
        <div className="bg-gray-200 min-h-fit w-full rounded-2xl flex">
          <div className="m-5">
            <img
              className="rounded-full"
              src={mitra.profile_img || "https://placehold.co/200x200"}
              alt="profile"
            />
          </div>
          <div className="">
            <h1 className="font-bold text-lg">{mitra.full_name}</h1>
            <p className="text-gray-600">{mitra.user?.email}</p>
            <p className="text-gray-600">{mitra.telephone}</p>
          </div>
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
        <hr />

        {/* ================================================================================================================================================================================================================================== */}
        {/* ================================================================================================================================================================================================================================== */}
        {/* ================================================================================================================================================================================================================================== */}

        {[
          {
            title: "Informasi KTP",
            type: "id_card",
            data: {
              nama: mitra.id_card_fullname,
              tanggalLahir: mitra.id_card_birthdate,
              nik: mitra.id_card_number,
              status: mitra.id_card_verified,
              img: mitra.id_card_img,
            },
          },
          {
            title: "Informasi SIM",
            type: "driver_license",
            data: {
              noSim: mitra.driver_license_number,
              tipe: mitra.driver_license_type,
              expired: mitra.driver_license_expired,
              status: mitra.driver_license_verified,
              img: mitra.driver_license_img,
            },
          },
          {
            title: "Informasi STNK",
            type: "police_clearance",
            data: {
              noStnk: mitra.police_clearance_certificate_number,
              nama: mitra.police_clearance_certificate_fullname,
              expired: mitra.police_clearance_certificate_expired,
              status: mitra.police_clearance_certificate_verified,
              img: mitra.police_clearance_certificate_img,
            },
          },
        ].map((item) => (
          <section key={item.type}>
            <h1 className="m-3 font-bold">{item.title}</h1>
            <div className="m-3 flex justify-baseline w-full">
              {/* Sisi Kiri */}
              <div className="flex flex-col min-w-lg">
                {Object.entries(item.data).map(
                  ([label, value]) =>
                    label !== "img" && (
                      <Input
                        key={label}
                        label={label.toUpperCase()}
                        value={
                          label === "status"
                            ? value === 1
                              ? "Terverivikasi"
                              : "Belum Diverivikasi"
                            : value || "-"
                        }
                      />
                    )
                )}
              </div>
              {/* Sisi Kanan */}
              <div className="flex flex-col m-3">
                <div className="w-fit bg-gray-300 m-3 rounded-2xl min-h-fit">
                  <div className="rounded-2xl">
                    <img
                      src={item.data.img || "https://placehold.co/300x200"}
                      alt={item.type}
                    />
                  </div>
                </div>
                <div className="p-3">
                  <button
                    className="bg-green-500 text-white px-3 py-2 rounded-xl mr-2"
                    onClick={() => handleVerify(item.type, 1)}
                    disabled={loading}
                  >
                    Terima
                  </button>
                  <button
                    className="bg-red-500 text-white px-3 py-2 rounded-xl"
                    onClick={() => handleVerify(item.type, 0)}
                    disabled={loading}
                  >
                    Tolak
                  </button>
                </div>
              </div>
            </div>
            <hr />
          </section>
        ))}

        {/* ================================================================================================================================================================================================================================== */}
        {/* ================================================================================================================================================================================================================================== */}
        {/* ================================================================================================================================================================================================================================== */}

        {/* ================================================================================================================================================================================================================================== */}
        {/* ================================================================================================================================================================================================================================== */}
        {/* ================================================================================================================================================================================================================================== */}

        <h1 className="m-3 font-bold">Informasi KTP</h1>
        <div className="m-3 flex justify-baseline w-full">
          {/* Sisi Kiri */}
          <div className="flex flex-col min-w-lg">
            <Input
              label="Nama Lengkap Sesuai KTP"
              value={mitra.id_card_fullname}
            />
            <Input label="Tanggal Lahir" value={mitra.id_card_birthdate} />
            <Input label="NIK" value={mitra.id_card_number} />
            <Input
              label="Status"
              value={mitra.card_verified == 1 ? "Terverivikasi" : "Terblokir"}
            />
          </div>
          {/* Sisi Kanan */}
          <div className="flex flex-col m-3">
            <div className="w-fit bg-gray-300 m-3 rounded-2xl min-h-200px">
              <div className="rounded-2xl">
                <img
                  src={mitra.id_card_img || "https://placehold.co/300x200"}
                  alt="ktp"
                  className="min-h-200px"
                />
              </div>
            </div>
            <div className="p-3">
              <button
                className="bg-green-500 text-white px-3 py-2 rounded-xl mr-2"
                onClick={handleVerify}
                disabled={loading}
              >
                Terima
              </button>
              <button
                className="bg-red-500 text-white px-3 py-2 rounded-xl"
                // onClick={handleBlock}
                disabled={loading}
              >
                Tolak
              </button>
            </div>
          </div>
        </div>

        {/* ================================================================================================================================================================================================================================== */}
        {/* ================================================================================================================================================================================================================================== */}
        {/* ================================================================================================================================================================================================================================== */}
      </div>
    </Layout>
  );
}
