package org.crud;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.bson.types.ObjectId;
import org.crud.entity.Book;
import org.crud.repository.BookRepository;
import org.jboss.logging.Logger;

import io.smallrye.mutiny.*;

@Path("/crud")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CrudApplication {

	@Inject
	BookRepository repository;

	private static final Logger loger = Logger.getLogger(CrudApplication.class);

	@GET
	@Path("/book")
	public Multi<Book> findAll() {
		loger.infof("Libros en Stock");
		return this.repository.streamAllBook();
	}

	@GET
	@Path("/book/id/{id}")
	public Uni<Book> findById(@PathParam("id") String id) {
		return this.repository.findById(new ObjectId(id));
	}
	
	@POST
	@Path("/book")
	public Uni<Response> create(Book book) {
		return this.repository.persist(book).map(respose -> Response.status(201).build());
	}
	
	@PUT
	@Path("/book/id/{id}")
	public Uni<Response> update(@PathParam("id") String id, Book book) {
		book.setId(new ObjectId(id));
		return book.update().map(r -> Response.accepted().build());
	}
	
	@DELETE
	@Path("/book/id/{id}")
	public Uni<Response> delete(@PathParam("id") String id) {
		return this.repository.deleteById(new ObjectId(id)).map(response -> Response.status(204).build());
	}
	
	@GET
	@Path("/book/search/{name}")
	public Uni<Response> search(@PathParam("name") String name) {
		return this.repository.findByAuthor(name).map(response -> Response.status(200).build());
	}
}