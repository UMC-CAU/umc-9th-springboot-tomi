package com.example.umc9th.domain.store.entity;

import com.example.umc9th.domain.mission.entity.Mission;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", length = 50,nullable=false)
    private String name;

    @Column(name="address", length = 50,nullable=false)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable=false)
    private Region region;

    @OneToMany(mappedBy = "store")
    private List<Mission> missions;
}
