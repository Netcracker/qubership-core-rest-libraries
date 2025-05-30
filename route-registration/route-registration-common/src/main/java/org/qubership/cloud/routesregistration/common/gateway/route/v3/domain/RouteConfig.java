package org.qubership.cloud.routesregistration.common.gateway.route.v3.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Data
@Builder
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteConfig that = (RouteConfig) o;
        if (!Objects.equals(version, that.version)) {
            return false;
        }
        if (routes == that.routes) {
            return true;
        }
        return routes != null && that.routes != null && routes.size() == that.routes.size()
                && routes.containsAll(that.routes) && that.routes.containsAll(routes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version);
    }
}
