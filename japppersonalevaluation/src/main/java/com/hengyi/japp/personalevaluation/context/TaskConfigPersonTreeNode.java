package com.hengyi.japp.personalevaluation.context;

import com.hengyi.japp.personalevaluation.domain.data.TaskConfigPerson;
import com.hengyi.japp.personalevaluation.domain.node.Operator;

public class TaskConfigPersonTreeNode extends TaskConfigPerson {
	private static final long serialVersionUID = 6524627989067563440L;
	protected Operator operator;
	protected Operator manager;

	public Operator getOperator() {
		return operator;
	}

	public Operator getManager() {
		return manager;
	}

	public void setOperator(Operator operator) {
		super.setOperator(operator);
		this.operator = operator;
	}

	public void setManager(Operator manager) {
		super.setManager(manager);
		this.manager = manager;
	}

	public TaskConfigPersonTreeNode() {
		super();
	}

	public TaskConfigPersonTreeNode(Operator operator) {
		super();
		setOperator(operator);
	}

	public TaskConfigPersonTreeNode(Long nodeId) {
		super(nodeId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(getOperator().getName());
		// if (isStatistic())
		// sb.append("统计；");
		// else
		// sb.append("不统计；");
		// if (isEvaluable())
		// sb.append("考核；");
		// else
		// sb.append("不考核；");
		// if (getLays() == LAYS_ALL)
		// sb.append("下属层级：全部");
		// else
		// sb.append("下属层级：" + getLays());
		return sb.toString();
	}
}
