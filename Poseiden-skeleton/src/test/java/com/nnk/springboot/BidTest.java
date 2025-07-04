package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BidTest {

	@Autowired
	private BidListRepository bidListRepository;

	@Test
	public void bidListTest() {
		BidList bid = new BidList();
		bid.setAccount("Account Test");
		bid.setType("Type Test");
		bid.setBidQuantity(10.0);
		bid.setAskQuantity(5.0);
		bid.setBid(9.5);
		bid.setAsk(5.5);

		// Save
		bid = bidListRepository.save(bid);
		assertNotNull(bid.getId());
		assertEquals(10, bid.getBidQuantity(), 10);

		// Update
		bid.setBidQuantity(20.0);
		bid = bidListRepository.save(bid);
		assertEquals(20.0, bid.getBidQuantity(), 20.0);

		// Find
		List<BidList> listResult = bidListRepository.findAll();
        assertFalse(listResult.isEmpty());

		// Delete
		Integer id = bid.getId();
		bidListRepository.delete(bid);
		Optional<BidList> bidList = bidListRepository.findById(id);
		assertFalse(bidList.isPresent());
	}
}
