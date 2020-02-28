package com.challenge.user.database.entitites;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "phone")
@Getter
@Setter
public class PhoneEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID userId;
    private String citycode;
    private String contrycode;
    private String number;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @Override
    public String toString() {
        return "PhoneEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", citycode='" + citycode + '\'' +
                ", contrycode='" + contrycode + '\'' +
                ", number='" + number + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                '}';
    }
}
