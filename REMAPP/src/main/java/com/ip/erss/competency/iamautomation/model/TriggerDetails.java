/**
 * 
 */
package com.ip.erss.competency.iamautomation.model;

/**
 * @author 134930
 *
 */
public class TriggerDetails {

	String triggerName;
	String triggerDescription;
	
	String jobKey;
	String jobName;
	String frequency;
	String lastExecutiontime;
	String nextFireTime;
	String triggerKey = null;
	
	/**
	 * @return the triggerName
	 */
	public String getTriggerName() {
		return triggerName;
	}
	/**
	 * @param triggerName the triggerName to set
	 */
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	/**
	 * @return the triggerDescription
	 */
	public String getTriggerDescription() {
		return triggerDescription;
	}
	/**
	 * @param triggerDescription the triggerDescription to set
	 */
	public void setTriggerDescription(String triggerDescription) {
		this.triggerDescription = triggerDescription;
	}
		/**
	 * @return the jobKey
	 */
	public String getJobKey() {
		return jobKey;
	}
	/**
	 * @param jobKey the jobKey to set
	 */
	public void setJobKey(String jobKey) {
		this.jobKey = jobKey;
	}
	/**
	 * @return the jobName
	 */
	public String getJobName() {
		return jobName;
	}
	/**
	 * @param jobName the jobName to set
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	/**
	 * @return the nextFireTime
	 */
	public String getNextFireTime() {
		return nextFireTime;
	}
	/**
	 * @param nextFireTime the nextFireTime to set
	 */
	public void setNextFireTime(String nextFireTime) {
		this.nextFireTime = nextFireTime;
	}
	/**
	 * @return the frequency
	 */
	public String getFrequency() {
		return frequency;
	}
	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	/**
	 * @return the lastExecutiontime
	 */
	public String getLastExecutiontime() {
		return lastExecutiontime;
	}
	/**
	 * @param lastExecutiontime the lastExecutiontime to set
	 */
	public void setLastExecutiontime(String lastExecutiontime) {
		this.lastExecutiontime = lastExecutiontime;
	}
	/**
	 * @return the triggerKey
	 */
	public String getTriggerKey() {
		return triggerKey;
	}
	/**
	 * @param triggerKey the triggerKey to set
	 */
	public void setTriggerKey(String triggerKey) {
		this.triggerKey = triggerKey;
	}
}
