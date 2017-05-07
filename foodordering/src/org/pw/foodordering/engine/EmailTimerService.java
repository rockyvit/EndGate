package org.pw.foodordering.engine;

import java.util.TimerTask;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pw.foodordering.interfaces.EmailServiceRepositoryInterface;

public class EmailTimerService
  extends TimerTask
{
  private EmailServiceRepositoryInterface emailServiceRepository;
  private Log logger = LogFactory.getLog(getClass());
  private Boolean sendingInProgress = Boolean.valueOf(false);
  
  public void setEmailServiceRepository(EmailServiceRepositoryInterface emailServiceRepository)
  {
    this.emailServiceRepository = emailServiceRepository;
  }
  
  public void run()
  {
    if (this.sendingInProgress.booleanValue())
    {
      this.logger.error("Sending emails lasts too long...");
      return;
    }
    this.sendingInProgress = Boolean.valueOf(true);
    try
    {
      this.emailServiceRepository.checkOrdersAndSend();
    }
    catch (Exception e)
    {
      this.logger.error(e);
    }
    this.sendingInProgress = Boolean.valueOf(false);
    this.logger.debug("Continuing...");
  }
}
