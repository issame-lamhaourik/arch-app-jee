package myboot.app1.security;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.DispatcherType;
import myboot.app1.dao.XUserRepository;
import myboot.app1.model.XUser;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@Profile("simple")
public class SpringSecuritySimple {

	@Autowired
	XUserRepository userRepo;

	@PostConstruct
	public void init() {
		var encoder = passwordEncoder();
		var aa = new XUser("aaa", encoder.encode("aaa"), Set.of("ADMIN", "USER"));
		var bb = new XUser("bbb", encoder.encode("bbb"), Set.of("USER"));
		userRepo.save(aa);
		userRepo.save(bb);
		System.out.println("--- INIT SPRING SECURITY SIMPLE");
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		String[] anonymousRequests = { //
				"/", "/webjars/**", "/login", //
				"/app", "/app.js", //
				"/api/**", "/apibis/**",//
		};
		String[] adminRequests = { //
				"/for-admin/**", //
		};

		http.authorizeHttpRequests(config -> {//
			config.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll();
			// Pour tous
			config.requestMatchers(anonymousRequests).permitAll();//
			// Pour les admins
			config.requestMatchers(adminRequests).hasAnyAuthority("ADMIN");//
			// Pour les autres
			config.anyRequest().authenticated();
		});
		// Nous autorisons un formulaire de login
		http.formLogin(config -> {
			config.permitAll();
		});
		// Nous autorisons un formulaire de logout
		http.logout(config -> {
			config.permitAll();
			config.logoutSuccessUrl("/");
		});
		// Nous activons CSRF pour les actions protégées
		http.csrf(config -> {
			config.ignoringRequestMatchers(anonymousRequests);
		});
		return http.build();
	}

	@Autowired
	UserDetailsService userDetailsService;

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	/*
	 * Définir le fournisseur d'authentification. Nous utilisons la version
	 * DaoAuthenticationProvider qui récupère les informations à partir du
	 * UserDetailsService que nous avons défini avant.
	 */
	@Bean
	public AuthenticationProvider myAuthenticationProvider(//
			@Autowired PasswordEncoder encoder, //
			@Autowired UserDetailsService userDetailsService) {
		var authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(encoder);
		return authProvider;
	}

	/*
	 * Définir la politique par défaut pour le cryptage des mots de passe.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
