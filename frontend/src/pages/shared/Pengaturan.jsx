import { useEffect, useState } from "react";
import Layout from "../../components/Layout";
import useAuth from "../../hooks/useAuth";
import { useUsers } from "../../hooks/useUsers";
import Input from "../../components/Input";
import dayjs from "dayjs";

export default function Pengaturan() {
  const { user } = useAuth();
  const { getUserById, isLoadingDetail } = useUsers();
  const formatTanggal = (tanggal) =>
    tanggal ? dayjs(tanggal).format("DD MMMM YYYY") : "-";

  const [selectedUser, setSelectedUser] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    const load = async () => {
      try {
        const res = await getUserById(user.id);
        console.log("User dari page:", res);
        setSelectedUser(res.data);
      } catch (error) {
        setError(error);
      }
    };

    if (user?.id) load();
  }, [user, getUserById]);

  if (isLoadingDetail)
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

  if (error || !selectedUser)
    return (
      <Layout>
        <h1 className="text-center py-4 text-gray-500 dark:text-neutral-400">
          {error ? "Terjadi Kesalahan saat memuat data" : "Belum ada data"}
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
        <div className="bg-gray-200 min-h-fit w-full rounded-2xl flex justify-items-center">
          <div className="m-5">
            <img
              className="rounded-full"
              src={selectedUser.profile_img || "https://placehold.co/200x200"}
              alt="SIM"
            />
          </div>
          <div className="flex flex-col justify-center justify-items-center m-3">
            <h1 className="font-semibold">{selectedUser.name}</h1>
            <p className="text-gray-600">{selectedUser.email}</p>
            <p className="text-gray-600">{selectedUser.user_type}</p>
            <span>
              {selectedUser.verified === true ? (
                <span className="inline-flex items-center gap-x-1.5 rounded-full bg-green-800 px-2 py-1 text-xs font-medium text-white">
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
                <span className="inline-flex items-center gap-x-1.5 rounded-full bg-red-800 px-2 py-1 text-xs font-medium text-white">
                  <svg
                    className="h-1.5 w-1.5 fill-red-500"
                    viewBox="0 0 8 8"
                    aria-hidden="true"
                  >
                    <circle cx="4" cy="4" r="3" />
                  </svg>
                  Terblokir
                </span>
              )}
            </span>
          </div>
        </div>
        <h1 className="m-3 font-bold">Informasi Pribadi</h1>
        <div className="m-3 flex justify-baseline w-full">
          {/* Sisi Kiri */}
          <div className="flex flex-col min-w-lg">
            <Input label="ID selectedUser" value={selectedUser.id} />
            <Input label="Nama Lengkap" value={selectedUser.name} />
            <Input label="Username" value={selectedUser.username} />
          </div>
          {/* Sisi Kanan */}
          <div className="flex flex-col">
            <Input label="Email" value={selectedUser.email} />
            <Input
              label="Tanggal Bergabung"
              value={formatTanggal(selectedUser.created_at)}
            />
            <Input label="No.Tlp" value={selectedUser.user_type} />
          </div>
        </div>
      </div>
    </Layout>
  );
}
