package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CurveTest {

	@Autowired
	private CurvePointRepository curvePointRepository;

	@Test
	public void curvePointTest() {

		CurvePoint curvePointList = new CurvePoint();
		curvePointList.setCurveId(10);
		curvePointList.setTerm(1.0);
		curvePointList.setValue(100.0);
		curvePointList.setCreationDate(LocalDateTime.now()); // Conversion
		curvePointList.setAsOfDate(LocalDateTime.now());

		// Save
		curvePointList = curvePointRepository.save(curvePointList);
		assertNotNull(curvePointList.getId());
		assertEquals(10, curvePointList.getCurveId());

		// Update
		curvePointList.setCurveId(20);
		curvePointList = curvePointRepository.save(curvePointList);
		assertEquals(20, curvePointList.getCurveId());

		// FindAll
		List<CurvePoint> listResult = curvePointRepository.findAll();
		assertFalse(listResult.isEmpty());

		// Delete
		Integer id = curvePointList.getId();
		curvePointRepository.delete(curvePointList);
		Optional<CurvePoint> deleted = curvePointRepository.findById(id);
		assertFalse(deleted.isPresent());
	}

}
