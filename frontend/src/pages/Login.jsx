"use client";

import { Dialog, DialogPanel } from "@headlessui/react";
import { Bars3Icon, XMarkIcon } from "@heroicons/react/24/outline";
import { useNavigate } from "react-router-dom";
import { loginUser } from "../api/auth";
import { useState } from "react";
import useAuth from "../hooks/useAuth";

export default function Login() {
  const { login } = useAuth();
  const [userIdentifier, setUserIdentifier] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    setLoading(true);

    try {
      const response = await loginUser({ userIdentifier, password });
      const { user, token } = response.data;
      login(user, token);
      navigate("/home");
    } catch (error) {
      console.error("Login error :", error);
      alert("Login gagal, periksa kembali email / username dan password");
    } finally {
      setLoading(false);
    }
  };
  return (
    <div className="bg-blue-500 overflow-hidden h-screen flex items-center justify-center">
      <header className="absolute inset-x-0 top-0 z-50">
        <nav
          aria-label="Global"
          className="flex items-center justify-between p-6 lg:px-8"
        >
          <div className="flex lg:flex-1">
            <a href="#" className="-m-1.5 p-1.5">
              <span className="sr-only">Your Company</span>
              <img
                alt=""
                src="https://tailwindcss.com/plus-assets/img/logos/mark.svg?color=indigo&shade=500"
                className="h-8 w-auto"
              />
            </a>
          </div>
        </nav>
      </header>

      <div className="relative isolate px-6 lg:px-8">
        <div className=" flex justify-between">
          <div
            aria-hidden="true"
            className="fixed flex-row inset-x-0 top-0 -z-10 transform-gpu left-0 overflow-hidden"
          >
            <svg
              width="655"
              height="720"
              viewBox="0 0 655 720"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
              className="w-auto h-auto max-w-[50vw] max-h-[50vh]"
            >
              <path
                opacity="0.6"
                fillRule="evenodd"
                clipRule="evenodd"
                d="M53.5123 -124.947C-178.73 -40.418 -298.476 216.376 -213.946 448.619C-168.757 572.775 93.3961 360.789 206.979 406.621C305.861 446.521 251.534 755.418 359.62 716.077C591.863 631.548 711.608 374.754 627.079 142.511C542.549 -89.7317 285.755 -209.477 53.5123 -124.947Z"
                fill="#0C1F3D"
                fillOpacity="0.1"
              />
            </svg>
          </div>
          <div className="fixed flex-row right-0 top-0 -z-10 transform-gpu overflow-hidden">
            <svg
              width="750"
              height="505"
              viewBox="0 0 750 505"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
              className="w-auto h-auto max-w-[50vw] max-h-[50vh]"
            >
              <path
                opacity="0.541829"
                fillRule="evenodd"
                clipRule="evenodd"
                d="M8.46914 -99.9152C-41.4964 183.454 147.714 453.675 431.083 503.64C582.571 530.352 521.439 142.63 633.769 54.7875C731.56 -21.6852 1011.38 212.907 1034.64 81.0262C1084.6 -202.343 895.394 -472.564 612.025 -522.529C328.656 -572.495 58.4347 -383.284 8.46914 -99.9152Z"
                fill="#0C1F3D"
                fillOpacity="0.1"
              />
            </svg>
          </div>
        </div>
        <div className=" w-full max-w-xl z-10">
          <div className="bg-white rounded-xl p-5 min-w-lg">
            <div className="flex min-h-full flex-col justify-center px-6 py-12 lg:px-8">
              <div className="sm:mx-auto sm:w-full sm:max-w-sm">
                <h2 className="mt-3 text-center text-2xl/9 font-bold tracking-tight text-black">
                  Masuk
                </h2>
                <p className="font-medium text-gray-600 text-center mt-2">
                  Masukkan email dan password anda
                </p>
              </div>

              <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
                <form
                  onSubmit={handleLogin}
                  method="POST"
                  className="space-y-6"
                >
                  <div>
                    <label
                      htmlFor="userIdentifier"
                      className="block text-sm/6 font-medium text-black"
                    >
                      Email / Username
                    </label>
                    <div className="mt-2">
                      <input
                        id="userIdentifier"
                        name="userIdentifier"
                        value={userIdentifier}
                        onChange={(e) => setUserIdentifier(e.target.value)}
                        type="text"
                        required
                        autoComplete="username"
                        className="block w-full rounded-md bg-white/5 px-3 py-1.5 text-base text-black outline-1 -outline-offset-1 outline-gray-500 placeholder:text-gray-500 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-500 sm:text-sm/6"
                      />
                    </div>
                  </div>

                  <div>
                    <div className="flex items-center justify-between">
                      <label
                        htmlFor="password"
                        className="block text-sm/6 font-medium text-black"
                      >
                        Password
                      </label>
                    </div>
                    <div className="mt-2">
                      <input
                        id="password"
                        name="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        type="password"
                        required
                        autoComplete="current-password"
                        className="block w-full rounded-md bg-white/5 px-3 py-1.5 text-base text-black outline-1 -outline-offset-1 outline-gray-500 placeholder:text-gray-500 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-500 sm:text-sm/6"
                      />
                    </div>
                  </div>

                  <div>
                    <button
                      type="submit"
                      className={`flex w-full justify-center rounded-md px-3 py-2 text-sm font-semibold text-white ${
                        loading
                          ? "bg-gray-400 cursor-not-allowed"
                          : "bg-indigo-600 hover:bg-indigo-500"
                      } focus-visible:outline focus-visible:outline-offset-2 focus-visible:outline-indigo-600`}
                    >
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
                      ) : (
                        "Masuk"
                      )}
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
        <div className="flex justify-between w-full min-h-full">
          <div aria-hidden="true" className="fixed bottom-0 left-0 -z-10">
            <svg
              width="554"
              height="430"
              viewBox="0 0 554 430"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
              className="w-auto h-auto max-w-[40vw] max-h-[40vh]"
            >
              <path
                fillRule="evenodd"
                clipRule="evenodd"
                d="M106.5 895C353.647 895 554 694.647 554 447.5C554 315.376 235.153 424.916 144.095 343C64.8233 271.687 221.523 0 106.5 0C-140.647 0 -341 200.353 -341 447.5C-341 694.647 -140.647 895 106.5 895Z"
                fill="#0C1F3D"
                fillOpacity="0.1"
              />
            </svg>
          </div>
          <div aria-hidden="true" className="fixed bottom-0 right-0 -z-10">
            <svg
              width="741"
              height="551"
              viewBox="0 0 741 551"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
              className="w-auto h-auto max-w-[50vw] max-h-[50vh]"
            >
              <path
                fillRule="evenodd"
                clipRule="evenodd"
                d="M1141.38 667.958C1196.52 355.219 987.699 56.9905 674.96 1.84616C507.77 -27.6339 575.24 400.274 451.267 497.221C343.34 581.62 34.5121 322.713 8.8477 468.263C-46.2967 781.002 162.525 1079.23 475.264 1134.38C788.004 1189.52 1086.23 980.698 1141.38 667.958Z"
                fill="#0C1F3D"
                fillOpacity="0.1"
              />
            </svg>
          </div>
        </div>
      </div>
    </div>
  );
}
