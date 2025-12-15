package com.devsuperior.dsmeta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService saleService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = saleService.findById(id);
		return ResponseEntity.ok(dto);
	}

	 @GetMapping("/report")
	    public Page<SaleReportDTO> report(
	            @RequestParam(value = "minDate", required = false) String minDate,
	            @RequestParam(value = "maxDate", required = false) String maxDate,
	            @RequestParam(value = "name", required = false) String name,
	            @PageableDefault(size = 12) Pageable pageable) {

	        return saleService.getReport(minDate, maxDate, name, pageable);
	    }

	    @GetMapping("/summary")
	    public List<SaleSummaryDTO> summary(
	            @RequestParam(value = "minDate", required = false) String minDate,
	            @RequestParam(value = "maxDate", required = false) String maxDate) {

	        return saleService.getSummary(minDate, maxDate);
	    }



}
