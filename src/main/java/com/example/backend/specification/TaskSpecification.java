package com.example.backend.specification;

import org.springframework.data.jpa.domain.Specification;

import com.example.backend.entity.Task;
import com.example.backend.enums.Priority;

public class TaskSpecification {
	
	private TaskSpecification() {
    }

    public static Specification<Task> hasCompleted(Boolean completed) {

        return (root, query, cb) ->
                completed == null
                        ? null
                        : cb.equal(root.get("completed"), completed);
    }

    public static Specification<Task> hasPriority(Priority priority) {

        return (root, query, cb) ->
                priority == null
                        ? null
                        : cb.equal(root.get("priority"), priority);
    }
    
    public static Specification<Task> hasTag(String tagName) {

        return (root, query, cb) -> {

            if (tagName == null || tagName.isBlank()) {
                return null;
            }

            query.distinct(true);

            return cb.equal(
                    root.join("tags").get("name"),
                    tagName
            );
        };
    }

}
