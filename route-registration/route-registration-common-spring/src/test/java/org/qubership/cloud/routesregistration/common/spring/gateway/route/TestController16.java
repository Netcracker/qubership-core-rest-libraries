package org.qubership.cloud.routesregistration.common.spring.gateway.route;

import org.qubership.cloud.routesregistration.common.annotation.FacadeRoute;
import org.qubership.cloud.routesregistration.common.annotation.Route;
import org.qubership.cloud.routesregistration.common.gateway.route.RouteType;
import org.qubership.cloud.routesregistration.common.spring.gateway.route.annotation.GatewayRequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("TestController16")
@RequestMapping(path = "/api/v1/ingress")
@GatewayRequestMapping("/api/v1/sample-service/ingress")
@Route(type = RouteType.FACADE, gateways = RoutesTestConfiguration.DEFAULT_INGRESS_GATEWAY)
public class TestController16 {

    @Route(gateways = RoutesTestConfiguration.DEFAULT_INGRESS_GATEWAY)
    @GetMapping("/inner")
    public ResponseEntity innerRequestDetails() {
        return null;
    }

    @FacadeRoute(gateways = RoutesTestConfiguration.DEFAULT_INGRESS_GATEWAY)
    @GetMapping("/facade")
    public ResponseEntity facadeRequestDetails() {
        return null;
    }
}