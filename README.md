# Cammunda spring boot starter

This is a sample to help reproduce the issue https://forum.camunda.io/t/camunda-7-21-0-fails-to-boot-with-spring-boot-3-2-x-for-multi-module-projects/51904

## Pre-requisite

- JDK 21

## Reproducing the issue

In order to re-produce this issue you can perform the following:

- `./gradlew build`
- `java -jar bootstrap/build/libs/bootstrap.jar`

You will notice that it will fail to boot with the following exception

```
java.lang.RuntimeException: org.camunda.bpm.engine.ProcessEngineException: ENGINE-08043 Exception while performing 'Deployment of Process Application org.example.camunda.WorkflowConfig' => 'Deployment of process archive 'null': ENGINE-08006 IOException while scanning archive '/nested:/bootstrap.jar'.
	at org.camunda.bpm.engine.spring.application.SpringProcessApplication.onApplicationEvent(SpringProcessApplication.java:105) ~[camunda-engine-spring-6-7.21.0.jar!/:7.21.0]
	at org.camunda.bpm.engine.spring.application.SpringProcessApplication.onApplicationEvent(SpringProcessApplication.java:52) ~[camunda-engine-spring-6-7.21.0.jar!/:7.21.0]
	at org.camunda.bpm.spring.boot.starter.SpringBootProcessApplication$$SpringCGLIB$$0.onApplicationEvent(<generated>) ~[camunda-bpm-spring-boot-starter-7.21.0.jar!/:7.21.0]
	at org.springframework.context.event.SimpleApplicationEventMulticaster.doInvokeListener(SimpleApplicationEventMulticaster.java:185) ~[spring-context-6.1.5.jar!/:6.1.5]
	at org.springframework.context.event.SimpleApplicationEventMulticaster.invokeListener(SimpleApplicationEventMulticaster.java:178) ~[spring-context-6.1.5.jar!/:6.1.5]
	at org.springframework.context.event.SimpleApplicationEventMulticaster.multicastEvent(SimpleApplicationEventMulticaster.java:156) ~[spring-context-6.1.5.jar!/:6.1.5]
	at org.springframework.context.support.AbstractApplicationContext.publishEvent(AbstractApplicationContext.java:451) ~[spring-context-6.1.5.jar!/:6.1.5]
	at org.springframework.context.support.AbstractApplicationContext.publishEvent(AbstractApplicationContext.java:384) ~[spring-context-6.1.5.jar!/:6.1.5]
	at org.springframework.context.support.AbstractApplicationContext.finishRefresh(AbstractApplicationContext.java:984) ~[spring-context-6.1.5.jar!/:6.1.5]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:627) ~[spring-context-6.1.5.jar!/:6.1.5]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:146) ~[spring-boot-3.2.4.jar!/:3.2.4]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:754) ~[spring-boot-3.2.4.jar!/:3.2.4]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:456) ~[spring-boot-3.2.4.jar!/:3.2.4]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:334) ~[spring-boot-3.2.4.jar!/:3.2.4]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1354) ~[spring-boot-3.2.4.jar!/:3.2.4]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1343) ~[spring-boot-3.2.4.jar!/:3.2.4]
	at de.gefa.roe.calculator.boot.RoeCalculatorApplication.main(RoeCalculatorApplication.java:14) ~[!/:na]
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:580) ~[na:na]
	at org.springframework.boot.loader.launch.Launcher.launch(Launcher.java:91) ~[roe-calculator-service.jar:na]
	at org.springframework.boot.loader.launch.Launcher.launch(Launcher.java:53) ~[roe-calculator-service.jar:na]
	at org.springframework.boot.loader.launch.JarLauncher.main(JarLauncher.java:58) ~[roe-calculator-service.jar:na]
Caused by: org.camunda.bpm.engine.ProcessEngineException: ENGINE-08043 Exception while performing 'Deployment of Process Application org.example.camunda.WorkflowConfig' => 'Deployment of process archive 'null': ENGINE-08006 IOException while scanning archive '/nested:/bootstrap.jar'.
	at org.camunda.bpm.container.impl.ContainerIntegrationLogger.exceptionWhilePerformingOperationStep(ContainerIntegrationLogger.java:316) ~[camunda-engine-7.21.0.jar!/:7.21.0]
	at org.camunda.bpm.container.impl.spi.DeploymentOperation.execute(DeploymentOperation.java:136) ~[camunda-engine-7.21.0.jar!/:7.21.0]
	at org.camunda.bpm.container.impl.jmx.MBeanServiceContainer.executeDeploymentOperation(MBeanServiceContainer.java:160) ~[camunda-engine-7.21.0.jar!/:7.21.0]
	at org.camunda.bpm.container.impl.spi.DeploymentOperation$DeploymentOperationBuilder.execute(DeploymentOperation.java:216) ~[camunda-engine-7.21.0.jar!/:7.21.0]
	at org.camunda.bpm.container.impl.RuntimeContainerDelegateImpl.deployProcessApplication(RuntimeContainerDelegateImpl.java:102) ~[camunda-engine-7.21.0.jar!/:7.21.0]
	at org.camunda.bpm.application.AbstractProcessApplication.deploy(AbstractProcessApplication.java:71) ~[camunda-engine-7.21.0.jar!/:7.21.0]
	at org.camunda.bpm.engine.spring.application.SpringProcessApplication.start(SpringProcessApplication.java:110) ~[camunda-engine-spring-6-7.21.0.jar!/:7.21.0]
	at org.camunda.bpm.engine.spring.application.SpringProcessApplication.afterPropertiesSet(SpringProcessApplication.java:119) ~[camunda-engine-spring-6-7.21.0.jar!/:7.21.0]
	at org.camunda.bpm.spring.boot.starter.SpringBootProcessApplication.afterPropertiesSet(SpringBootProcessApplication.java:102) ~[camunda-bpm-spring-boot-starter-7.21.0.jar!/:7.21.0]
	at org.camunda.bpm.engine.spring.application.SpringProcessApplication.onApplicationEvent(SpringProcessApplication.java:96) ~[camunda-engine-spring-6-7.21.0.jar!/:7.21.0]
	... 21 common frames omitted
Caused by: org.camunda.bpm.engine.ProcessEngineException: ENGINE-08006 IOException while scanning archive '/nested:/bootstrap.jar'.
	at org.camunda.bpm.container.impl.ContainerIntegrationLogger.exceptionWhileScanning(ContainerIntegrationLogger.java:74) ~[camunda-engine-7.21.0.jar!/:7.21.0]
	at org.camunda.bpm.container.impl.deployment.scanning.ClassPathProcessApplicationScanner.handleArchive(ClassPathProcessApplicationScanner.java:195) ~[camunda-engine-7.21.0.jar!/:7.21.0]
	at org.camunda.bpm.container.impl.deployment.scanning.ClassPathProcessApplicationScanner.scanPath(ClassPathProcessApplicationScanner.java:159) ~[camunda-engine-7.21.0.jar!/:7.21.0]
	at org.camunda.bpm.container.impl.deployment.scanning.ClassPathProcessApplicationScanner.scanUrl(ClassPathProcessApplicationScanner.java:140) ~[camunda-engine-7.21.0.jar!/:7.21.0]
	at org.camunda.bpm.container.impl.deployment.scanning.ClassPathProcessApplicationScanner.scanPaResourceRootPath(ClassPathProcessApplicationScanner.java:98) ~[camunda-engine-7.21.0.jar!/:7.21.0]
	at org.camunda.bpm.container.impl.deployment.scanning.ClassPathProcessApplicationScanner.findResources(ClassPathProcessApplicationScanner.java:59) ~[camunda-engine-7.21.0.jar!/:7.21.0]
	at org.camunda.bpm.container.impl.deployment.scanning.ProcessApplicationScanningUtil.findResources(ProcessApplicationScanningUtil.java:68) ~[camunda-engine-7.21.0.jar!/:7.21.0]
	at org.camunda.bpm.container.impl.deployment.DeployProcessArchiveStep.findResources(DeployProcessArchiveStep.java:184) ~[camunda-engine-7.21.0.jar!/:7.21.0]
	at org.camunda.bpm.container.impl.deployment.DeployProcessArchiveStep.performOperationStep(DeployProcessArchiveStep.java:105) ~[camunda-engine-7.21.0.jar!/:7.21.0]
	at org.camunda.bpm.container.impl.spi.DeploymentOperation.execute(DeploymentOperation.java:120) ~[camunda-engine-7.21.0.jar!/:7.21.0]
	... 29 common frames omitted
Caused by: java.nio.file.NoSuchFileException: nested:/bootstrap.jar
	at java.base/sun.nio.fs.UnixException.translateToIOException(UnixException.java:92) ~[na:na]
	at java.base/sun.nio.fs.UnixException.rethrowAsIOException(UnixException.java:106) ~[na:na]
	at java.base/sun.nio.fs.UnixException.rethrowAsIOException(UnixException.java:111) ~[na:na]
	at java.base/sun.nio.fs.UnixFileAttributeViews$Basic.readAttributes(UnixFileAttributeViews.java:55) ~[na:na]
	at java.base/sun.nio.fs.UnixFileSystemProvider.readAttributes(UnixFileSystemProvider.java:171) ~[na:na]
	at java.base/sun.nio.fs.LinuxFileSystemProvider.readAttributes(LinuxFileSystemProvider.java:99) ~[na:na]
	at java.base/java.nio.file.Files.readAttributes(Files.java:1853) ~[na:na]
	at java.base/java.util.zip.ZipFile$Source.get(ZipFile.java:1445) ~[na:na]
	at java.base/java.util.zip.ZipFile$CleanableResource.<init>(ZipFile.java:724) ~[na:na]
	at java.base/java.util.zip.ZipFile.<init>(ZipFile.java:251) ~[na:na]
	at java.base/java.util.zip.ZipFile.<init>(ZipFile.java:180) ~[na:na]
	at java.base/java.util.zip.ZipFile.<init>(ZipFile.java:194) ~[na:na]
	at org.camunda.bpm.container.impl.deployment.scanning.ClassPathProcessApplicationScanner.handleArchive(ClassPathProcessApplicationScanner.java:165) ~[camunda-engine-7.21.0.jar!/:7.21.0]
	... 37 common frames omitted
```
