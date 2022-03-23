package poc.springboot.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("appConfig")
@ConfigurationProperties("app")
public class AppConfig {

	// @Bean on static method and the return type without ConfigurationProperties annotation will trigger the issue
	@Bean("testbean2")
	public static Object testBean2() {
		return new Object();
	}

	private boolean enabled = true;

	// @Bean("testbean1")
	// public static AppConfig testBean1() {
	// return new AppConfig();
	// }

	// @Bean("restTemplate")
	// public static RestTemplate restTemplate(List<HttpMessageConverter<?>> messageConverters
	// // ,@Autowired @Qualifier("requestFactory") HttpComponentsClientHttpRequestFactory requestFactory
	// ) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
	// final RestTemplate restTemplate = new RestTemplate(messageConverters);
	// // restTemplate.setRequestFactory(requestFactory);
	// return restTemplate;
	// }

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
