package com.project.test.testwithasynctask.data;

import java.util.Date;

public class NotificationModel {
    
    public NotificationModel(String sub, String text, Date sDt, Date fDt){
        this.subject = sub;
        this.text = text;
        this.startDateTime = sDt;
        this.endDateTime = fDt;
    }
    
    private String id;
  
    private String subject;
  
    private String text;
    
    private Date startDateTime;
   
    private Date endDateTime;
    
    public String getSubject() {
        return subject;
    }
    
    public String getText() {
        return text;
    }
    
    public Date getStartDateTime() {
        return startDateTime;
    }
    
    public Date getEndDateTime() {
        return endDateTime;
    }
    
    public void setSubject(final String subject) {
        this.subject = subject;
    }
    
    public void setText(final String text) {
        this.text = text;
    }
    
    public void setStartDateTime(final Date startDateTime) {
        this.startDateTime = startDateTime;
    }
    
    public void setEndDateTime(final Date endDateTime) {
        this.endDateTime = endDateTime;
    }
}
