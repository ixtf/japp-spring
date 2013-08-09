package com.hengyi.japp.common.exception;

public class JappEventException extends RuntimeException {
	private static final long serialVersionUID = 454288258290822002L;
	private Object event;

	public JappEventException(Object event) {
		this.event = event;
	}

	public JappEventException(Object event, String message) {
		super(message);
		this.event = event;
	}

	public JappEventException(Object event, String message, Throwable cause) {
		super(message, cause);
		this.event = event;
	}

	public JappEventException(Object event, Throwable cause) {
		super(cause);
		this.event = event;
	}

	public Object getEvent() {
		return event;
	}

	public void setEvent(Object event) {
		this.event = event;
	}

	@Override
	public String getLocalizedMessage() {
		// TODO Auto-generated method stub
		return super.getLocalizedMessage();
	}
}
