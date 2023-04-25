package Models;

import java.util.Date;

public class bookIssue {
	private String book_id ;
	private String member_id ;
	private Date issue_date ;
	private Date due_date ;
	private Date return_date ;
	
	public bookIssue(String book_id, String member_id, Date issue_date, Date due_date, Date return_date) {
		super();
		this.book_id = book_id;
		this.member_id = member_id;
		this.issue_date = issue_date;
		this.due_date = due_date;
		this.return_date = return_date;
	}
	public bookIssue(String book_id, String member_id, Date issue_date, Date due_date) {
		super();
		this.book_id = book_id;
		this.member_id = member_id;
		this.issue_date = issue_date;
		this.due_date = due_date;
		this.return_date = null;
	}

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public Date getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}

	public Date getDue_date() {
		return due_date;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}

	public Date getReturn_date() {
		return return_date;
	}

	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}

	@Override
	public String toString() {
		return "bookIssue [book_id=" + book_id + ", member_id=" + member_id + ", issue_date=" + issue_date
				+ ", due_date=" + due_date + ", return_date=" + return_date + "]";
	}
	
	
	
	

}
