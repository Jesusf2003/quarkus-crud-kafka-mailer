package org.payment.domain;

import java.util.UUID;

import javax.persistence.*;

import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor 
@NoArgsConstructor
public class Payment {
    @Id
    private UUID paymentId;
    private Integer userId;
    private Integer cashierId;
    private float mount;
    private String comment;
    private String createdAt;
}