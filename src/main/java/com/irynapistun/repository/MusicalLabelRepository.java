package com.irynapistun.repository;

import com.irynapistun.domain.MusicalLabel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicalLabelRepository extends JpaRepository<MusicalLabel, Integer> {
}
