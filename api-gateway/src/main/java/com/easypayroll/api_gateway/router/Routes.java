package com.easypayroll.api_gateway.router;


import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;




import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;


import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;

@Configuration
public class Routes {
    @Bean
    public RouterFunction<ServerResponse> companyServiceRoute() {
        return route("companyservice")
                .route(RequestPredicates.path("/api/worker/**"), HandlerFunctions.http("http://localhost:8081"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> companyServiceSwagger() {
        return route("companyservice_swagger")
                .route(RequestPredicates.path("/aggregate/companyservice/v3/api-docs"), HandlerFunctions.http("http://localhost:8081"))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> payrollServiceRoute() {
        return route("payrollservice")
                .route(RequestPredicates.path("/api/payroll/**"), HandlerFunctions.http("http://localhost:8082"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> payrollServiceSwagger() {
        return route("payrollservice_swagger")
                .route(RequestPredicates.path("/aggregate/payrollservice/v3/api-docs"), HandlerFunctions.http("http://localhost:8082"))
                .filter(setPath("/api-docs"))
                .build();
    }

}
