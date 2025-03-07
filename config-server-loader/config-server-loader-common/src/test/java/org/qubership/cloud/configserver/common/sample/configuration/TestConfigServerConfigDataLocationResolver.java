package org.qubership.cloud.configserver.common.sample.configuration;

import org.qubership.cloud.configserver.common.configuration.AbstractCustomConfigServerConfigDataLocationResolver;
import org.qubership.cloud.restclient.MicroserviceRestClient;
import org.qubership.cloud.restclient.entity.RestClientResponseEntity;
import org.mockito.Mockito;
import org.springframework.boot.logging.DeferredLogFactory;
import org.springframework.cloud.config.environment.Environment;

import java.util.HashMap;
import java.util.Map;

public class TestConfigServerConfigDataLocationResolver extends AbstractCustomConfigServerConfigDataLocationResolver {

    public TestConfigServerConfigDataLocationResolver(DeferredLogFactory log) {
        super(log);
    }

    @Override
    public int getOrder() {
        return super.getOrder() - 1;
    }

    @Override
    public MicroserviceRestClient getMicroserviceRestClient() {
        MicroserviceRestClient microserviceRestClient = Mockito.mock(MicroserviceRestClient.class);
        RestClientResponseEntity<Environment> restClientResponseEntity;
        Environment environment = new Environment("test", "default");
        environment.setVersion("test-version");
        environment.setState("test-state");
        Map<String, String> testMap = new HashMap<>();
        testMap.put("test.key", "test_value");
        environment.add(new org.springframework.cloud.config.environment.PropertySource("test", testMap));
        restClientResponseEntity = new RestClientResponseEntity<>(environment, 200);
        Mockito.doReturn(restClientResponseEntity)
                .when(microserviceRestClient).doRequest(Mockito.any(),
                Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        return microserviceRestClient;
    }
}
