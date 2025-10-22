import { NavLink } from "react-router-dom";
import LogoutButton from "./LogoutButton";
export default function SideBar() {
  return (
    <aside className="hidden md:flex flex-col justify-between bg-white text-gray-700 dark:border-gray-700 dark:text-white dark:bg-gray-600 w-64 min-h-screen">
      {/* ATAS */}
      <div className="">
        <div className="py-3 h-17 text-2xl uppercase text-center tracking-widest bg-gray-900 border-b-1 border-gray-800 mb-8 dark:bg-gray-700">
          <NavLink to="/" className="text-white">
            Logo
          </NavLink>
        </div>
        <nav className="text-sm text-gray-400">
          <ul className="flex flex-col">
            <li className="cursor-pointer text-gray-700  hover:text-blue-500">
              <NavLink
                to="/home"
                className={({ isActive }) =>
                  `relative flex items-center px-4 py-3 transition-all duration-300 group
                ${
                  isActive
                    ? "text-blue-500 font-semibold before:content-[''] before:absolute before:left-0 before:top-0 before:h-full before:w-[3px] before:bg-blue-500 before:rounded-r-md"
                    : "text-gray-600 dark:text-gray-300 hover:text-blue-500"
                }`
                }
              >
                {/* Garis Kanan */}
                <span
                  className={`absolute right-0 top-0 h-full w-[3px] rounded-r-md bg-blue-500 transition-all duration-300 opacity-0 group-hover:opacity-100`}
                ></span>
                <svg
                  className="w-4 mr-3"
                  aria-hidden="true"
                  xmlns="http://www.w3.org/2000/svg"
                  width="24"
                  height="24"
                  fill="currentColor"
                  viewBox="0 0 24 24"
                >
                  <path
                    fillRule="evenodd"
                    d="M11.293 3.293a1 1 0 0 1 1.414 0l6 6 2 2a1 1 0 0 1-1.414 1.414L19 12.414V19a2 2 0 0 1-2 2h-3a1 1 0 0 1-1-1v-3h-2v3a1 1 0 0 1-1 1H7a2 2 0 0 1-2-2v-6.586l-.293.293a1 1 0 0 1-1.414-1.414l2-2 6-6Z"
                    clipRule="evenodd"
                  />
                </svg>
                Beranda
              </NavLink>
            </li>
            <li className="cursor-pointer text-gray-700 dark:text-white hover:text-blue-500">
              <NavLink
                to="/mitra"
                className={({ isActive }) =>
                  `relative flex items-center px-4 py-3 transition-all duration-300 group
                ${
                  isActive
                    ? "text-blue-500 font-semibold before:content-[''] before:absolute before:left-0 before:top-0 before:h-full before:w-[3px] before:bg-blue-500 before:rounded-r-md"
                    : "text-gray-600 dark:text-gray-300 hover:text-blue-500"
                }`
                }
              >
                {/* Garis Kanan */}
                <span
                  className={`absolute right-0 top-0 h-full w-[3px] rounded-r-md bg-blue-500 transition-all duration-300 opacity-0 group-hover:opacity-100`}
                ></span>
                <svg
                  className="w-4 mr-3"
                  aria-hidden="true"
                  xmlns="http://www.w3.org/2000/svg"
                  width="24"
                  height="24"
                  fill="currentColor"
                  viewBox="0 0 24 24"
                >
                  <path
                    fillRule="evenodd"
                    d="M12 4a4 4 0 1 0 0 8 4 4 0 0 0 0-8Zm-2 9a4 4 0 0 0-4 4v1a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2v-1a4 4 0 0 0-4-4h-4Z"
                    clipRule="evenodd"
                  />
                </svg>
                Mitra
              </NavLink>
            </li>
            <li className="cursor-pointer text-gray-700 dark:text-white hover:text-white">
              <NavLink
                to="/customer"
                className={({ isActive }) =>
                  `relative flex items-center px-4 py-3 transition-all duration-300 group
                ${
                  isActive
                    ? "text-blue-500 font-semibold before:content-[''] before:absolute before:left-0 before:top-0 before:h-full before:w-[3px] before:bg-blue-500 before:rounded-r-md"
                    : "text-gray-600 dark:text-gray-300 hover:text-blue-500"
                }`
                }
              >
                {/* Garis Kanan */}
                <span
                  className={`absolute right-0 top-0 h-full w-[3px] rounded-r-md bg-blue-500 transition-all duration-300 opacity-0 group-hover:opacity-100`}
                ></span>
                <svg
                  className="w-4 mr-3"
                  aria-hidden="true"
                  xmlns="http://www.w3.org/2000/svg"
                  width="24"
                  height="24"
                  fill="currentColor"
                  viewBox="0 0 24 24"
                >
                  <path
                    fillRule="evenodd"
                    d="M8 4a4 4 0 1 0 0 8 4 4 0 0 0 0-8Zm-2 9a4 4 0 0 0-4 4v1a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2v-1a4 4 0 0 0-4-4H6Zm7.25-2.095c.478-.86.75-1.85.75-2.905a5.973 5.973 0 0 0-.75-2.906 4 4 0 1 1 0 5.811ZM15.466 20c.34-.588.535-1.271.535-2v-1a5.978 5.978 0 0 0-1.528-4H18a4 4 0 0 1 4 4v1a2 2 0 0 1-2 2h-4.535Z"
                    clipRule="evenodd"
                  />
                </svg>
                Customer
              </NavLink>
            </li>
            <li className="cursor-pointer text-gray-700 dark:text-white hover:text-white">
              <NavLink
                to="/pesanan"
                className={({ isActive }) =>
                  `relative flex items-center px-4 py-3 transition-all duration-300 group
                ${
                  isActive
                    ? "text-blue-500 font-semibold before:content-[''] before:absolute before:left-0 before:top-0 before:h-full before:w-[3px] before:bg-blue-500 before:rounded-r-md"
                    : "text-gray-600 dark:text-gray-300 hover:text-blue-500"
                }`
                }
              >
                {/* Garis Kanan */}
                <span
                  className={`absolute right-0 top-0 h-full w-[3px] rounded-r-md bg-blue-500 transition-all duration-300 opacity-0 group-hover:opacity-100`}
                ></span>
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  strokeWidth="1.5"
                  stroke="currentColor"
                  className="w-4 mr-3"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M6 6.878V6a2.25 2.25 0 0 1 2.25-2.25h7.5A2.25 2.25 0 0 1 18 6v.878m-12 0c.235-.083.487-.128.75-.128h10.5c.263 0 .515.045.75.128m-12 0A2.25 2.25 0 0 0 4.5 9v.878m13.5-3A2.25 2.25 0 0 1 19.5 9v.878m0 0a2.246 2.246 0 0 0-.75-.128H5.25c-.263 0-.515.045-.75.128m15 0A2.25 2.25 0 0 1 21 12v6a2.25 2.25 0 0 1-2.25 2.25H5.25A2.25 2.25 0 0 1 3 18v-6c0-.98.626-1.813 1.5-2.122"
                  />
                </svg>
                Pesananan
              </NavLink>
            </li>
            <li className="cursor-pointer text-gray-700 dark:text-white hover:text-white">
              <NavLink
                to="refund"
                className={({ isActive }) =>
                  `relative flex items-center px-4 py-3 transition-all duration-300 group
                ${
                  isActive
                    ? "text-blue-500 font-semibold before:content-[''] before:absolute before:left-0 before:top-0 before:h-full before:w-[3px] before:bg-blue-500 before:rounded-r-md"
                    : "text-gray-600 dark:text-gray-300 hover:text-blue-500"
                }`
                }
              >
                {/* Garis Kanan */}
                <span
                  className={`absolute right-0 top-0 h-full w-[3px] rounded-r-md bg-blue-500 transition-all duration-300 opacity-0 group-hover:opacity-100`}
                ></span>
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  strokeWidth="1.5"
                  stroke="currentColor"
                  className="w-4 mr-3"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M6 6.878V6a2.25 2.25 0 0 1 2.25-2.25h7.5A2.25 2.25 0 0 1 18 6v.878m-12 0c.235-.083.487-.128.75-.128h10.5c.263 0 .515.045.75.128m-12 0A2.25 2.25 0 0 0 4.5 9v.878m13.5-3A2.25 2.25 0 0 1 19.5 9v.878m0 0a2.246 2.246 0 0 0-.75-.128H5.25c-.263 0-.515.045-.75.128m15 0A2.25 2.25 0 0 1 21 12v6a2.25 2.25 0 0 1-2.25 2.25H5.25A2.25 2.25 0 0 1 3 18v-6c0-.98.626-1.813 1.5-2.122"
                  />
                </svg>
                Refund
              </NavLink>
            </li>
            <li className="cursor-pointer text-gray-700 dark:text-white hover:text-white">
              <NavLink
                to="/laporan"
                className={({ isActive }) =>
                  `relative flex items-center px-4 py-3 transition-all duration-300 group
                ${
                  isActive
                    ? "text-blue-500 font-semibold before:content-[''] before:absolute before:left-0 before:top-0 before:h-full before:w-[3px] before:bg-blue-500 before:rounded-r-md"
                    : "text-gray-600 dark:text-gray-300 hover:text-blue-500"
                }`
                }
              >
                {/* Garis Kanan */}
                <span
                  className={`absolute right-0 top-0 h-full w-[3px] rounded-r-md bg-blue-500 transition-all duration-300 opacity-0 group-hover:opacity-100`}
                ></span>
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  strokeWidth="1.5"
                  stroke="currentColor"
                  className="w-4 mr-3"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M3.75 3v11.25A2.25 2.25 0 0 0 6 16.5h2.25M3.75 3h-1.5m1.5 0h16.5m0 0h1.5m-1.5 0v11.25A2.25 2.25 0 0 1 18 16.5h-2.25m-7.5 0h7.5m-7.5 0-1 3m8.5-3 1 3m0 0 .5 1.5m-.5-1.5h-9.5m0 0-.5 1.5M9 11.25v1.5M12 9v3.75m3-6v6"
                  />
                </svg>
                Laporan
              </NavLink>
            </li>
          </ul>
        </nav>
      </div>
      {/* NAVBAR BAWAH */}
      <div className="border-t dark:border-gray-700 dark:text-white pb-8 bg-white text-gray-400 dark:bg-gray-700">
        <ul className="flex flex-col">
          <li className="cursor-pointer hover:text-white">
            <NavLink
              to=""
              className="relative flex items-center w-full text-left px-4 py-3 transition-all duration-300 group text-gray-600 dark:text-gray-300 hover:text-red-500 hover:before:content-[''] hover:before:absolute hover:before:left-0 hover:before:top-0 hover:before:h-full hover:before:w-[3px] hover:before:bg-red-500 hover:before:rounded-r-md"
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                strokeWidth={1.5}
                stroke="currentColor"
                className="size-6 w-4 mr-3"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="M9.594 3.94c.09-.542.56-.94 1.11-.94h2.593c.55 0 1.02.398 1.11.94l.213 1.281c.063.374.313.686.645.87.074.04.147.083.22.127.325.196.72.257 1.075.124l1.217-.456a1.125 1.125 0 0 1 1.37.49l1.296 2.247a1.125 1.125 0 0 1-.26 1.431l-1.003.827c-.293.241-.438.613-.43.992a7.723 7.723 0 0 1 0 .255c-.008.378.137.75.43.991l1.004.827c.424.35.534.955.26 1.43l-1.298 2.247a1.125 1.125 0 0 1-1.369.491l-1.217-.456c-.355-.133-.75-.072-1.076.124a6.47 6.47 0 0 1-.22.128c-.331.183-.581.495-.644.869l-.213 1.281c-.09.543-.56.94-1.11.94h-2.594c-.55 0-1.019-.398-1.11-.94l-.213-1.281c-.062-.374-.312-.686-.644-.87a6.52 6.52 0 0 1-.22-.127c-.325-.196-.72-.257-1.076-.124l-1.217.456a1.125 1.125 0 0 1-1.369-.49l-1.297-2.247a1.125 1.125 0 0 1 .26-1.431l1.004-.827c.292-.24.437-.613.43-.991a6.932 6.932 0 0 1 0-.255c.007-.38-.138-.751-.43-.992l-1.004-.827a1.125 1.125 0 0 1-.26-1.43l1.297-2.247a1.125 1.125 0 0 1 1.37-.491l1.216.456c.356.133.751.072 1.076-.124.072-.044.146-.086.22-.128.332-.183.582-.495.644-.869l.214-1.28Z"
                />
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="M15 12a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z"
                />
              </svg>
              Pengaturan
            </NavLink>
          </li>
          <LogoutButton />
        </ul>
      </div>
    </aside>
  );
}
