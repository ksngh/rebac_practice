package com.rebac.service;

import com.rebac.entity.RelationTuple;
import com.rebac.enums.ObjectType;
import com.rebac.enums.RelationType;
import com.rebac.repository.RelationTupleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRelationService {

    private final RelationTupleRepository relationRepo;

    // follow 요청
    public void requestFollow(Long requesterId, Long targetId) {
        relationRepo.save(RelationTuple.of(
                ObjectType.USER, targetId,
                RelationType.FOLLOW_PENDING,
                ObjectType.USER, requesterId
        ));
    }

    // follow 승인
    public void acceptFollow(Long viewerId, Long requesterId) {
        // revoke pending
        relationRepo.findByObjectTypeAndObjectIdAndRelationAndSubjectTypeAndSubjectIdAndRevokedAtIsNull(
                ObjectType.USER, viewerId, RelationType.FOLLOW_PENDING,
                ObjectType.USER, requesterId
        ).ifPresent(RelationTuple::revoke);

        // follow 추가
        relationRepo.save(RelationTuple.of(
                ObjectType.USER, viewerId,
                RelationType.FOLLOW,
                ObjectType.USER, requesterId
        ));
    }

    // block
    public void block(Long blockerId, Long targetId) {
        relationRepo.save(RelationTuple.of(
                ObjectType.USER, targetId,
                RelationType.BLOCK,
                ObjectType.USER, blockerId
        ));
    }
}
