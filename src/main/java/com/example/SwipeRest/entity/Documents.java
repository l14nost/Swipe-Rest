package com.example.SwipeRest.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.Builder;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
public class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddocuments")
    private int idDocuments;

    private String name;

    private String fileName;

    @ManyToOne
    @JoinColumn(name = "id_lcd")
    private LCD lcd;

}
