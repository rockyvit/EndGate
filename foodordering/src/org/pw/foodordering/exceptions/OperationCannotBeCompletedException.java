package org.pw.foodordering.exceptions;

public class OperationCannotBeCompletedException
  extends Exception
{
  private static final long serialVersionUID = 5653415723114320732L;
  private String exceptionInformation = "Operation cannot be completed";
  
  public OperationCannotBeCompletedException() {}
  
  public OperationCannotBeCompletedException(String msg)
  {
    this.exceptionInformation = (this.exceptionInformation + ": " + msg);
  }
  
  public String toString()
  {
    return this.exceptionInformation + "\n" + super.toString();
  }
  
  public String getMessage()
  {
    return this.exceptionInformation + "\n" + super.getMessage();
  }
}
