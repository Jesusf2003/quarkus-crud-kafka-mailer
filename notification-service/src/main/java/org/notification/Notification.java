package org.notification;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.reactive.ReactiveMailer;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;

@Path("/notification")
public class Notification {

	@Inject
	Mailer mailer;

	@GET
	@Blocking
	public void sendEmail() {
		mailer.send(Mail.withText("fernandocanales554@gmail.com", "Ahoy from Quarkus",
				"A simple email sent from a Quarkus application."));
	}

	@Inject
	ReactiveMailer reactiveMailer;

	@GET
	@Path("/reactive")
	public Uni<Void> sendEmailUsingReactiveMailer() {
		return reactiveMailer.send( // <4>
				Mail.withText("fernandocanales554@gmail.com", "Ahoy from Quarkus Reactivo",
						"A simple email sent from a Quarkus application using the reactive API."));
	}
}