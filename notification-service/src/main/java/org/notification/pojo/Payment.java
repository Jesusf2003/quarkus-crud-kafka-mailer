package org.notification.pojo;

import java.util.UUID;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Payment {

	private UUID paymentId;
	private Integer userId;
	private Integer cashierId;
	private float mount;
	private String comment;
	private String createdAt;
}