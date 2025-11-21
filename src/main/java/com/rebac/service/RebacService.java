package com.rebac.service;

import com.rebac.entity.RelationTuple;
import com.rebac.enums.ObjectType;
import com.rebac.enums.RelationType;
import com.rebac.repository.RelationTupleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RebacService {

    private final RelationTupleRepository relationRepo;

    /** viewer 가 author 를 block 또는 inverse block 했는가? **/
    public boolean isBlocked(Long viewerId, Long authorId) {

        // viewer → author block
        boolean viewerBlocksAuthor = relationRepo
                .findByObjectTypeAndObjectIdAndRelationAndSubjectTypeAndSubjectIdAndRevokedAtIsNull(
                        ObjectType.USER, authorId, RelationType.BLOCK,
                        ObjectType.USER, viewerId
                ).isPresent();

        // author → viewer block
        boolean authorBlocksViewer = relationRepo
                .findByObjectTypeAndObjectIdAndRelationAndSubjectTypeAndSubjectIdAndRevokedAtIsNull(
                        ObjectType.USER, viewerId, RelationType.BLOCK,
                        ObjectType.USER, authorId
                ).isPresent();

        return viewerBlocksAuthor || authorBlocksViewer;
    }

    public boolean isFollower(Long viewerId, Long authorId) {
        return relationRepo
                .findByObjectTypeAndObjectIdAndRelationAndSubjectTypeAndSubjectIdAndRevokedAtIsNull(
                        ObjectType.USER, authorId, RelationType.FOLLOW,
                        ObjectType.USER, viewerId
                ).isPresent();
    }

    public boolean isGroupMember(Long viewerId, Long groupId) {
        return relationRepo
                .findByObjectTypeAndObjectIdAndRelationAndSubjectTypeAndSubjectIdAndRevokedAtIsNull(
                        ObjectType.GROUP, groupId, RelationType.MEMBER,
                        ObjectType.USER, viewerId
                ).isPresent();
    }

    public Long findGroupForPost(Long postId) {
        return relationRepo
                .findByObjectTypeAndObjectIdAndRelationAndRevokedAtIsNull(
                        ObjectType.GROUP, postId, RelationType.GROUP_HAS_POST
                ).stream()
                .findFirst()
                .map(RelationTuple::getObjectId)
                .orElse(null);
    }

    public Long findAuthor(Long postId) {
        return relationRepo
                .findByObjectTypeAndObjectIdAndRelationAndRevokedAtIsNull(
                        ObjectType.POST, postId, RelationType.AUTHOR
                ).stream()
                .findFirst()
                .map(RelationTuple::getSubjectId)
                .orElse(null);
    }

}
