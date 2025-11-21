package com.rebacService.service;

import com.rebac.entity.Post;
import com.rebac.entity.User;
import com.rebac.repository.PostRepository;
import com.rebac.repository.UserRepository;
import com.rebac.service.RebacService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final RebacService rebacService;

    public boolean canView(Long viewerId, Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("no post"));

        Long authorId = rebacService.findAuthor(postId);
        if (authorId == null) return false;

        // 1) block
        if (rebacService.isBlocked(viewerId, authorId)) {
            return false;
        }

        // 2) visibility
        User user = userRepository.findById(authorId).orElseThrow(RuntimeException::new);
        return switch (user.getPrivacyPolicy()) {

            case PUBLIC -> true;

            case PRIVATE -> viewerId.equals(authorId);

            case FOLLOWERS -> rebacService.isFollower(viewerId, authorId);

            case GROUP_ONLY -> {
                Long groupId = rebacService.findGroupForPost(postId);
                if (groupId == null) yield false;
                yield rebacService.isGroupMember(viewerId, groupId);
            }
        };
    }
}
