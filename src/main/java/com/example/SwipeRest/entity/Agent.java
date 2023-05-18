package com.example.SwipeRest.entity;

import com.example.SwipeRest.enums.TypeAgent;
import jakarta.persistence.*;
import lombok.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idagent")
    private int idAgent;

    private String name;

    private String surname;

    private String number;

    private String mail;

    @Enumerated(EnumType.STRING)
    private TypeAgent type;

    @OneToMany(mappedBy = "agent")
    private List<User> users = new ArrayList<>();

}
