package com.platform.obour.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "sections")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "type")
    private String type; // "psychological" or "academic"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Section parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Section> children;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    private List<Question> questions;
}
