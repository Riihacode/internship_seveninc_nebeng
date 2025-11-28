import refundService from "../service/RefundService";
import { useState, useEffect, useCallback } from "react";
import useAuth from "./useAuth";

export function useRefunds({ search = "", status = "" } = {}) {
  const { user, loading: authLoading } = useAuth();
  const [refunds, setRefunds] = useState([]);
  const [error, setError] = useState(null);
  const [isLoadingList, setIsLoadingList] = useState(false);
  const [isLoadingDetail, setIsLoadingDetail] = useState(false);
  const [meta, setMeta] = useState({
    current_page: 1,
    last_page: 1,
    per_page: 10,
    total: 0,
  });
  const [links, setLinks] = useState({
    next_page_url: null,
    prev_page_url: null,
  });

  const handleError = (err) => {
    console.error("Order hook error : ", err);
  };

  const fetchRefunds = useCallback(
    async (page = 1) => {
      if (authLoading || !user) return;
      setIsLoadingList(true);
      setError(null);
      try {
        const response = await refundService.getAll({ page, search, status });
        console.log("📦 Data refund:", response);
        if (response.success) {
          setRefunds(response.data || []);
          setMeta(response.meta || {});
          setLinks(response.links || {});
        } else {
          setRefunds([]);
          setMeta({
            current_page: 1,
            last_page: 1,
            per_page: 10,
            total: 0,
            next_page_url: null,
            prev_page_url: null,
          });
        }
      } catch (err) {
        handleError(err);
      } finally {
        setIsLoadingList(false);
      }
    },
    [authLoading, user, search, status]
  );

  const fetchRefundById = useCallback(async (type, bookingId) => {
    if (!type || !bookingId) return;
    setIsLoadingDetail(true);
    setError(null);
    try {
      const data = await refundService.getById(type, bookingId);
      setRefunds(data || null);
    } catch (error) {
      handleError(error);
    } finally {
      setIsLoadingDetail(false);
    }
  }, []);

  useEffect(() => {
    if (!authLoading && user) {
      fetchRefunds();
    }
  }, [fetchRefunds, authLoading, user]);

  return {
    refunds,
    links,
    meta,
    isLoadingDetail,
    isLoadingList,
    error,
    fetchRefunds,
    fetchRefundById,
  };
}
