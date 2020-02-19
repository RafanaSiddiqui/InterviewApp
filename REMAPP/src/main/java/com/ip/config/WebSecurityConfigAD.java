package com.ip.config;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import com.ip.erss.competency.iamautomation.ADAuthenticationJDBCAuthorizationProvider;
import com.ip.erss.competency.iamautomation.ADLdapAuthenticationProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfigAD extends WebSecurityConfigurerAdapter {

	@Value("${ad.domain}")
	private String AD_DOMAIN;

	@Value("${ad.url}")
	private String AD_URL;
	
	@Value("${ad.rootDn}")
	private String AD_ROOT_DN;
	
	@Value("${ad.searchFilter}")
	private String AD_SEARCH_FILTER;
	
	@Value("${ad.userName}")
	private String AD_USERNAME;
	
	@Value("${ad.userPass}")
	private String AD_USERPASS;
	

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;
    
    /*@Autowired
    @Qualifier("userAuthService")
    LdapAuthoritiesPopulator ldapAuthoritiesPopulator;*/
	
    @Override
	protected void configure(HttpSecurity http) throws Exception {

		/* http.csrf().disable(); */

		http.headers().contentSecurityPolicy(
				"default-src 'self' 'unsafe-inline' 'unsafe-eval'; style-src 'self' 'unsafe-inline'; img-src 'self' 'unsafe-inline' data:; child-src 'self' 'unsafe-inline'; frame-src *;")
				.and().frameOptions().sameOrigin().httpStrictTransportSecurity().includeSubDomains(true)
				.maxAgeInSeconds(31536000).and().and().authorizeRequests()
				// .antMatchers("/resources/**", "/static/**",
				// "/loadPageableMasterDemandRequest/**", "/loadPageableDemandReqBySoId/**",
				// "/fetchConfiguration/**", "/fetchAllRoles/**", "/searchAssociates/**",
				// "/loadAllConfiguration/**").permitAll()
				.antMatchers("/resources/**", "/static/**", "/sendMail/**", "/loadSkillAtteatationBySupervisorApi/**",
						"/updateSkillAttestationApi/**", "/loadAllAssociteDetails/", "/indexAllBots/**",
						"/indexAllBotMeasurements/**", "/indexAllTrackers/**", "/indexAllAccountBases/**",
						"/indexAllNoBots/**", "/indexAllFunctionalSpread/**")
				.permitAll()
				.antMatchers("/masterDemand/**", "/dashboards/**", "/associateSearch/**", "/loadAssociates/**",
						"/fetchConfiguration/**", "/loadPageableMasterDemandRequest/**",
						"/loadPageableMasterDemandRequest/**", "/editDemandRequest/**", "/createNewMasterDemand/**")
				.access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DELIVERY_LEAD') or hasRole('ROLE_TSC') or hasRole('ROLE_TAG')")
				.antMatchers("/masterDashboard/**", "/addNewUser/**", "/findUser/**", "/Configuration/**",
						"/emailTemplate/**", "/EditUser/**", "/resetPassword/**", "/addNewUser/**", "/configuration/**",
						"/configurationShowAll/**", "/fetchtemplateGUIConfiguration/**", "/configurationByCategory/**",
						"/configurationByCategoryandName/**", "/updateUsrDetails/**", "/fetchConfiguration/**",
						"/showAllAccountBases/**", "/loadAllAccountBases/**", "/accountBaseRequest/**",
						"/interview/admin/**")
				.access("hasRole('ROLE_ADMIN')")
				.antMatchers("/bulkRequestUpload/**", "/updateBulkRequest/**", "/bulkRequest/**",
						"/bulkrequestByStatus/**", "/requestShowAllBulk/**", "/bulkrequestByID/**",
						"/bulkrequestByIdStatus/**", "/downloadBulkFile/**")
				.access("hasRole('ROLE_ADMIN') or hasRole('ROLE_TSC') or hasRole('ROLE_TAG')")
				.antMatchers("/addBot/**", "/showAllBots/**", "/createBotMeasurement/**", "/showAllMeasurements/**",
						"/botDashboards/**", "/defaulterList/**", "/addBot/**", "/editBot/**", "/showAllBots/**",
						"/loadAllBotMeasurements/**", "/loadBotMeasurementById/**", "/updateBotMeasurement/**",
						"/botMeasurementRequest/**", "/deleteBotMeasurement/**", "/downloadDefaulterFile/**",
						"/getDefaulters/**", "/loadAllBots/**", "/botRequest/**", "/loadBotById/**", "/deleteBot/**",
						"/updateBot/**", "/checkBotName/**", "/createBotMeasurement/**", "/showAllMeasurements/**",
						"/editBotMeasurement/**")
				.access("hasRole('ROLE_ADMIN') or hasRole('ROLE_BOTS')").antMatchers("/interview/hr/**")
				.access("hasRole('ROLE_ADMIN') or hasRole('ROLE_TSC') or hasRole('ROLE_CTL') or hasRole('ROLE_PANEL') or hasRole('ROLE_COMPTEAM')").antMatchers("/interview/compLead/**")
				.access("hasRole('ROLE_ADMIN') or hasRole('ROLE_CTL') or hasRole('ROLE_COMPTEAM') or hasRole('ROLE_PANEL')").antMatchers("/interview/panel/**")
				.access("hasRole('ROLE_ADMIN') or hasRole('ROLE_PANEL')  or hasRole('ROLE_TSC') or hasRole('ROLE_COMPTEAM')").antMatchers("/interview/admin/**","/interview/compTeam/**")
				.access("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPTEAM')").anyRequest().authenticated().and().formLogin()
				.loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll().and()
				.logout().permitAll().and().csrf()
				// .ignoringAntMatchers("/h2-console/**", "/loadPageableMasterDemandRequest/**",
				// "/loadPageableDemandReqBySoId/**", "/fetchConfiguration/**",
				// "/fetchAllRoles/**", "/searchAssociates/**", "/loadAllConfiguration/**")
				.ignoringAntMatchers("/masterDemandRequest/**", "/updateUsrDetails/**", "/updatePassword/**",
						"/createUser/**", "/emailTemplateService/**", "/createNewPageRedirect/**",
						"/updateBulkRequest/**", "/bulkRequest/**", "/updateBot/**", "/botRequest/**",
						"/updateBotMeasurement/**", "/botMeasurementRequest/**", "/searchAssociates/**", "/api/**",
						"/configuration/**", "/h2-console/**", "/loadPageableMasterDemandRequest/**",
						"/loadPageableDemandReqBySoId/**", "/fetchConfiguration/**", "/fetchAllRoles/**",
						"/searchAssociates/**", "/loadAllConfiguration/**", "/getUsers/**", "/deleteBot/**",
						"/deleteBotMeasurement/**", "/accountBaseRequest/**", "/loadSkillAtteatationBySupervisorApi/**",
						"/updateSkillAttestationApi/**", "/loadAllAssociteDetails/**", "/indexAllBots/**",
						"/indexAllBotMeasurements/**", "/indexAllTrackers/**", "/indexAllAccountBases/**",
						"/indexAllNoBots/**", "/indexAllFunctionalSpread/**")
				.csrfTokenRepository(csrfTokenRepository()).and().addFilterAfter(csrfHeaderFilter(), CsrfFilter.class)
				.authorizeRequests();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		System.out.println("configure------------AuthenticationManagerBuilder----------");
		authManagerBuilder.authenticationProvider(activeDirectoryLdapAuthenticationProvider())
				.userDetailsService(userDetailsService());
	}

	@Bean
	public AuthenticationManager authenticationManager() {
		System.out.println("configure--------authenticationManager--------------");
		return new ProviderManager(Arrays.asList(activeDirectoryLdapAuthenticationProvider()));
	}

	@Bean
	public AuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
		System.out.println("configure----------------------" + AD_DOMAIN);
		ADLdapAuthenticationProvider provider = new ADLdapAuthenticationProvider(AD_DOMAIN, AD_URL);
		provider.setConvertSubErrorCodesToExceptions(true);
		provider.setUseAuthenticationRequestCredentials(true);
		provider.setSearchFilter(AD_SEARCH_FILTER);
		ADAuthenticationJDBCAuthorizationProvider authenticationProvider = new ADAuthenticationJDBCAuthorizationProvider(provider);
		return authenticationProvider;
	}
	
	
	 /*@Autowired
	    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		 	auth.authenticationProvider(activeDirectoryLdapAuthenticationProvider());
		 	//auth.userDetailsService(userDetailsService);
		 	//auth.authenticationProvider(authenticationProvider());
	    }*/
	 
	     
	     
	     
	    /*@Bean
	    public AuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	        authenticationProvider.setUserDetailsService(userDetailsService);
	        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
	        return authenticationProvider;
	    }*/

	/*@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth.ldapAuthentication()
	      .contextSource()
	        .url(AD_URL)
	        .managerDn(AD_USERNAME)
	        .managerPassword(AD_USERPASS)
	      .and()
	      .userSearchBase(AD_ROOT_DN)
	      .userSearchFilter(AD_SEARCH_FILTER)
	      .ldapAuthoritiesPopulator(ldapAuthoritiesPopulator);
	}*/
	
	/*private static final class NullLdapAuthoritiesPopulator implements LdapAuthoritiesPopulator {
		@Override
		public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData,
				String username) {
			return AuthorityUtils.NO_AUTHORITIES;
		}
	}*/
	
	private Filter csrfHeaderFilter() {
        return new OncePerRequestFilter() {
			
			@Override
			protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
					throws ServletException, IOException {
				CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
                if (csrf != null) {
                    Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
                    String token = csrf.getToken();
                    if (cookie == null || token != null && !token.equals(cookie.getValue())) {
                        cookie = new Cookie("XSRF-TOKEN", token);
                        cookie.setPath("/");
                        //cookie.setHttpOnly(true);
                        //cookie.setSecure(true);
                        response.addCookie(cookie);
                    }
                }
                filterChain.doFilter(request, response);
				
			}
		};
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
    
	
}