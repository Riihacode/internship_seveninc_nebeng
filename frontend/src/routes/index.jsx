import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import { AuthProvider } from "../context/AuthProvider.jsx";
import Login from "../pages/Login.jsx";
import Home from "../pages/Home.jsx";
import Mitra from "../pages/Mitra.jsx";
import ProtectedRoute from "../components/ProtectedRoute.jsx";
import Customer from "../pages/Customer.jsx";
import Pesanan from "../pages/Pesanan.jsx";
import DetailPesanan from "../pages/DetailPasanan.jsx";
import Refund from "../pages/Refund.jsx";
import Laporan from "../pages/Laporan.jsx";
import Pengaturan from "../pages/Pengaturan.jsx";
import DetailMitra from "../pages/DetailMitra.jsx";
import DetailCustomer from "../pages/DetailCustomer.jsx";

export default function AppRoutes() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route
            path="/home"
            element={
              <ProtectedRoute>
                <Home />
              </ProtectedRoute>
            }
          />
          <Route
            path="/mitra"
            element={
              <ProtectedRoute>
                <Mitra />
              </ProtectedRoute>
            }
          />
          <Route
            path="/mitra/:id"
            element={
              <ProtectedRoute>
                <DetailMitra />
              </ProtectedRoute>
            }
          />
          <Route
            path="/customer"
            element={
              <ProtectedRoute>
                <Customer />
              </ProtectedRoute>
            }
          />
          <Route
            path="/customer/:id"
            element={
              <ProtectedRoute>
                <DetailCustomer />
              </ProtectedRoute>
            }
          />
          <Route
            path="/orders"
            element={
              <ProtectedRoute>
                <Pesanan />
              </ProtectedRoute>
            }
          />
          <Route
            path="/orders/:booking_type/:id"
            element={
              <ProtectedRoute>
                <DetailPesanan />
              </ProtectedRoute>
            }
          />
          <Route
            path="/refund"
            element={
              <ProtectedRoute>
                <Refund />
              </ProtectedRoute>
            }
          />
          <Route
            path="/laporan"
            element={
              <ProtectedRoute>
                <Laporan />
              </ProtectedRoute>
            }
          />
          <Route
            path="/pengaturan"
            element={
              <ProtectedRoute>
                <Pengaturan />
              </ProtectedRoute>
            }
          />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}
