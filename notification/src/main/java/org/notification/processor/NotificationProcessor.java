package org.notification.processor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.notification.domain.Payment;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.reactive.ReactiveMailer;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.kafka.Record;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class NotificationProcessor {

	@Inject
	ReactiveMailer reactiveMailer;

	@Incoming("payment-requests")
	@SneakyThrows
	public Uni<Void> process(Record<String, String> record) {

		ObjectMapper objectMapper = new ObjectMapper();
		var payment = objectMapper.readValue(record.value(), Payment.class);

		try {

			log.info("Topics paymentid {} | mount {}", record.key(), payment.getMount());
			log.info("Aqui llegue");

		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		return reactiveMailer.send( // <4>
				Mail.withText("correo@dominio", "Pago Realizado COD " + record.key(),
						"La orden de pago es " + record.key() + " con el monto de " + payment.getMount()));
	}
}