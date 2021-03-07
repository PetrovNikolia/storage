package com.petrov.storage.Persist.Repo;

import com.petrov.storage.Persist.Entity.File;
import org.springframework.data.jpa.domain.Specification;

public class FileSpecification {

    public static Specification<File> trueLiteral() {

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(true));

    }

    public static Specification<File> nameLike(String name){
        return (root, criteriaQuery, criteriaBuilder)-> criteriaBuilder.like(root.get("name"),"%" + name + "%");
    }

    public static Specification<File> timeGreaterOrEqualsThan(String low) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("time"), low);
    }

    public static Specification<File> timeLesserOrEqualsThan(String up) {
        return  (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("time"), up);
    }


}
