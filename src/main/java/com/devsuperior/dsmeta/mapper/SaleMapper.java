package com.devsuperior.dsmeta.mapper;

import org.springframework.stereotype.Component;

import com.devsuperior.dsmeta.dto.SaleSummaryDTO;

@Component
public class SaleMapper {
    public SaleSummaryDTO toSummaryDTO(Object[] row) {
        String sellerName = (String) row[0];
        Double total = row[1] == null ? 0.0 : ((Number) row[1]).doubleValue();
        return new SaleSummaryDTO(sellerName, total);
    }
}
