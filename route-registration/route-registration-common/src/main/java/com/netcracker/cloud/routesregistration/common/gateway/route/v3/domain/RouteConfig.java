package com.netcracker.cloud.routesregistration.common.gateway.route.v3.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Data
@Builder
@EqualsAndHashCode
public class RouteConfig {
    private String version;
    private List<RouteV3> routes;

    public RouteConfig merge(RouteConfig anotherRouteConfig) {
        if (!Objects.equals(version, anotherRouteConfig.getVersion())) {
            throw new IllegalArgumentException("Cannot merge RouteConfigs with different versions");
        }

        anotherRouteConfig.routes.forEach(anotherRoute -> {
            Optional<RouteV3> routeToMerge = routes.stream()
                    .filter(thisRoute -> thisRoute.getDestination().equals(anotherRoute.getDestination()))
                    .findAny();
            if (routeToMerge.isPresent()) {
                routeToMerge.get().merge(anotherRoute);
            } else {
                routes.add(anotherRoute);
            }
        });

        return this;
    }
}
