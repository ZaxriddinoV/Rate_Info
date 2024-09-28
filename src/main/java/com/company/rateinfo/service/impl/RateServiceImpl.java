package com.company.rateinfo.service.impl;

import com.company.rateinfo.constants.ApplicationProperty;
import com.company.rateinfo.domain.RateEntity;
import com.company.rateinfo.dto.RateDTO;
import com.company.rateinfo.helper.Requester;
import com.company.rateinfo.repository.RateRepository;
import com.company.rateinfo.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {

    private final Requester requester;
    private final RateRepository rateRepository;

    @Override
    public List<RateEntity> getTodayRates() {

        List<RateEntity> visibleRates;

        URI uri = UriComponentsBuilder.fromHttpUrl(ApplicationProperty.EXCHANGE_RATES.getValue()).build().toUri();

        List<RateDTO> list = requester.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        List<RateEntity> newRates = list.stream().map(RateDTO::map2Entity).toList();

        List<RateEntity> oldRates = rateRepository.findAll();

        if (oldRates.isEmpty()) {
            return rateRepository.saveAll(newRates);
        } else {
            Map<Long, RateEntity> newRatesMap = newRates.stream()
                    .collect(Collectors.toMap(RateEntity::getApiId, rate -> rate));
            List<RateEntity> saveRates = oldRates.stream().map(oldRate -> {
                RateEntity newRate = newRatesMap.get(oldRate.getApiId());
                if (newRate != null) {
                    newRate.setId(oldRate.getId());
                    return newRate;
                }
                return oldRate;
            }).toList();

            return rateRepository.saveAll(saveRates);
        }

    }
}
