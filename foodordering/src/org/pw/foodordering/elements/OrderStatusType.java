package org.pw.foodordering.elements;

public enum OrderStatusType
{
  UNDER_CONSTRUCTION(0),  CONSTRUCTED(1),  CLOSED(2),  ERROR(3);
  
  private int id;
  
  private OrderStatusType(int id)
  {
    this.id = id;
  }
  
  public int getId()
  {
    return this.id;
  }
}
