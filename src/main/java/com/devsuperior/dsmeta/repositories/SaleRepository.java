package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.dto.SaleReportDTO;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleReportDTO(s.id, s.date, s.amount, sel.name) " +
	        "FROM Sale s " +
	        "JOIN s.seller sel " +
	        "WHERE s.date BETWEEN :min AND :max " +
	        "AND LOWER(sel.name) LIKE LOWER(CONCAT('%', :namePart, '%')) " +
	        "ORDER BY s.date DESC, s.id DESC")
	    Page<SaleReportDTO> searchReport(@Param("min") LocalDate min,
	                                     @Param("max") LocalDate max,
	                                     @Param("namePart") String namePart,
	                                     Pageable pageable);

	
	 @Query(value =  "SELECT sel.name AS sellerName, SUM(s.amount) AS total " +
		        "FROM tb_sales s " +
		        "INNER JOIN tb_seller sel ON sel.id = s.seller_id " +
		        "WHERE s.date BETWEEN :min AND :max " +
		        "GROUP BY sel.name " +
		        "ORDER BY sel.name ", 
		        nativeQuery = true) 
		    List<Object[]> summarizeBySeller(@Param("min") LocalDate min,
		                                     @Param("max") LocalDate max);

	
}
