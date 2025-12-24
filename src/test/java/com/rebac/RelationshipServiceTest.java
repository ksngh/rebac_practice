package com.rebac;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RelationshipServiceTest {

    @Autowired
    private RelationshipService relationshipService;

    @Test
    @DisplayName("관계를 가진 사용자는 permission을 가진다")
    void check_permission_success() {
        //given
        String resourceType = "post";
        String resourceId = "post" + UUID.randomUUID();
        String relation = "author";
        String subjectType = "user";
        String subjectId = "Alice";
        String permission = "view";

        relationshipService.addRelationship(resourceType, resourceId, relation, subjectType, subjectId);

        // when
        boolean result =
                relationshipService.checkPermission(
                        resourceType, resourceId, permission, subjectType, subjectId
                );

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("관계가 없는 사용자는 permission을 가지지 않는다")
    void check_permission_failure() {
        //given
        String resourceType = "post";
        String resourceId = "post" + UUID.randomUUID();
        String relation = "author";
        String subjectType = "user";
        String subjectId = "Alice";
        String permission = "view";

        relationshipService.addRelationship(resourceType, resourceId, relation, subjectType, subjectId);

        // when
        boolean result =
                relationshipService.checkPermission(
                        resourceType, resourceId, permission, subjectType, "Bob"
                );

        //then
        assertThat(result).isFalse();
    }

}