package websocket.component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author prinswu
 * @version v1.0
 * @since v1.0 2018/11/26
 */
@Entity
public class CrMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String status;
	private String crid;
	private String sender;
	private String sendTime;
	private String message;
	private String crn;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCrid() {
		return crid;
	}

	public void setCrid(String crid) {
		this.crid = crid;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCrn() {
		return crn;
	}

	public void setCrn(String crn) {
		this.crn = crn;
	}

}
