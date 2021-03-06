/*
    Copyright (c) 2015 Wolfgang Imig
    
    This file is part of the library "JOA Issue Tracker for Microsoft Outlook".

    This file must be used according to the terms of   
      
      MIT License, http://opensource.org/licenses/MIT

 */
package com.wilutions.itol.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Issue implements Serializable {

	private static final long serialVersionUID = 7470768205802774676L;
	
	public final static Issue NULL = new Issue();

	private String id;
	
	private String parentIssueId;
	
	private List<String> subIssueIds;
	
	private List<String> relatedIssueIds;
	
	/**
	 * Issue items in reverse order.
	 * The latest item is at index 0.
	 * The initial item is at end position.
	 */
	private List<IssueUpdate> updates;
	
	/**
	 * Initializes a NULL object.
	 */
	public Issue() {
		this.id = "";
		this.updates = new ArrayList<IssueUpdate>(1);
		this.updates.add(new IssueUpdate());
	}
	
	public Issue(String id, Issue rhs) {
		assert id != null && id.length() != 0;
		assert rhs != null;
		this.id = id;
		this.updates = rhs.getUpdates();
	}
	
	public Issue(String id, List<IssueUpdate> updates) {
		assert id != null && id.length() != 0;
		assert updates != null && updates.size() != 0;
		this.id = id;
		this.updates = updates;
	}
	
	public Issue(String id, IssueUpdate initialUpdate) {
		assert id != null && id.length() != 0;
		assert initialUpdate != null;
		this.id = id;
		this.updates = new ArrayList<IssueUpdate>(1);
		this.updates.add(initialUpdate);
	}
	
	@Override
	public Object clone() {
		Issue ret = new Issue();
		ret.id = this.id;
		ret.updates = new ArrayList<IssueUpdate>(this.updates.size());
		for (IssueUpdate upd : this.updates) {
			IssueUpdate updCopy = (IssueUpdate)upd.clone();
			ret.updates.add(updCopy);
		}
		return ret;
	}
	
	@Override
	public boolean equals(Object rhs) {
		boolean ret = false;
		if (rhs != null && rhs instanceof Issue) {
			Issue issue = (Issue)rhs;
			ret = this.id.equals(issue.id);
			if (ret) {
				ret = updates.equals(issue.updates);
			}
		}
		return ret;
	}
	
	public void findChangedMembers(Issue rhs, List<String> propIds) {
		if (rhs != null) {
			updates.get(0).findChangedMembers(rhs.updates.get(0), propIds);
		}
	}
	
	public boolean isNull() {
		return this.id.isEmpty();
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public String getParentIssueId() {
		if (parentIssueId == null) parentIssueId = "";
		return parentIssueId;
	}

	public void setParentIssueId(String parentIssueId) {
		this.parentIssueId = parentIssueId;
	}

	public List<String> getSubIssueIds() {
		if (subIssueIds == null) subIssueIds = new ArrayList<String>();
		return subIssueIds;
	}

	public List<String> getRelatedIssueIds() {
		if (relatedIssueIds == null) relatedIssueIds = new ArrayList<String>();
		return relatedIssueIds;
	}

	public List<IssueUpdate> getUpdates() {
		return updates;
	}

	public IssueUpdate getInitialUpdate() {
		return updates.get(updates.size()-1);
	}
	
	public IssueUpdate getLastUpdate() {
		return updates.get(0);
	}
	
	public Object getPropertyValue(String propertyId, Object defaultValue) {
		Object ret = getLastUpdate().getProperty(propertyId).getValue();
		if (ret == null) {
			ret = defaultValue;
			setPropertyValue(propertyId, ret);
		}
		return ret;
	}
	
	public void setPropertyValue(String propertyId, Object value) {
		if (value != null) {
			Property prop = new Property(propertyId, value);
			getLastUpdate().setProperty(prop);
		}
		else {
			getLastUpdate().removeProperty(propertyId);
		}
	}
	
	public String getPropertyString(String propertyId, String defaultValue) {
		return (String)getPropertyValue(propertyId, defaultValue);
	}
	
	public void setPropertyString(String propertyId, String value) {
		setPropertyValue(propertyId, value);
	}
	
	public Boolean getPropertyBoolean(String propertyId, Boolean defaultValue) {
		return (Boolean)getPropertyValue(propertyId, defaultValue);
	}
	
	public void setPropertyBoolean(String propertyId, Boolean value) {
		setPropertyValue(propertyId, value);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getPropertyStringList(String propertyId, List<String> defaultValue) {
		return (List<String>)getPropertyValue(propertyId, defaultValue);
	}
	
	public void setPropertyStringList(String propertyId, List<String> value) {
		setPropertyValue(propertyId, value);
	}
	
	public String getType() {
		return (String)getPropertyValue(Property.ISSUE_TYPE, "");
	}
	
	public void setType(String value) {
		setPropertyValue(Property.ISSUE_TYPE, value);
	}
	
	public String getAssignee() {
		return (String)getPropertyValue(Property.ASSIGNEE, "");
	}
	
	public void setAssignee(String value) {
		setPropertyValue(Property.ASSIGNEE, value);
	}
	
	public String getStatus() {
		return (String)getPropertyValue(Property.STATUS, "");
	}
	
	public void setStatus(String value) {
		setPropertyValue(Property.STATUS, value);
	}
	
	public String getProject() {
		return (String)getPropertyValue(Property.PROJECT, "");
	}
	
	public void setProject(String value) {
		setPropertyValue(Property.PROJECT, value);
	}
	
	public String getPriority() {
		return (String)getPropertyValue(Property.PRIORITY, "");
	}
	
	public void setPriority(String value) {
		setPropertyValue(Property.PRIORITY, value);
	}

	public String getSubject() {
		return (String)getPropertyValue(Property.SUBJECT, "");
	}

	public void setSubject(String value) {
		setPropertyValue(Property.SUBJECT, value);
	}
	
	public String getDescription() {
		return (String)getPropertyValue(Property.DESCRIPTION, "");
	}

	public void setDescription(String value) {
		setPropertyValue(Property.DESCRIPTION, value);
	}
	
	@SuppressWarnings("unchecked")
	public List<Attachment> getAttachments() {
		return (List<Attachment>)getPropertyValue(Property.ATTACHMENTS, new ArrayList<Attachment>(0));
	}
	
	public void setAttachments(List<Attachment> atts) {
		setPropertyValue(Property.ATTACHMENTS, atts);
	}
	

}
