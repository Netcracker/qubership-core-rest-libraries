package org.qubership.cloud.restlegacy.resttemplate.requestfactory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.qubership.cloud.restlegacy.resttemplate.configuration.ClientHttpRequestFactoryConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;

@SpringBootTest(classes = {ClientHttpRequestFactoryConfiguration.class, TestRequestFactoryProviderImpl.class})
public class RequestFactoryProviderTest {

    @Autowired
    @Qualifier("clientHttpRequestFactory")
    ClientHttpRequestFactory clientHttpRequestFactory;

    @Test
    public void maxTotalTest() {
        Assertions.assertTrue(clientHttpRequestFactory instanceof BufferingClientHttpRequestFactory);
    }
}
