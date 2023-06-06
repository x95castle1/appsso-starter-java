package com.vmware.tanzu.apps.sso.accelerator.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.google.common.base.Joiner;

@Controller
public class AuthenticatedHomeController {
	// see: https://www.baeldung.com/spring-security-openid-connect#1-accessing-user-information
	@GetMapping("/authenticated/home")
	public String authenticatedHome(Model model, @AuthenticationPrincipal OidcUser authenticatedUser) {
		model.addAttribute("username", authenticatedUser.getClaims().get("sub"));

		authenticatedUser.getClaims().keySet().stream()
                .forEach(System.out::println);

								model.addAttribute("all_claims", Joiner.on(", ").withKeyValueSeparator(": ").join(authenticatedUser.getClaims()));

		return "authenticated-home.html";
	}

}