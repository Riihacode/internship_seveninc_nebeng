import transactionService from "../service/transactionService";
import { useState, useEffect, useCallback } from "react";
import useAuth from "./useAuth";

export function useTransactions() {
  const { user, loading: authLoading } = useAuth();
  const [transaction, setTransaction] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const fetchTransactions = useCallback(async () => {
    if (authLoading || !user) return;
    setLoading(true);
    setError(null);
    try {
      const data = await transactionService.getAll();
      console.log("📦 Data transaction:", data);
      setTransaction(data || []);
    } catch (error) {
      setError(error);
    } finally {
      setLoading(false);
    }
  }, [authLoading, user]);

  const fetchTransactionBooking = useCallback(async (type, bookingId) => {
    if (!type || !bookingId) return;
    setLoading(true);
    setError(null);
    try {
      const data = await transactionService.getByBooking(type, bookingId);
      console.log("📦 Data transaction by booking:", data);
      setTransaction(data || null);
    } catch (error) {
      setError(error);
    } finally {
      setLoading(false);
    }
  }, []);

  // const fetchTransactionById = useCallback(async (type, bookingId) => {
  //   if (!type || !bookingId) return;
  //   setLoading(true);
  //   setError(null);
  //   try {
  //     const data = await transactionService.getById(type, bookingId);
  //     setTransaction(data || null);
  //   } catch (error) {
  //     setError(error);
  //   } finally {
  //     setLoading(false);
  //   }
  // }, []);

  useEffect(() => {
    if (!authLoading && user) {
      fetchTransactions();
    }
  }, [fetchTransactions, authLoading, user]);

  return {
    transaction,
    loading,
    error,
    fetchTransactions,
    fetchTransactionBooking,
  };
}
