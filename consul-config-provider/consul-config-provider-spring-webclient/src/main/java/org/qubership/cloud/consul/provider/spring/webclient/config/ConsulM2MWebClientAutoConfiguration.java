package org.qubership.cloud.consul.provider.spring.webclient.config;

import org.qubership.cloud.consul.provider.common.TokenStorage;
import org.qubership.cloud.consul.provider.common.TokenStorageFactory;
import org.qubership.cloud.consul.provider.spring.common.SpringTokenStorageFactory;
import org.qubership.cloud.consul.provider.spring.common.Utils;
import org.qubership.cloud.restclient.webclient.MicroserviceWebClient;
import org.qubership.cloud.security.common.reactive.DummyM2MManager;
import org.qubership.cloud.security.common.reactive.M2MManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.consul.ConditionalOnConsulEnabled;
import org.springframework.cloud.consul.ConsulProperties;
import org.springframework.cloud.consul.config.ConsulConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnConsulEnabled
//@EnableReactiveM2MManager
@ConditionalOnProperty(value = "spring.cloud.consul.config.m2m.enabled", havingValue = "true", matchIfMissing = true)
public class ConsulM2MWebClientAutoConfiguration {

    @Bean
    public TokenStorage consulTokenStorageViaM2MWebClient(ConsulConfigProperties consulConfigProperties,
                                                          ConsulProperties consulProperties,
                                                          M2MManager m2MManager) {
        TokenStorageFactory factory = new SpringTokenStorageFactory(consulConfigProperties, new MicroserviceWebClient());

        return factory.create(new TokenStorageFactory.CreateOptions.Builder()
                .consulUrl(Utils.formatConsulAddress(consulProperties))
                .namespace(System.getenv("NAMESPACE"))
                .m2mSupplier(() -> m2MManager.getToken().block().getTokenValue())
                .build());
    }

    @Bean
    @ConditionalOnMissingBean(M2MManager.class)
    public M2MManager m2MManager() {
        return new DummyM2MManager();
    }
}
