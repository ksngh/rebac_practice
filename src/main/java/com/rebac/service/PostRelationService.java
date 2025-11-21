package com.rebac.service;

import com.rebac.entity.RelationTuple;
import com.rebac.enums.ObjectType;
import com.rebac.enums.RelationType;
import com.rebac.repository.RelationTupleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostRelationService {

    private final RelationTupleRepository relationTupleRepository;

    public void addRepost(Long newPostId, Long originalPostId) {
        relationTupleRepository.save(RelationTuple.of(
                ObjectType.POST, newPostId,
                RelationType.REPOST_OF,
                ObjectType.POST, originalPostId
        ));
    }

    public void addQuote(Long newPostId, Long originalPostId) {
        relationTupleRepository.save(RelationTuple.of(
                ObjectType.POST, newPostId,
                RelationType.QUOTE_OF,
                ObjectType.POST, originalPostId
        ));
    }

    public void addReply(Long newPostId, Long originalPostId) {
        relationTupleRepository.save(RelationTuple.of(
                ObjectType.POST, newPostId,
                RelationType.REPLY_TO,
                ObjectType.POST, originalPostId
        ));
    }
}