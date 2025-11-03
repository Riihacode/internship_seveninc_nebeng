export default function SearchBar() {
  return (
    <div className=" flex items-center justify-between bg-white min-h-fit p-3 rounded-2xl">
      <div className="w-md space-y-3 border-1 border-gray-300 rounded-xl">
        <input
          type="text"
          className="py-2.5 sm:py-3 px-4 block w-full border-gray-200 rounded-2xl sm:text-sm focus:border-blue-500 focus:ring-blue-500 disabled:opacity-50 disabled:pointer-events-none dark:bg-neutral-900 dark:border-neutral-700 dark:text-neutral-400 dark:placeholder-neutral-500 dark:focus:ring-neutral-600"
          placeholder="Cari disini"
        />
      </div>
      <div className="flex">
        {/* <input
          className="bg-green-400 p-1.5"
          placeholder="strat"
          type="date"
        />
        <input
          className="bg-green-400 p-1.5"
          placeholder="end"
          type="date"
        /> */}
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
        <select
          name=""
          id=""
          className="bg-gray-100 shadow-sm p-1.5 rounded-lg hover:bg-blue-400"
        >
          <option value="">Status</option>
        </select>
      </div>
    </div>
  );
}
