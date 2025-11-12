import { NavLink } from "react-router-dom";
import LogoutButton from "./LogoutButton";
import { sideBarMenu } from "./SideBarMenu";
import useAuth from "../hooks/useAuth";
export default function SideBar() {
  const { user } = useAuth();
  if (!user) return null;
  const menus = sideBarMenu[user?.user_type] || [];

  return (
    <aside
      className={`hidden md:flex flex-col justify-between bg-[#10367d] dark:border-gray-700 dark:text-white dark:bg-gray-600 w-64 min-h-screen relative ${user?.user_type}`}
    >
      <nav className="text-sm text-white">
        <div className="flex justify-center justify-items-center py-3 h-17 text-2xl uppercase text-center tracking-widest bg-[#10367d] border-b-1 border-white mb-4 dark:bg-gray-700">
          {" "}
          <NavLink to="/" className="max-h-15">
            {" "}
            <img
              className="w-37"
              src="http://localhost:5173/logoNebeng.png"
              alt="logo"
            />{" "}
          </NavLink>{" "}
        </div>
        <ul className="flex flex-col">
          {menus.map((menu, i) => (
            <li key={i} className="cursor-pointer hover:text-blue-500">
              <NavLink
                to={menu.path}
                className={({ isActive }) =>
                  `relative flex items-center gap-3 px-4 py-3 transition-all duration-300 group
                ${
                  isActive
                    ? "text-blue-500 font-semibold before:content-[''] before:absolute before:left-0 before:top-0 before:h-full before:w-[3px] before:bg-blue-500 before:rounded-r-md"
                    : "white dark:text-gray-300 hover:text-blue-500"
                }`
                }
              >
                {/* Garis Kanan */}
                <span
                  className={`absolute right-0 top-0 h-full w-[3px] rounded-r-md bg-blue-500 transition-all duration-300 opacity-0 group-hover:opacity-100`}
                ></span>
                <menu.icon size={20} />
                <span>{menu.name}</span>
              </NavLink>
            </li>
          ))}
        </ul>
      </nav>
      <div className="py-4">
        <li className="cursor-pointer hover:text-white">
          {" "}
          <NavLink
            to="/pengaturan"
            className="relative flex items-center w-full text-left px-4 py-3 transition-all duration-300 group text-white dark:text-gray-300 hover:text-red-500 hover:before:content-[''] hover:before:absolute hover:before:left-0 hover:before:top-0 hover:before:h-full hover:before:w-[3px] hover:before:bg-red-500 hover:before:rounded-r-md"
          >
            {" "}
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              strokeWidth={1.5}
              stroke="currentColor"
              className="size-6 w-4 mr-3"
            >
              {" "}
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M9.594 3.94c.09-.542.56-.94 1.11-.94h2.593c.55 0 1.02.398 1.11.94l.213 1.281c.063.374.313.686.645.87.074.04.147.083.22.127.325.196.72.257 1.075.124l1.217-.456a1.125 1.125 0 0 1 1.37.49l1.296 2.247a1.125 1.125 0 0 1-.26 1.431l-1.003.827c-.293.241-.438.613-.43.992a7.723 7.723 0 0 1 0 .255c-.008.378.137.75.43.991l1.004.827c.424.35.534.955.26 1.43l-1.298 2.247a1.125 1.125 0 0 1-1.369.491l-1.217-.456c-.355-.133-.75-.072-1.076.124a6.47 6.47 0 0 1-.22.128c-.331.183-.581.495-.644.869l-.213 1.281c-.09.543-.56.94-1.11.94h-2.594c-.55 0-1.019-.398-1.11-.94l-.213-1.281c-.062-.374-.312-.686-.644-.87a6.52 6.52 0 0 1-.22-.127c-.325-.196-.72-.257-1.076-.124l-1.217.456a1.125 1.125 0 0 1-1.369-.49l-1.297-2.247a1.125 1.125 0 0 1 .26-1.431l1.004-.827c.292-.24.437-.613.43-.991a6.932 6.932 0 0 1 0-.255c.007-.38-.138-.751-.43-.992l-1.004-.827a1.125 1.125 0 0 1-.26-1.43l1.297-2.247a1.125 1.125 0 0 1 1.37-.491l1.216.456c.356.133.751.072 1.076-.124.072-.044.146-.086.22-.128.332-.183.582-.495.644-.869l.214-1.28Z"
              />{" "}
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M15 12a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z"
              />{" "}
            </svg>{" "}
            Pengaturan{" "}
          </NavLink>{" "}
        </li>
        <LogoutButton />
      </div>
    </aside>
  );
}
