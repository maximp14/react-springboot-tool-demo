package com.maxi.mtool.repository;

import com.maxi.mtool.domain.Backlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog,Long> {

  Backlog findByProjectIdentifier(String identifier);
}
