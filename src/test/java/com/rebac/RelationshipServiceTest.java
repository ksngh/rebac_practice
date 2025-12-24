package com.rebac;

import com.authzed.api.v1.WriteRelationshipsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RelationshipServiceTest {

    @Autowired
    private RelationshipService relationshipService;

    @Test
    @DisplayName("관계 튜플을 SpiceDB에 write 할 수 있다")
    void create_relationship_success() {
        //given
        String resourceType = "post";
        String resourceId = "post-1";
        String relation = "author";
        String subjectType = "user";
        String subjectId = "Alice";

        // when
        WriteRelationshipsResponse response =
                relationshipService.addRelationship(
                        resourceType, resourceId, relation, subjectType, subjectId
                );

        // then
        System.out.println("=== Write Result ===");
        System.out.println(response);

        assertThat(response).isNotNull();
    }
}