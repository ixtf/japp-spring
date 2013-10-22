package org.hengyi.japp.personalevaluation;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.personalevaluation.domain.node.Operator;
import com.hengyi.japp.personalevaluation.service.EvaluationService;
import com.hengyi.japp.personalevaluation.service.OperatorService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
@Transactional
public class EvaluateTest {
	@Inject
	OperatorService operatorService;
	@Inject
	EvaluationService evaluationService;

	@Test
	public void test() throws Exception {
		//Operator operator = operatorService.findByEmpsn("1200077");
		//System.out.print(operator);
	}
}
