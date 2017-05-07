package org.pw.foodordering.elements;

public class TestClass
{
  String one;
  String two;
  
  public TestClass(String a, String b)
  {
    this.one = a;
    this.two = b;
  }
  
  public TestClass()
  {
    this.one = "null";
    this.two = "null";
  }
  
  public String getOne()
  {
    return this.one;
  }
  
  public void setOne(String one)
  {
    this.one = one;
  }
  
  public String getTwo()
  {
    return this.two;
  }
  
  public void setTwo(String two)
  {
    this.two = two;
  }
}
