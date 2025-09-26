package com.charis.api.e_commerce.common.service;

import com.charis.api.e_commerce.common.dtos.KeySetPaginationResponseDto;
import com.charis.api.e_commerce.common.model.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;

@Service
@RequiredArgsConstructor
public class KeySetPaginator {

    private final EntityManager entityManager;

    public <T extends BaseEntity> KeySetPaginationResponseDto<T> paginate(
            Class<T> entityClass,
            String sortBy,
            Sort.Direction direction,
            UUID cursor,
            int limit,
            BiFunction<Root<T>, CriteriaBuilder, List<Predicate>> extraPredicates
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);


        Path<UUID> sortColumn = root.get(sortBy);

        Predicate condition = cb.conjunction();
        if (cursor != null) {
            condition = (direction == Sort.Direction.DESC)
                    ? cb.lessThan(sortColumn, cursor)
                    : cb.greaterThan(sortColumn, cursor);
        }

        if (extraPredicates != null) {
            List<Predicate> predicates = extraPredicates.apply(root, cb);
            if (predicates != null && !predicates.isEmpty()) {
                Predicate[] arr = predicates.toArray(new Predicate[0]);
                condition = cb.and(condition, cb.and(arr));
            }
        }


        if (condition != null) {
            cq.where(condition);
        }

        cq.orderBy(direction == Sort.Direction.DESC ? cb.desc(sortColumn) : cb.asc(sortColumn));

        TypedQuery<T> query = entityManager.createQuery(cq);
        query.setMaxResults(limit + 1);

        List<T> results = query.getResultList();

        boolean hasNext = results.size() > limit;
        if (hasNext) {
            results = results.subList(0, limit);
        }

        UUID nextCursor = results.isEmpty()
                ? null
                : results.get(results.size() - 1).getId();

        return new KeySetPaginationResponseDto<T>(results, nextCursor, hasNext);
    }

    public <T extends BaseEntity> KeySetPaginationResponseDto<T> paginate(
            Class<T> entityClass,
            String sortBy,
            Sort.Direction direction,
            UUID cursor,
            int limit
    ) {
        return paginate(entityClass, sortBy, direction, cursor, limit, null);
    }
}

