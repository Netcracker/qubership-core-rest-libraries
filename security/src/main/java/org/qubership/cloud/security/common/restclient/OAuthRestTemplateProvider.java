package org.qubership.cloud.security.common.restclient;

import org.springframework.web.client.RestTemplate;

public interface OAuthRestTemplateProvider {
    RestTemplate getOAuthRestTemplate();
}
