/*
 * package com.safesmart.safesmart.model;
 * 
 * //import java.time.LocalDate; import java.time.LocalDateTime; import
 * java.util.List;
 * 
 * import javax.persistence.CascadeType; import javax.persistence.Entity; import
 * javax.persistence.Enumerated; import javax.persistence.GeneratedValue; import
 * javax.persistence.GenerationType; import javax.persistence.Id; import
 * javax.persistence.ManyToOne; import javax.persistence.OneToMany;
 * 
 * @Entity public class ChangeRequestOrder {
 * 
 * private Long id;
 * 
 * private List<ChangeRequestOld> changerequests;
 * 
 * private OrderStatus orderStatus;
 * 
 * private UserInfo createdBy;
 * 
 * private UserInfo deliveredBy;
 * 
 * // private LocalDate createdOn; // // private LocalDate updatedon;
 * 
 * 
 * private LocalDateTime createdTime;
 * 
 * 
 * private LocalDateTime updatedTime;
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.AUTO) public Long getId() { return
 * id; }
 * 
 * public void setId(Long id) { this.id = id; }
 * 
 * @OneToMany(cascade = CascadeType.ALL,mappedBy = "changeRequestOrder") public
 * List<ChangeRequestOld> getChangerequests() { return changerequests; }
 * 
 * public void setChangerequests(List<ChangeRequestOld> changerequests) {
 * this.changerequests = changerequests; }
 * 
 * @Enumerated public OrderStatus getOrderStatus() { return orderStatus; }
 * 
 * public void setOrderStatus(OrderStatus orderStatus) { this.orderStatus =
 * orderStatus; }
 * 
 * @ManyToOne public UserInfo getCreatedBy() { return createdBy; }
 * 
 * public void setCreatedBy(UserInfo createdBy) { this.createdBy = createdBy; }
 * 
 * @ManyToOne public UserInfo getDeliveredBy() { return deliveredBy; }
 * 
 * public void setDeliveredBy(UserInfo deliveredBy) { this.deliveredBy =
 * deliveredBy; }
 * 
 * // public LocalDate getCreatedOn() { // return createdOn; // } // // public
 * void setCreatedOn(LocalDate createdOn) { // this.createdOn = createdOn; // }
 * 
 * public LocalDateTime getCreatedTime() { return createdTime; }
 * 
 * public void setCreatedTime(LocalDateTime createdTime) { this.createdTime =
 * createdTime; }
 * 
 * // public LocalDate getUpdatedon() { // return updatedon; // } // // public
 * void setUpdatedon(LocalDate updatedon) { // this.updatedon = updatedon; // }
 * 
 * public LocalDateTime getUpdatedTime() { return updatedTime; }
 * 
 * public void setUpdatedTime(LocalDateTime updatedTime) { this.updatedTime =
 * updatedTime; }
 * 
 * }
 */