import { Navigate } from "react-router-dom";
import useAuth from "../hooks/useAuth";

export default function ProtectedRoute({ children, requiredRole }) {
  const { user, loading, isAuthenticated, role } = useAuth();

  if (loading) {
    return <div>Loading...</div>;
  }

  if (!user) {
    return <Navigate to="/" replace />;
  }

  if (Array.isArray(requiredRole)) {
    if (!requiredRole.includes(role)) {
      return <Navigate to="/unauthorized" replace />;
    }
  } else if (requiredRole && role !== requiredRole) {
    return <Navigate to="/unauthorized" replace />;
  }

  if (!isAuthenticated()) {
    return <Navigate to="/" replace />;
  }

  return children;
}
