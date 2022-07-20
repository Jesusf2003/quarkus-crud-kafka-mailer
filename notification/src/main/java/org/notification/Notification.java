package org.notification;

import javax.inject.Inject;
import javax.ws.rs.*;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.reactive.ReactiveMailer;
import io.smallrye.common.annotation.Blocking;

@Path("/notification")
public class Notification {
	@Inject
	Mailer mailer;

	@Inject
	ReactiveMailer reactiveMailer;

	@GET
	@Blocking
	public void hello() {
		mailer.send(Mail.withText("fernandocanales554@gmail.com", "Saludos desde Quarkus", "Esta es una aplicación sencilla usando mailer."));
	}
}