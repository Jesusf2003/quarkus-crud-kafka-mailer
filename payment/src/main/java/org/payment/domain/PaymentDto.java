package org.payment.domain;

import lombok.Data;

@Data
public class PaymentDto {
    private Integer userId;
    private Integer cashierId;
    private float mount;
    private String comment;
}