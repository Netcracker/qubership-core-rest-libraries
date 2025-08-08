package com.netcracker.cloud.routesregistration.common.spring.gateway.route;

import org.qubership.cloud.routesregistration.common.annotation.FacadeRoute;
import org.qubership.cloud.routesregistration.common.annotation.Route;
import org.qubership.cloud.routesregistration.common.spring.gateway.route.annotation.GatewayRequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("TestController13")
@RequestMapping(path = "/api/v1/ingress")
@GatewayRequestMapping("/api/v1/sample-service/ingress")
@Route(gateways = RoutesTestConfiguration.INGRESS_GATEWAY)
public class TestController13 {

    @GetMapping
    public ResponseEntity getRequestDetails() {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/post")
    public ResponseEntity postRequestDetails() {
        return null;
    }

    @Route(gateways = RoutesTestConfiguration.INGRESS_GATEWAY)
    @GetMapping("/inner")
    public ResponseEntity innerRequestDetails() {
        return null;
    }

    @FacadeRoute(gateways = RoutesTestConfiguration.INGRESS_GATEWAY)
    @GetMapping("/facade")
    public ResponseEntity facadeRequestDetails() {
        return null;
    }
}
