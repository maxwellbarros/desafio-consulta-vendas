package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.mapper.SaleMapper;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository saleRepository;
	private SaleMapper saleMapper;

	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = saleRepository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

    public SaleService(SaleRepository saleRepository, SaleMapper saleMapper) {
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
    }

    public Page<SaleReportDTO> getReport(String minDateStr, String maxDateStr, String name, Pageable pageable) {
        LocalDate max = parseOrToday(maxDateStr);
        LocalDate min = parseOrMinusOneYear(minDateStr, max);
        String namePart = (name == null) ? "" : name.trim();

        return saleRepository.searchReport(min, max, namePart, pageable);
    }

    public List<SaleSummaryDTO> getSummary(String minDateStr, String maxDateStr) {
        LocalDate max = parseOrToday(maxDateStr);
        LocalDate min = parseOrMinusOneYear(minDateStr, max);

        return saleRepository.summarizeBySeller(min, max)
                .stream()
                .map(saleMapper::toSummaryDTO)
                .toList();
    }

    private LocalDate parseOrToday(String dateStr) {
        if (dateStr == null || dateStr.isBlank()) {
            LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
            return today;
        }
        return LocalDate.parse(dateStr);
    }

    private LocalDate parseOrMinusOneYear(String dateStr, LocalDate referenceMax) {
        if (dateStr == null || dateStr.isBlank()) {
            return referenceMax.minusYears(1L);
        }
        return LocalDate.parse(dateStr);
    }

	
}
