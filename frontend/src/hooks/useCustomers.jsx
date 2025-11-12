import customerService from "../service/customerService";
import { useState, useEffect, useCallback } from "react";
import useAuth from "./useAuth";

export function useCustomers() {
  const { user, loading: authLoading } = useAuth();
  const [customers, setCustomers] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const fetchCustomers = useCallback(async () => {
    if (authLoading || !user) return;
    setLoading(true);
    setError(null);
    try {
      const data = await customerService.getAll();
      setCustomers(data || []);
    } catch (error) {
      setError(error);
    } finally {
      setLoading(false);
    }
  }, [authLoading, user]);

  const createCustomer = async (payload) => {
    setLoading(true);
    try {
      await customerService.create(payload);
      await fetchCustomers(); // refresh data
    } catch (error) {
      setError(error.message);
    } finally {
      setLoading(false);
    }
  };

  const updateCustomer = async (id, payload) => {
    setLoading(true);
    try {
      await customerService.update(id, payload);
      await fetchCustomers();
    } catch (error) {
      setError(error.message);
    } finally {
      setLoading(false);
    }
  };

  const verifyCustomer = async (id, payload) => {
    setLoading(true);
    try {
      await customerService.verify(id, payload);
      await fetchCustomers();
    } catch (error) {
      setError(error.message);
    } finally {
      setLoading(false);
    }
  };

  const deleteCustomer = async (id) => {
    setLoading(true);
    try {
      await customerService.remove(id);
      setCustomers((prev) => prev.filter((d) => d.id !== id));
    } catch (error) {
      setError(error.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    if (!authLoading && user) {
      fetchCustomers();
    }
  }, [fetchCustomers, authLoading, user]);

  return {
    customers,
    loading,
    error,
    fetchCustomers,
    createCustomer,
    updateCustomer,
    deleteCustomer,
    verifyCustomer,
  };
}
