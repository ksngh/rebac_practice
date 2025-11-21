package com.rebac.enums;

public enum RelationType {
    // user-user
    FOLLOW,
    FOLLOW_PENDING,
    BLOCK,

    // post-post
    REPOST_OF,
    QUOTE_OF,
    REPLY_TO,

    // post-user
    AUTHOR,

    // group-user
    MEMBER,
    ADMIN,

    // group-post
    GROUP_HAS_POST
}
