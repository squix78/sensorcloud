package ch.squix.sensorcloud.model.sensor.actor;

public class Actor {
	
	private Long sensorId;
	private Double threshold;
	private ThresholdDirection direction;
	private Integer supportingValues;
	private String emailRecipient;
	private String messageSubject;
	private String messageBody;
	
	public Long getSensorId() {
		return sensorId;
	}
	public Double getThreshold() {
		return threshold;
	}

	public Integer getSupportingValues() {
		return supportingValues;
	}
	public String getEmailRecipient() {
		return emailRecipient;
	}
	public String getMessageSubject() {
		return messageSubject;
	}
	public String getMessageBody() {
		return messageBody;
	}
	public void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}
	public void setThreshold(Double threshold) {
		this.threshold = threshold;
	}

	public void setSupportingValues(Integer supportingValues) {
		this.supportingValues = supportingValues;
	}
	public void setEmailRecipient(String emailRecipient) {
		this.emailRecipient = emailRecipient;
	}
	public void setMessageSubject(String messageSubject) {
		this.messageSubject = messageSubject;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	/**
	 * @return the direction
	 */
	public ThresholdDirection getDirection() {
		return direction;
	}
	/**
	 * @param direction the direction to set
	 */
	public void setDirection(ThresholdDirection direction) {
		this.direction = direction;
	}

}
