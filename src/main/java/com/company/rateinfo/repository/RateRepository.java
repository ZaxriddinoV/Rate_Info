package com.company.rateinfo.repository;

import com.company.rateinfo.domain.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<RateEntity, Long> {

}
