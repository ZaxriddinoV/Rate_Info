package com.company.rateinfo.service;

import com.company.rateinfo.domain.RateEntity;
import org.aspectj.weaver.ast.Literal;

import java.util.List;

public interface RateService {

    List<RateEntity> getTodayRates();
}
