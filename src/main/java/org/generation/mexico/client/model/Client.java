package org.generation.mexico.client.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 35)
    private String name;

    @Column(length = 35)
    private String lastName;

    @Column(length = 35)
    private int phoneNumber;

    @Column(length = 40)
    @Email(message = "El formato del email es incorrecto")
    private String email;

}
