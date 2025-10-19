<?php

namespace App\Http\Repositories;

use App\Models\PassengerTransaction;

class PassengerTransactionRepository
{
    protected $model;

    public function __construct(PassengerTransaction $transaction)
    {
        $this->model = $transaction;
    }

    // Ambil semua transaksi beserta relasi
    public function getAll()
    {
        return $this->model
            ->with(['customer', 'passengerRideBooking', 'paymentMethod'])
            ->orderBy('transaction_date', 'DESC')
            ->get();
    }

    // Ambil transaksi berdasarkan ID
    public function findById($id)
    {
        return $this->model
            ->with(['customer', 'passengerRideBooking', 'paymentMethod'])
            ->find($id);
    }

    // Ambil transaksi berdasarkan customer
    public function getByCustomer($customerId)
    {
        return $this->model
            ->where('customer_id', $customerId)
            ->with(['passengerRideBooking', 'paymentMethod'])
            ->orderBy('transaction_date', 'DESC')
            ->get();
    }

    // Ambil transaksi berdasarkan booking
    public function getByBooking($bookingId)
    {
        return $this->model
            ->where('passenger_ride_booking_id', $bookingId)
            ->with(['customer', 'paymentMethod'])
            ->first();
    }

    // Tambah transaksi baru
    public function create(array $data)
    {
        return $this->model->create($data);
    }

    // Update transaksi
    public function update($id, array $data)
    {
        $transaction = $this->model->findOrFail($id);
        $transaction->update($data);
        return $transaction;
    }

    // Hapus transaksi
    public function delete($id)
    {
        $transaction = $this->model->findOrFail($id);
        return $transaction->delete();
    }
}
