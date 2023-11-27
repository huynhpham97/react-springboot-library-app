/*
 * package com.luv2code.srpingbootlibrary.config;
 * 
 * import org.springframework.context.annotation.Configuration; import
 * org.springframework.data.rest.core.config.RepositoryRestConfiguration; import
 * org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer; import
 * org.springframework.web.servlet.config.annotation.CorsRegistry; import
 * org.springframework.http.HttpMethod; import
 * org.springframework.web.servlet.config.annotation.EnableWebMvc;
 * 
 * @Configuration
 * 
 * @EnableWebMvc public class WebConfig implements RepositoryRestConfigurer {
 * 
 * private String theAllowedOrigins = "http://localhost:3000";
 * 
 * @Override public void
 * configureRepositoryRestConfiguration(RepositoryRestConfiguration config,
 * CorsRegistry cors) { HttpMethod[] theUnsupportedActions = { HttpMethod.GET,
 * HttpMethod.POST, HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.PUT}; //
 * config.exposeIdsFor(Book.class); // config.exposeIdsFor(Review.class); //
 * onfig.exposeIdsFor(Message.class);
 * 
 * cors.addMapping(config.getBasePath() +
 * "/**").allowedOrigins(theAllowedOrigins);
 * 
 * } }
 */