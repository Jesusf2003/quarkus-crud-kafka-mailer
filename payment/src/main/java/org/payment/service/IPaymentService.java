package org.payment.service;

import java.util.UUID;

import org.payment.domain.Payment;

public interface IPaymentService {

	void createPayment(Payment order);

	Payment getPaymentById(UUID id);
}