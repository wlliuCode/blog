package com.wlliu.blog.infrastructure.apigateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.*;


/**
 * sentinel gateway 限流
 */
@Configuration
public class GatewayConfig {

    private final List<ViewResolver> viewResolvers;


    private final ServerCodecConfigurer serverCodecConfigurer;

    public GatewayConfig(ObjectProvider<List<ViewResolver>> viewResolversRrovider,
                         ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolversRrovider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }

    @PostConstruct
    public void initGatewayRules() {


        //路由维度限流
        /*Set<GatewayFlowRule> rules = new HashSet<>();
        rules.add(
                new GatewayFlowRule("picture_route")
                        .setCount(1)
                        .setIntervalSec(1)
        );
        GatewayRuleManager.loadRules(rules);*/


        //API维度限流
        Set<GatewayFlowRule> rules = new HashSet<>();
        rules.add(new GatewayFlowRule("picture-api1").setCount(1).setIntervalSec(1));
        rules.add(new GatewayFlowRule("picture-api2").setCount(1).setIntervalSec(1));
        GatewayRuleManager.loadRules(rules);

    }


    @PostConstruct
    public void initBlockHandlers() {
        BlockRequestHandler blockRequestHandler = new BlockRequestHandler() {
            @Override
            public Mono<ServerResponse> handleRequest(
                    ServerWebExchange serverWebExchange,
                    Throwable throwable) {
                Map<Object, Object> map = new HashMap<>();
                map.put("code", 0);
                map.put("message", "限流");
                return ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromValue(map));
                //.contentType(MediaType.APPLICATION_JSON_UTF8)
                //.body(BodyInserters.fromObject(map));
            }
        };
        GatewayCallbackManager.setBlockHandler(blockRequestHandler);
    }

    /**
     * 自定义API分组
     */
    @PostConstruct
    private void initCustomizedApis() {
        Set<ApiDefinition> definitions = new HashSet<>();
        ApiDefinition apiDefinition1 = new ApiDefinition().setApiName("picture-api1")
                .setPredicateItems(new HashSet<ApiPredicateItem>() {{
                    add(new ApiPathPredicateItem().setPattern("/picture-api/picture/api1/**")
                            .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
                }});
        ApiDefinition apiDefinition2 = new ApiDefinition().setApiName("picture-api2")
                .setPredicateItems(new HashSet<ApiPredicateItem>() {{
                    add(new ApiPathPredicateItem().setPattern("/picture-api/picture/api2/a2")
                            .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
                }});
        definitions.add(apiDefinition1);
        definitions.add(apiDefinition2);
        GatewayApiDefinitionManager.loadApiDefinitions(definitions);
    }


}
