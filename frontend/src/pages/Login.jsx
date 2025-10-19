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
      const { user, token } = response.data.data;
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
    <div className="bg-blue-500 overflow-hidden h-screen">
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
        <div
          aria-hidden="true"
          className="absolute inset-x-0 -top-40 -z-10 transform-gpu overflow-hidden sm:-top-80"
        >
          <div
            style={{
              clipPath: "polygon(M10 45 Q 50 10 140 80 T 100 80 V 80 H 10 Z)",
            }}
            className="relative left-[calc(50%-11rem)] aspect-1155/678 w-144.5 -translate-x-1/2 rotate-30 bg-linear-to-tr from-[#050e99] to-[#291ce3] opacity-100 sm:left-[calc(50%-30rem)] sm:w-288.75 rounded-[50px]"
          />
        </div>
        <div className="mx-auto max-w-xl py-20 sm:py-48 lg:py-35">
          <div className="bg-white rounded-xl p-5 min-w-fit">
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
                      } focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600`}
                    >
                      {loading ? "Memproses..." : "Masuk"}
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
        <div
          aria-hidden="true"
          className="absolute inset-x-0 top-[calc(100%-13rem)] -z-10 transform-gpu overflow-hidden sm:top-[calc(100%-30rem)]"
        >
          <div
            style={{
              clipPath:
                "polygon(74.1% 44.1%, 100% 61.6%, 97.5% 50%, 85.5% 0.1%, 80.7% 2%, 72.5% 32.5%, 60.2% 80%, 52.4% 68.1%, 47.5% 58.3%, 45.2% 34.5%, 27.5% 76.7%, 0.1% 64.9%, 17.9% 100%, 27.6% 76.8%, 76.1% 97.7%, 74.1% 44.1%)",
            }}
            className="relative left-[calc(50%+3rem)] aspect-1155/678 w-144.5 -translate-x-1/2 bg-linear-to-tr from-[#ff80b5] to-[#9089fc] opacity-100 sm:left-[calc(50%+36rem)] sm:w-288.75"
          />
        </div>
      </div>
    </div>
  );
}
