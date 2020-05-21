package ru.javalab.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "verification_token")
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_of_verification")
    private Long id;

    @Column(name="token_of_verification")
    private String token;

    @Column(name="expiry_date_of_verification")
    private Timestamp expiryDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "id_user")
    private User user;
}
