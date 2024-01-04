package com.example.Gateway.filter;

import com.example.Gateway.config.AppConfig;
import com.example.Gateway.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {


    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtService jwtService;

    public  AuthenticationFilter(){
        super(Config.class);
    }


    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {

            if(routeValidator.isSecured.test(exchange.getRequest())){
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("missing authorization ");
                }

                String authHeaders=exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if(authHeaders!=null && authHeaders.startsWith("Bearer ")){
                    authHeaders = authHeaders.substring(7);

                }

                try {
                   jwtService.validateToken(authHeaders);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    throw new RuntimeException("token invalid");

                }
            }
            return chain.filter(exchange);
        });
    }



    public static class Config {}
}
