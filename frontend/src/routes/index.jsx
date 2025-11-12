import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import { AuthProvider } from "../context/AuthProvider.jsx";
import Login from "../pages/Login.jsx";
import Home from "../pages/admin/Home.jsx";
import Mitra from "../pages/shared/Mitra.jsx";
import ProtectedRoute from "../components/ProtectedRoute.jsx";
import Customer from "../pages/shared/Customer.jsx";
import Pesanan from "../pages/shared/Pesanan.jsx";
import DetailPesanan from "../pages/shared/DetailPasanan.jsx";
import Refund from "../pages/shared/Refund.jsx";
import Laporan from "../pages/admin/Laporan.jsx";
import Pengaturan from "../pages/shared/Pengaturan.jsx";
import DetailMitra from "../pages/shared/DetailMitra.jsx";
import DetailCustomer from "../pages/shared/DetailCustomer.jsx";
import Dashboard from "../pages/superAdmin/Dashboard.jsx";

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
              <ProtectedRoute requiredRole={["admin", "superadmin"]}>
                <Mitra />
              </ProtectedRoute>
            }
          />
          <Route
            path="/mitra/:id"
            element={
              <ProtectedRoute requiredRole={["admin", "superadmin"]}>
                <DetailMitra />
              </ProtectedRoute>
            }
          />
          <Route
            path="/customer"
            element={
              <ProtectedRoute requiredRole={["admin", "superadmin"]}>
                <Customer />
              </ProtectedRoute>
            }
          />
          <Route
            path="/customer/:id"
            element={
              <ProtectedRoute requiredRole={["admin", "superadmin"]}>
                <DetailCustomer />
              </ProtectedRoute>
            }
          />
          <Route
            path="/orders"
            element={
              <ProtectedRoute requiredRole={["admin", "superadmin"]}>
                <Pesanan />
              </ProtectedRoute>
            }
          />
          <Route
            path="/orders/:booking_type/:id"
            element={
              <ProtectedRoute requiredRole={["admin", "superadmin"]}>
                <DetailPesanan />
              </ProtectedRoute>
            }
          />
          <Route
            path="/refund"
            element={
              <ProtectedRoute requiredRole={["admin", "superadmin"]}>
                <Refund />
              </ProtectedRoute>
            }
          />
          <Route
            path="/laporan"
            element={
              <ProtectedRoute requiredRole="admin">
                <Laporan />
              </ProtectedRoute>
            }
          />
          <Route
            path="/pengaturan"
            element={
              <ProtectedRoute requiredRole={["admin", "superadmin"]}>
                <Pengaturan />
              </ProtectedRoute>
            }
          />
          <Route
            path="/sa/dashboard"
            element={
              <ProtectedRoute requiredRole="superadmin">
                <Dashboard />
              </ProtectedRoute>
            }
          />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}
