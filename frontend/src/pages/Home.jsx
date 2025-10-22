import SideBar from "../components/SideBar";
import useAuth from "../hooks/useAuth";

export default function Home() {
  const { user } = useAuth();
  return (
    <>
      <div className="flex min-h-screen">
        <SideBar />
        <div className="flex-1 flex flex-row bg-gray-100 w-screen">
          <header className="bg-gray-100 dark:bg-gray-400 dark:text-white py-3 h-17 text-end w-full border-b border-gray-200">
            <h2 className="p-3 font-semibold">{user?.name}</h2>
          </header>
        </div>
      </div>
    </>
  );
}
