package org.pw.foodordering.controllers;

import java.sql.Timestamp;
import java.util.Comparator;
import org.pw.foodordering.elements.misc.ReportData;

class ReportListComparator
  implements Comparator<ReportData>
{
  public int compare(ReportData o1, ReportData o2)
  {
    if (o1.getTs().equals(o2.getTs())) {
      return o1.getRestName().compareTo(o2.getRestName());
    }
    Boolean ret = Boolean.valueOf(o1.getTs().before(o2.getTs()));
    if (ret.booleanValue()) {
      return -1;
    }
    return 1;
  }
}
