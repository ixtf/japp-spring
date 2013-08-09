package com.hengyi.japp.personalevaluation.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.personalevaluation.context.EvaluationContext;
import com.hengyi.japp.personalevaluation.domain.node.Person;
import com.hengyi.japp.personalevaluation.domain.node.Task;
import com.hengyi.japp.personalevaluation.domain.relationship.LevelEvaluation;

@Named
@Scope("request")
public class HomeController extends AbstractController {
	private static final long serialVersionUID = 3708518912737819900L;
	private EvaluationContext evaluationContext;
	private Person person;

	public Task getTask() {
		return evaluationContext.getTask();
	}

	public List<Person> getToEvaluatePersons() {
		return evaluationContext.getToEvaluatePersons();
	}

	public List<LevelEvaluation> getEvaluatedPersons() {
		return evaluationContext.getEvaluatedPersons();
	}

	public List<Person> getUnSubmitPersons() {
		return evaluationContext.getUnSubmitPersons();
	}

	public void evaluationPerson() {
		redirect("/evaluation/persons/" + getPerson().getNodeId());
	}

	public void submit() {
		try {
			evaluationContext.submit();
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public boolean isCanSubmit() {
		return evaluationContext.canSubmit();
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@PostConstruct
	public void home() {
		try {
			evaluationContext = contextFactory.evaluationContext();
		} catch (Exception e) {
			addErrorMessage(e);
			redirect("taskChange");
		}
	}
}
