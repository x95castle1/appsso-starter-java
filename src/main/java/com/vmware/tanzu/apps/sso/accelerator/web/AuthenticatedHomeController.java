package com.vmware.tanzu.apps.sso.accelerator.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.stream.Collectors;

@Controller
public class AuthenticatedHomeController {
	// see: https://www.baeldung.com/spring-security-openid-connect#1-accessing-user-information
	@GetMapping("/authenticated/home")
	public String authenticatedHome(Model model, @AuthenticationPrincipal OidcUser authenticatedUser) {
		model.addAttribute("username", authenticatedUser.getClaims().get("sub"));

		authenticatedUser.getClaims().keySet().stream()
                .forEach(System.out::println);

		String allClaims = authenticatedUser.getClaims().entrySet().stream()
				.map(entrySet -> entrySet.getKey() + ": " + entrySet.getValue())
				.collect(Collectors.joining("<br>"));

		model.addAttribute("all_claims", allClaims);

		return "authenticated-home.html";
	}

}