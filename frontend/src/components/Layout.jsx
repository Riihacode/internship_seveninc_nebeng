import React from "react";
import Header from "./Header";
import SideBar from "./SideBar";

export default function layout({ children }) {
  return (
    <div className="flex min-h-screen">
      {/* Sidebar */}
      <SideBar />

      {/* Konten Utama */}
      <div className="flex-1 flex flex-col bg-gray-100 w-screen">
        {/* Header */}
        <Header />

        {/* Isi halaman */}
        <main className="p-6 flex-1">{children}</main>
      </div>
    </div>
  );
}
