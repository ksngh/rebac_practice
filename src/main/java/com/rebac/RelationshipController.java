package com.rebac;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RelationshipController {

    @PostMapping("/relationships")
    public ResponseEntity<Void> createRelationshipSpiceDB(
            @RequestParam String objectType,
            @RequestParam String objectId,
            @RequestParam String relation,
            @RequestParam String subjectType,
            @RequestParam String subjectId
    ) {
        // TODO: 요청 파라미터로 관계 튜플을 생성하고 SpiceDB에 write 한다.
        return ResponseEntity.noContent().build();
    }

}