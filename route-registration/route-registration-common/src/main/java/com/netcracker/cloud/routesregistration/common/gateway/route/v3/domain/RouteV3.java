package com.netcracker.cloud.routesregistration.common.gateway.route.v3.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder
@EqualsAndHashCode
public class RouteV3 {
    private RouteDestination destination;
    private List<Rule> rules;

    public RouteV3 merge(RouteV3 anotherRoute) {
        if (!destination.equals(anotherRoute.getDestination())) {
            throw new IllegalArgumentException("Cannot merge RouteV3 with different RouteDestination");
        }
        mergeRules(anotherRoute.rules);
        return this;
    }

    private void mergeRules(List<Rule> rulesToMerge) {
        if (rulesToMerge == null || rulesToMerge.isEmpty()) {
            return;
        }

        List<Rule> currentRules = (rules == null) ? List.of() : rules;

        this.rules = Stream.concat(currentRules.stream(), rulesToMerge.stream())
                .distinct()
                .collect(Collectors.toList());
    }
}
