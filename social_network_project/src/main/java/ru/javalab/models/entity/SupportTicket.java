package ru.javalab.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.javalab.models.enums.State;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "support_service")
public class SupportTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_of_ticket")
    private Long id;

    @Column(name="token_of_ticket")
    private String token;

    @Column(name = "user_id_of_ticket")
    private Long userId;

    @Column(name = "message_of_ticket")
    private String text;

    @Transient
    private User user;
}
