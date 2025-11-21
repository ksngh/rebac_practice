package com.rebac.repository;

import com.rebac.entity.RelationTuple;
import com.rebac.enums.ObjectType;
import com.rebac.enums.RelationType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RelationTupleRepository extends JpaRepository<RelationTuple, Long> {

    List<RelationTuple> findByObjectTypeAndObjectIdAndRelationAndRevokedAtIsNull(
            ObjectType objectType, Long objectId, RelationType relation
    );

    List<RelationTuple> findBySubjectTypeAndSubjectIdAndRelationAndRevokedAtIsNull(
            ObjectType subjectType, Long subjectId, RelationType relation
    );

    Optional<RelationTuple> findByObjectTypeAndObjectIdAndRelationAndSubjectTypeAndSubjectIdAndRevokedAtIsNull(
            ObjectType objectType, Long objectId, RelationType relation,
            ObjectType subjectType, Long subjectId
    );

    @Query("""
        SELECT r FROM RelationTuple r
         WHERE r.objectType = :objectType
           AND r.objectId   = :objectId
           AND r.relation   = :relation
           AND r.revokedAt IS NULL
    """)
    List<RelationTuple> findActiveRelations(
            @Param("objectType") ObjectType objectType,
            @Param("objectId") Long objectId,
            @Param("relation") RelationType relation);
}
