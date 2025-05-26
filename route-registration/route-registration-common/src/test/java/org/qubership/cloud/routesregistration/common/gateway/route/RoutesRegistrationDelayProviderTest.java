package org.qubership.cloud.routesregistration.common.gateway.route;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoutesRegistrationDelayProviderTest {

    private RoutesRegistrationDelayProvider routesRegistrationDelayProvider;

    @BeforeEach
    public void setUp() {
        routesRegistrationDelayProvider = new RoutesRegistrationDelayProvider();
    }

    @Test
    public void testIsRegistrationActive_default() {
        boolean isActiveRegistration = routesRegistrationDelayProvider.isRegistrationActive();
        assertTrue(isActiveRegistration);
    }

    @Test
    public void testPauseRegistration() {
        long timeBeforeWait = System.currentTimeMillis();
        routesRegistrationDelayProvider.pauseRegistration();
        assertTrue((System.currentTimeMillis() - timeBeforeWait) >= 1000);
    }

    @Test
    public void getProgressiveGatewayTimeoutTest() {
        assertEquals(1000, routesRegistrationDelayProvider.getTimeout());
        assertEquals(2000, routesRegistrationDelayProvider.getTimeout());
        for (int i = 0; i <= 11; i++) {
            routesRegistrationDelayProvider.getTimeout();
        }
        assertEquals(10000, routesRegistrationDelayProvider.getTimeout());
    }
}
