
package org.camunda.bpm.getstarted.loanapproval;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@EnableProcessApplication
public class WorkflowConfiguration {

  @Autowired
  private RuntimeService runtimeService;

  @EventListener
  public void processPostDeploy(PostDeployEvent event) {
    runtimeService.startProcessInstanceByKey("loanApproval");
  }

}