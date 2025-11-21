package com.rebac.entity;

import com.rebac.enums.Visibility;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class User {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private Visibility privacyPolicy;

}
