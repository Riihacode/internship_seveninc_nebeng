import orderService from "../service/orderService";
import { useState, useEffect, useCallback } from "react";
import useAuth from "./useAuth";

export function useOrders() {
  const { user, loading: authLoading } = useAuth();
  const [orders, setOrders] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const fetchOrders = useCallback(async () => {
    if (authLoading || !user) return;
    setLoading(true);
    setError(null);
    try {
      const data = await orderService.getAll();
      console.log("📦 Data orders dari service:", data);
      setOrders(data || []);
    } catch (error) {
      setError(error);
    } finally {
      setLoading(false);
    }
  }, [authLoading, user]);

  // const createOrder = async (payload) => {
  //   setLoading(true);
  //   try {
  //     await orderService.create(payload);
  //     await fetchOrders(); // refresh data
  //   } catch (error) {
  //     setError(error.message);
  //   } finally {
  //     setLoading(false);
  //   }
  // };

  // const updateOrder = async (id, payload) => {
  //   setLoading(true);
  //   try {
  //     await orderService.update(id, payload);
  //     await fetchOrders();
  //   } catch (error) {
  //     setError(error.message);
  //   } finally {
  //     setLoading(false);
  //   }
  // };

  // const deleteOrder = async (id) => {
  //   setLoading(true);
  //   try {
  //     await orderService.remove(id);
  //     setOrders((prev) => prev.filter((d) => d.id !== id));
  //   } catch (error) {
  //     setError(error.message);
  //   } finally {
  //     setLoading(false);
  //   }
  // };

  useEffect(() => {
    if (!authLoading && user) {
      fetchOrders();
    }
  }, [fetchOrders, authLoading, user]);

  return {
    orders,
    loading,
    error,
    fetchOrders,
    // createOrder,
    // updateOrder,
    // deleteOrder,
  };
}
