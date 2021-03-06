package com.nia.services.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="QUESTION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Question {
	
	
	
	public Question() {
		super();
	}
	

	public Question(Long id) {
		super();
		this.id = id;
	}

	@Id
	@Column(name = "question_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="question_desc")
	private String questionDesc;
	
	@OneToMany(
			cascade = CascadeType.ALL, 
			orphanRemoval = true,
			mappedBy="question"
    )
	@OrderBy("id asc")
	@JsonManagedReference
	private Set<QuestionOption> options = new HashSet<>();
	
	@Column(name="is_active")
	private boolean isActive;
	
	@Column(name="multiple_ans")
	private boolean multipleAns;
	
	@ManyToOne(fetch = FetchType.LAZY,
			cascade = CascadeType.ALL)
    @JoinColumn(name = "exam_id")
	@JsonBackReference
    private Exam exam;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestionDesc() {
		return questionDesc;
	}

	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}

	public Set<QuestionOption> getOptions() {
		return options;
	}

	public void setOptions(Set<QuestionOption> options) {
		this.options = options;
	}
	
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/*@Override
	public String toString() {
		return "Question [id=" + id + ", questionDesc=" + questionDesc + ", options=" + options + ", getId()=" + getId()
				+ ", getQuestionDesc()=" + getQuestionDesc() + ", getOptions()=" + getOptions() + "]";
	}*/

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	@Transient
	private String resultDesc; 

	@Transient
	private boolean correctAnswered = false;

	@Transient
	private boolean answered = false;
	
	public void setResultDesc(String value) {
		this.resultDesc = value;
	}

	public boolean isCorrectAnswered() {
		return correctAnswered;
	}

	public void setCorrectAnswered(boolean correctAnswered) {
		this.correctAnswered = correctAnswered;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public boolean isAnswered() {
		return answered;
	}

	public void setAnswered(boolean answered) {
		this.answered = answered;
	}

	public boolean isMultipleAns() {
		return multipleAns;
	}

	public void setMultipleAns(boolean multipleAns) {
		this.multipleAns = multipleAns;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
