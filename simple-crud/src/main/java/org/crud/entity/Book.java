package org.crud.entity;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection = "book")
public class Book extends ReactivePanacheMongoEntity {

	private ObjectId id;
	private String name;
	private String author;
	private String status;
}