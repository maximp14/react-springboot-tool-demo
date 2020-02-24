package com.maxi.mtool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;

@Entity
public class ProjectTask {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(updatable = false)
  private String projectSequence;
  @NotBlank(message = "Include a project summary")
  private String summary;
  private String acceptanceCriteria;
  private String status;
  private Integer priority;
  private Date dueDate;
  private Date created_at;
  private Date update_at;
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
  @JoinColumn(name = "backlog_id", updatable = false, nullable = false)
  @JsonIgnore
  private Backlog backlog;

  @Column(updatable = false)
  private String projectIdentifier;

  public ProjectTask() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getProjectSequence() {
    return projectSequence;
  }

  public void setProjectSequence(String projectSequence) {
    this.projectSequence = projectSequence;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getAcceptanceCriteria() {
    return acceptanceCriteria;
  }

  public void setAcceptanceCriteria(String acceptanceCriteria) {
    this.acceptanceCriteria = acceptanceCriteria;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public Date getCreated_at() {
    return created_at;
  }

  public void setCreated_at(Date created_at) {
    this.created_at = created_at;
  }

  public Date getUpdate_at() {
    return update_at;
  }

  public void setUpdate_at(Date update_at) {
    this.update_at = update_at;
  }

  public String getProjectIdentifier() {
    return projectIdentifier;
  }

  public void setProjectIdentifier(String projectIdentifier) {
    this.projectIdentifier = projectIdentifier;
  }

  public Backlog getBacklog() {
    return backlog;
  }

  public void setBacklog(Backlog backlog) {
    this.backlog = backlog;
  }

  @PrePersist
  protected void onCreate(){
    this.created_at = new Date();
  }

  @PreUpdate
  protected void onUpdate(){
    this.update_at = new Date();
  }


  @Override
  public String toString() {
    return "ProjectTask{" +
        "id=" + id +
        ", projectSequence='" + projectSequence + '\'' +
        ", summary='" + summary + '\'' +
        ", acceptanceCriteria='" + acceptanceCriteria + '\'' +
        ", status='" + status + '\'' +
        ", priority=" + priority +
        ", dueDate=" + dueDate +
        ", created_at=" + created_at +
        ", update_at=" + update_at +
        ", projectIdentifier='" + projectIdentifier + '\'' +
        '}';
  }
}
