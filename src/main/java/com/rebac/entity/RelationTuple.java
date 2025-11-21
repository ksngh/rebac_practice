package com.rebac.entity;

import com.rebac.enums.ObjectType;
import com.rebac.enums.RelationType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Table(name = "relation_tuples",
        indexes = {
                @Index(name = "idx_object",  columnList = "objectType,objectId,relation,revokedAt"),
                @Index(name = "idx_subject", columnList = "subjectType,subjectId,relation,revokedAt")
        })
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RelationTuple {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ObjectType objectType;

    private Long objectId;

    @Enumerated(EnumType.STRING)
    private RelationType relation;

    @Enumerated(EnumType.STRING)
    private ObjectType subjectType;

    private Long subjectId;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime revokedAt;

    public static RelationTuple of(
            ObjectType objectType, Long objectId,
            RelationType relation,
            ObjectType subjectType, Long subjectId
    ) {
        RelationTuple rt = new RelationTuple();
        rt.objectType = objectType;
        rt.objectId = objectId;
        rt.relation = relation;
        rt.subjectType = subjectType;
        rt.subjectId = subjectId;
        return rt;
    }

    public void revoke() {
        this.revokedAt = LocalDateTime.now();
    }

    public boolean isActive() {
        return revokedAt == null;
    }

}
