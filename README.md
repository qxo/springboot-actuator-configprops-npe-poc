NPE for /actuator/configprops POC
==================================
* start spring-boot app (SpringbootActuatorConfigpropsPocApplication)

```
mvn spring-boot:run 
```


* visit http://127.0.0.1:8081/actuator/configprops we see the issue.

```
java.lang.NullPointerException
	at java.base/java.util.stream.Collectors.lambda$uniqKeysMapAccumulator$1(Collectors.java:177)
	at java.base/java.util.stream.ReduceOps$3ReducingSink.accept(ReduceOps.java:169)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:177)
	at java.base/java.util.Iterator.forEachRemaining(Iterator.java:133)
	at java.base/java.util.Spliterators$IteratorSpliterator.forEachRemaining(Spliterators.java:1801)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
	at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:913)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:578)
	at org.springframework.boot.actuate.context.properties.ConfigurationPropertiesReportEndpoint.describeBeans(ConfigurationPropertiesReportEndpoint.java:220)
	at org.springframework.boot.actuate.context.properties.ConfigurationPropertiesReportEndpoint.extract(ConfigurationPropertiesReportEndpoint.java:151)
	at org.springframework.boot.actuate.context.properties.ConfigurationPropertiesReportEndpoint.configurationProperties(ConfigurationPropertiesReportEndpoint.java:137)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.springframework.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:282)
	at org.springframework.boot.actuate.endpoint.invoke.reflect.ReflectiveOperationInvoker.invoke(ReflectiveOperationInvoker.java:74)
	at org.springframework.boot.actuate.endpoint.annotation.AbstractDiscoveredOperation.invoke(AbstractDiscoveredOperation.java:60)
```
* In **`@ConfigurationProperties` bean** class with **`@bean`** on **static method** which **the return type do not have ConfigurationProperties** annotation will trigger the issue.
ie:

```
@Configuration("appConfig")
@ConfigurationProperties("app")
public class AppConfig {

	// @Bean on static method and the return type without ConfigurationProperties annotation will trigger the issue
	@Bean("testbean2")
	public static Object testBean2() {
		return new Object();
	}
	
	//...
}
```

* `ConfigurationPropertiesBean.getAll` should ignored the null value (ConfigurationPropertiesBean) in the return Map.



## ref
* https://github.com/spring-projects/spring-boot/pull/30068

