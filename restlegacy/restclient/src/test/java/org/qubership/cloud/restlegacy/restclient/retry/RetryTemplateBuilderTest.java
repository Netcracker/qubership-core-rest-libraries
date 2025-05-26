package org.qubership.cloud.restlegacy.restclient.retry;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RetryTemplateBuilderTest {

    @Test
    public void testIncorrectCreateBuilder(){
        assertThrows(IllegalStateException.class, () -> {
            new RetryTemplateBuilder().withCircuitBreakerOpenTimeoutInMillis(100L).build();
        });
    }
}
