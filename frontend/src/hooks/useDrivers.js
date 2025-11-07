import driverService from "../service/driverService";
import { useState, useEffect, useCallback } from "react";
import useAuth from "./useAuth";

export function useDrivers() {
  const { user, loading: authLoading } = useAuth();
  const [drivers, setDrivers] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const fetchDrivers = useCallback(async () => {
    if (authLoading || !user) return;
    setLoading(true);
    setError(null);
    try {
      const data = await driverService.getAll();
      setDrivers(data || []);
    } catch (error) {
      setError(error);
    } finally {
      setLoading(false);
    }
  }, [authLoading, user]);

  const createDriver = async (payload) => {
    setLoading(true);
    try {
      await driverService.create(payload);
      await fetchDrivers(); // refresh data
    } catch (error) {
      setError(error.message);
    } finally {
      setLoading(false);
    }
  };

  const updateDriver = async (id, payload) => {
    setLoading(true);
    try {
      await driverService.update(id, payload);
      await fetchDrivers();
    } catch (error) {
      setError(error.message);
    } finally {
      setLoading(false);
    }
  };

  const verifyDriver = async (id, payload) => {
    setLoading(true);
    try {
      await driverService.verify(id, payload);
      await fetchDrivers();
    } catch (error) {
      setError(error.message);
    } finally {
      setLoading(false);
    }
  };

  const deleteDriver = async (id) => {
    setLoading(true);
    try {
      await driverService.remove(id);
      setDrivers((prev) => prev.filter((d) => d.id !== id));
    } catch (error) {
      setError(error.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    if (!authLoading && user) {
      fetchDrivers();
    }
  }, [fetchDrivers, authLoading, user]);

  return {
    drivers,
    loading,
    error,
    fetchDrivers,
    verifyDriver,
    createDriver,
    updateDriver,
    deleteDriver,
  };
}
