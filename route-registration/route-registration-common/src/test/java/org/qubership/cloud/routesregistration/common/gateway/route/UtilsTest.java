package org.qubership.cloud.routesregistration.common.gateway.route;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilsTest {

    @Test
    public void formatMicroserviceInternalURLTest() {
        String result = Utils.formatMicroserviceInternalURL("cloudTest", "nameTest", "portTest", "/contextTest", false);
        assertEquals("http://cloudTest:portTest/contextTest", result);
    }
}
