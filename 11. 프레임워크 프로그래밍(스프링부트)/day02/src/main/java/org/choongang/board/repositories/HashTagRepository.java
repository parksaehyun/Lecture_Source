package org.choongang.board.repositories;

import org.choongang.board.entities.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface HashTagRepository extends JpaRepository<HashTag, String>, QuerydslPredicateExecutor<HashTag> {
}
