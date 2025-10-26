export default function SearchBar() {
  return (
    <div className=" flex items-center justify-start bg-white min-h-fit p-3 rounded-2xl">
      <div className="flex items-center justify-center pr-2 mr-2 border-r-1 border-gray-300">
        <div className="">
          <svg
            width="22"
            height="24"
            viewBox="0 0 22 24"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              fillRule="evenodd"
              clipRule="evenodd"
              d="M10.515 9.75C15.908 9.75 20.28 7.73528 20.28 5.25C20.28 2.76472 15.908 0.75 10.515 0.75C5.12193 0.75 0.75 2.76472 0.75 5.25C0.75 7.73528 5.12193 9.75 10.515 9.75Z"
              stroke="#0C1F3D"
              strokeWidth="1.5"
              strokeLinecap="round"
              strokeLinejoin="round"
            />
            <path
              d="M0.75 5.25C0.752534 9.76548 3.86091 13.688 8.26152 14.729V21C8.26152 22.2426 9.27043 23.25 10.515 23.25C11.7595 23.25 12.7684 22.2426 12.7684 21V14.729C17.169 13.688 20.2774 9.76548 20.28 5.25"
              stroke="#0C1F3D"
              strokeWidth="1.5"
              strokeLinecap="round"
              strokeLinejoin="round"
            />
          </svg>
        </div>
      </div>
      <div className="flex items-center justify-center pr-2 mr-2 border-r-1 border-gray-300">
        {/* <select
            className=""
            onChange={(e) => onFilterChange(e.target.value)}
          >
            <option value="">Status</option>
            {filterOptions.map((opt) => (
              <option key={opt.value} value={opt.value}>
                {opt.label}
              </option>
            ))}
          </select> */}
        <select name="" id="" className="">
          <option value="">Status</option>
        </select>
      </div>
      <div className="flex items-center justify-center pr-2 mr-2 border-r-1 border-gray-300">
        <button>
          {" "}
          <svg
            width="13"
            height="15"
            viewBox="0 0 13 15"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
            className="inline mb-2 m-1"
          >
            <path
              d="M6.00922 3V0L2.25346 3.75L6.00922 7.5V4.5C8.49553 4.5 10.5161 6.5175 10.5161 9C10.5161 11.4825 8.49553 13.5 6.00922 13.5C3.5229 13.5 1.5023 11.4825 1.5023 9H0C0 12.315 2.68912 15 6.00922 15C9.32931 15 12.0184 12.315 12.0184 9C12.0184 5.685 9.32931 3 6.00922 3Z"
              fill="#FC2D2D"
            />
          </svg>
          Reset Filter
        </button>
      </div>
      <div className="flex items-center justify-center">
        <div className="max-w-sm space-y-3 border-1 border-gray-300 rounded-2xl">
          <input
            type="text"
            className="py-2.5 sm:py-3 px-4 block w-full border-gray-200 rounded-2xl sm:text-sm focus:border-blue-500 focus:ring-blue-500 disabled:opacity-50 disabled:pointer-events-none dark:bg-neutral-900 dark:border-neutral-700 dark:text-neutral-400 dark:placeholder-neutral-500 dark:focus:ring-neutral-600"
            placeholder="Cari disini"
          />
        </div>
      </div>
    </div>
  );
}
