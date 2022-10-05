package com.example.todo_list.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "todos")
public class Todo {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private long id;

		private String userName;

	    private String description;

	    private Date targetDate;

		private Date creationDate;

		private String link;

	    public Todo() {
	        super();
	    }

	public Todo(String user, String desc, Date targetDate, boolean isDone) {
	        super();
	        this.setUserName(user);
	        this.setDescription(desc);
	        this.setTargetDate(targetDate);
	    }

	public Todo(String user, String desc, Date targetDate, boolean isDone, String link) {
		super();
		this.setUserName(user);
		this.setDescription(desc);
		this.setTargetDate(targetDate);
		this.setLink(link);
	}


	public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Date getTargetDate() {
			return targetDate;
		}

		public void setTargetDate(Date targetDate) {
			this.targetDate = targetDate;
		}

		public Date getCreationDate() {
			return creationDate;
		}
		public void setCreationDate(Date creationDate) {
				this.creationDate = creationDate;
			}
		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}


}
