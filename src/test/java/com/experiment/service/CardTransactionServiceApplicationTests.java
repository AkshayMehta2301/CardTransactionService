package com.experiment.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CardTransactionRepositoryConfig.class)
class CardTransactionServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
