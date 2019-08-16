package com.example.springoauth.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginCallBackController {

	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;
	
	@GetMapping("/")
	public String index(Model model, OAuth2AuthenticationToken authentication) {
		
		System.out.println(authentication.getDetails());
		OAuth2AuthorizedClient authorizedClient =
				this.authorizedClientService.loadAuthorizedClient(
						authentication.getAuthorizedClientRegistrationId(),
						authentication.getName());
		//System.out.println("(authentication.getName() "+authentication.getName());
		//System.out.println("authentication.getCredentials() "+authentication.getCredentials());
		//System.out.println("authentication.getDetails() "+authentication.getDetails());
		//System.out.println("authentication.getAuthorities() "+authentication.getAuthorities());
		//System.out.println("authentication.getPrincipal() "+authentication.getPrincipal());
	System.out.println(authorizedClient.getAccessToken().getTokenValue());
	
		model.addAttribute("userName", authentication.getName());
		model.addAttribute("clientName", authorizedClient.getClientRegistration().getClientName());
		return "index";
	}
	/*
	@GetMapping("/userinfo")
	public String userinfo(Model model, OAuth2AuthenticationToken authentication) {
		OAuth2AuthorizedClient authorizedClient =
				this.authorizedClientService.loadAuthorizedClient(
						authentication.getAuthorizedClientRegistrationId(),
						authentication.getName());
		Map userAttributes = Collections.emptyMap();
		String userInfoEndpointUri = authorizedClient.getClientRegistration()
			.getProviderDetails().getUserInfoEndpoint().getUri();
		if (!StringUtils.isEmpty(userInfoEndpointUri)) {	// userInfoEndpointUri is optional for OIDC Clients
			userAttributes = WebClient.builder()
				.filter(oauth2Credentials(authorizedClient))
				.build()
				.get()
				.uri(userInfoEndpointUri)
				.retrieve()
				.bodyToMono(Map.class)
				.block();
		}
		model.addAttribute("userAttributes", userAttributes);
		return "userinfo";
	}

	private ExchangeFilterFunction oauth2Credentials(OAuth2AuthorizedClient authorizedClient) {
		return ExchangeFilterFunction.ofRequestProcessor(
			clientRequest -> {
				ClientRequest authorizedRequest = ClientRequest.from(clientRequest)
					.header(HttpHeaders.AUTHORIZATION, "Bearer " + authorizedClient.getAccessToken().getTokenValue())
					.build();
				return Mono.just(authorizedRequest);
			});
	}
*/
}
