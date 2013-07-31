package com.hengyi.japp.personalevaluation.service.impl;

import static com.hengyi.japp.personalevaluation.Constant.SESSION_TASK;

import java.util.Set;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import com.hengyi.japp.personalevaluation.Constant.ErrorCode;
import com.hengyi.japp.personalevaluation.domain.data.Level;
import com.hengyi.japp.personalevaluation.domain.data.PersonStatus;
import com.hengyi.japp.personalevaluation.domain.data.TaskConfigPerson;
import com.hengyi.japp.personalevaluation.domain.node.Operator;
import com.hengyi.japp.personalevaluation.domain.node.Person;
import com.hengyi.japp.personalevaluation.domain.node.SubmitLimit;
import com.hengyi.japp.personalevaluation.domain.node.Task;
import com.hengyi.japp.personalevaluation.domain.node.TaskConfig;
import com.hengyi.japp.personalevaluation.domain.relationship.KpiEvaluation;
import com.hengyi.japp.personalevaluation.domain.relationship.LevelEvaluation;
import com.hengyi.japp.personalevaluation.domain.repository.KpiRepository;
import com.hengyi.japp.personalevaluation.domain.repository.PersonRepository;
import com.hengyi.japp.personalevaluation.domain.repository.TaskRepository;
import com.hengyi.japp.personalevaluation.service.CacheServiceFacade;
import com.hengyi.japp.personalevaluation.service.EvaluationService;

@Named
@Transactional
// @Veto
public class EvaluationServiceImpl implements EvaluationService {
	@Resource(name = "jappCommonSoapClient")
	private com.hengyi.japp.common.ws.SoapService jappCommonSoapClient;
	@Inject
	private CacheServiceFacade cacheService;
	@Inject
	private Neo4jOperations template;
	@Inject
	private PersonRepository personRepository;
	@Inject
	private KpiRepository kpiRepository;
	@Inject
	private TaskRepository taskRepository;

	@Override
	public Person findOnePerson(Long nodeId) throws Exception {
		return personRepository.findOne(nodeId);
	}

	@Override
	public Person findOnePerson(Task task, Operator operator) {
		return personRepository.findOne(task, operator);
	}

	@Override
	public void evaluate(Person personStart, Person personEnd,
			LevelEvaluation levelEvaluation,
			Iterable<KpiEvaluation> kpiEvaluations) throws Exception {
		checkEvaluation(personEnd.getNodeId());
		if (personStart.equals(personEnd)) {
			Person person = personRepository.findOne(personEnd.getNodeId());
			person.setWork(personEnd.getWork());
			personEnd = personRepository.save(personEnd);
		}
		personStart.evaluateLevel(template, personEnd,
				levelEvaluation.getLevel(), levelEvaluation.getSummary());
		for (KpiEvaluation kpiEvaluation : kpiEvaluations)
			personStart.evaluateKpi(template, kpiEvaluation.getKpi(),
					kpiEvaluation.getScore());
	}

	@Override
	public void checkEvaluation(Long personNodeId) throws Exception {
		Task task = cacheService.getCurrentTask();
		Person personStart = findOnePerson(task,
				cacheService.getCurrentOperator());
		Person personEnd = personRepository.findOne(personNodeId);
		if (!task.isActive())
			throw new Exception(ErrorCode.ERROR_NOT_ACTIVE);
		if (!task.equals(personEnd.getTask(template))
				|| !task.equals(personStart.getTask(template)))
			throw new Exception(ErrorCode.SYSTEM);
		if (personStart.isSubmit())
			throw new Exception(ErrorCode.ERROR_ALREADY_SUBMIT
					+ personStart.getName());
	}

	@Override
	public void submit() throws Exception {
		checkSubmit();
		Person self = findOnePerson(cacheService.getCurrentTask(),
				cacheService.getCurrentOperator());
		self.setStatus(PersonStatus.SUBMIT);
		personRepository.save(self);
	}

	private void checkSubmit() throws Exception {
		Task task = cacheService.getSession(SESSION_TASK, Task.class);
		if (!task.isActive())
			throw new Exception(ErrorCode.ERROR_NOT_ACTIVE);
		Person self = findOnePerson(cacheService.getCurrentTask(),
				cacheService.getCurrentOperator());
		if (self.isSubmit())
			throw new Exception(ErrorCode.ERROR_ALREADY_SUBMIT);
		if (self.isEvaluable() && StringUtils.isBlank(self.getWork()))
			throw new Exception(ErrorCode.ERROR_WORK_BLANK);
		// 检查提交的限制
		Set<SubmitLimit> submitLimits = Sets.newHashSet();
		TaskConfig taskConfig = task.getConfig(template);
		for (TaskConfigPerson taskConfigPerson : taskConfig.getTaskPersons()) {
			if (self.getOperator().getNodeId()
					.equals(taskConfigPerson.getNodeId())) {
				submitLimits.addAll(taskConfigPerson.getSubmitLimits());
				break;
			}
		}
		if (submitLimits.isEmpty())
			return;
		Multiset<Level> set = HashMultiset.<Level> create();
		for (LevelEvaluation levelEvaluation : template.fetch(self
				.getEvaluations())) {
			Person person = levelEvaluation.getPersonEnd(template);
			if (!person.isEvaluable() || !person.isStatistic())
				continue;
			set.add(levelEvaluation.getLevel());
		}

		for (SubmitLimit submitLimit : submitLimits)
			if (!checkSubmit(set, submitLimit))
				throw new Exception(ErrorCode.ERROR_SUBMITLIMIT
						+ submitLimit.getName());
	}

	private boolean checkSubmit(Multiset<Level> set, SubmitLimit submitLimit)
			throws Exception {
		String cal1 = StringUtils.upperCase(submitLimit.getCalaculate1());
		String cal2 = StringUtils.upperCase(submitLimit.getCalaculate2());
		for (Level level : Level.values()) {
			Number n = set.count(level);
			cal1 = StringUtils.replace(cal1, level.name(), n.toString());
			cal2 = StringUtils.replace(cal2, level.name(), n.toString());
		}
		Double number1 = jappCommonSoapClient.calString(cal1);
		Double number2 = jappCommonSoapClient.calString(cal2);
		switch (submitLimit.getCompare()) {
		case GT:
			return number1.compareTo(number2) > 0 ? submitLimit.isSubmit()
					: !submitLimit.isSubmit();
		case GTOREG:
			return number1.compareTo(number2) >= 0 ? submitLimit.isSubmit()
					: !submitLimit.isSubmit();
		case EQ:
			return number1.compareTo(number2) == 0 ? submitLimit.isSubmit()
					: !submitLimit.isSubmit();
		default:
			return false;
		}
	}
}
