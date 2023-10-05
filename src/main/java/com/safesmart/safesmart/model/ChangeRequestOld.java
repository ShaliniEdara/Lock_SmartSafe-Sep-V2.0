/*
 * package com.safesmart.safesmart.model;
 * 
 * import javax.persistence.Entity; import javax.persistence.GeneratedValue;
 * import javax.persistence.GenerationType; import javax.persistence.Id; import
 * javax.persistence.ManyToOne;
 * 
 * @Entity public class ChangeRequestOld {
 * 
 * private Long id;
 * 
 * private String coin;
 * 
 * private int actualCount;
 * 
 * private int deliveredCount;
 * 
 * private ChangeRequestOrder changeRequestOrder;
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.AUTO) public Long getId() { return
 * id; }
 * 
 * public void setId(Long id) { this.id = id; }
 * 
 * public String getCoin() { return coin; }
 * 
 * public void setCoin(String coin) { this.coin = coin; }
 * 
 * public int getActualCount() { return actualCount; }
 * 
 * public void setActualCount(int actualCount) { this.actualCount = actualCount;
 * }
 * 
 * public int getDeliveredCount() { return deliveredCount; }
 * 
 * public void setDeliveredCount(int deliveredCount) { this.deliveredCount =
 * deliveredCount; }
 * 
 * @ManyToOne public ChangeRequestOrder getChangeRequestOrder() { return
 * changeRequestOrder; }
 * 
 * public void setChangeRequestOrder(ChangeRequestOrder changeRequestOrder) {
 * this.changeRequestOrder = changeRequestOrder; }
 * 
 * }
 */