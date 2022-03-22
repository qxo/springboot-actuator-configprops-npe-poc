package poc.springboot.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration()
@ConfigurationProperties("app")
public class AppConfig {

	private boolean enabled = true;

	@Bean("testbean")
	public Object testBean() {
		return new Object();
	}
//	@Bean
//	public RestTemplate restTemplate(List<HttpMessageConverter<?>> messageConverters
//	// ,@Autowired @Qualifier("requestFactory") HttpComponentsClientHttpRequestFactory requestFactory
//	) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
//		final RestTemplate restTemplate = new RestTemplate(messageConverters);
//		// restTemplate.setRequestFactory(requestFactory);
//		return restTemplate;
//	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
