package org.digitinary.traninng.librarymanagmentsystem.util;

import org.digitinary.traninng.librarymanagmentsystem.entity.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    public static Specification<User> hasEmailLike(String emailType) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("email"), "%@"+emailType+".com");
    }
    public static Specification<User> hasNameLike(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), name);
    }
    public static Specification<User> hasPhoneLike(String code) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("phone"), code);
    }
}
