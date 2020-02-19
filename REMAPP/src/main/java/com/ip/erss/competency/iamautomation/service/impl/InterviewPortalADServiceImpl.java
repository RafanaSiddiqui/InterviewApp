package com.ip.erss.competency.iamautomation.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.stereotype.Repository;

import com.ip.erss.competency.iamautomation.model.InterviewPortalPanel;
import com.ip.erss.competency.iamautomation.service.InterviewPortalADService;


@Repository
public class InterviewPortalADServiceImpl implements InterviewPortalADService {
	
	private final Logger logger = LoggerFactory.getLogger(InterviewPortalADServiceImpl.class);

	@Value("${ldap.baseDN}")
	private String baseDN;

	@Value("${ldap.URL}")
	private String ldapURL;

	@Value("${ldap.userDN}")
	private String userDN;

	@Value("${ldap.password}")
	private String userPassword;

	@Value("${ldap.loginAttribute}")
	private String loginAttr;
	
		
	public LdapContext getLdapContext(){
		LdapContext ctx = null;
		try{
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			env.put(Context.SECURITY_PRINCIPAL, userDN);
			env.put(Context.SECURITY_CREDENTIALS, userPassword);
			env.put(Context.PROVIDER_URL, ldapURL);
			env.put(Context.REFERRAL, "follow");
			ctx = new InitialLdapContext(env, null);
			logger.info("Connection Successful.");
		}catch(NamingException nex){
			logger.info("LDAP Connection: FAILED");
			nex.printStackTrace();
		}
		return ctx;
	}
	
	public InterviewPortalPanel getEmployeeDetails(String employeeid, String employeeName) {
		InterviewPortalPanel panel = new InterviewPortalPanel();
		try {
			LdapContext ctx = getLdapContext();
			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String[] attrIDs = { "sAMAccountName", "cn"};
			constraints.setReturningAttributes(attrIDs);
			NamingEnumeration answer = ctx.search(baseDN, "sAMAccountName="+ employeeid, constraints);
			String empId = "";
			String empName = "";
			if (answer.hasMore()) {
				Attributes attrs = ((SearchResult) answer.next()).getAttributes();
				empId = attrs.get("sAMAccountName").get(0).toString();
				empName = attrs.get("cn").get(0).toString();
				panel.setEmpId(Long.parseLong(empId));
				panel.setName(empName);
				panel.setValid(true);
				//Attribute directReports = attrs.get("directReports");				
			}else{
				//throw new Exception("Invalid User");
				// Invalid User
				panel.setEmpId(Long.parseLong(employeeid));
				panel.setName(empName);
				panel.setValid(false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return panel;
	}

	private LdapTemplate getLdapTemplate() throws Exception {
		LdapTemplate ldapTemplate = new LdapTemplate(getContextSource());
		ldapTemplate.setIgnorePartialResultException(true);
		ldapTemplate.afterPropertiesSet();
		return ldapTemplate;
	}

	private ContextSource getContextSource() {
		LdapContextSource contextSource = new LdapContextSource();
		contextSource.setUrl(ldapURL);
		contextSource.setUserDn(userDN);
		contextSource.setPassword(userPassword);
		contextSource.afterPropertiesSet();
		return contextSource;
	}


	
	/**
	 * @param employeeid
	 * @return String 
	 * Comments - Added on 24-09-2018 to get Email ID for an Employee
	 */
	public String getEmployeeEmail(String employeeid) {
		String empEmail = "";

		try {
			LdapContext ctx = getLdapContext();
			SearchControls constraints = new SearchControls();

			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String[] attrIDs = { "sAMAccountName", "cn","mail"};
			constraints.setReturningAttributes(attrIDs);
			NamingEnumeration answer = ctx.search(baseDN, "sAMAccountName="+ employeeid, constraints);
			
			if (answer.hasMore()) {
				Attributes attrs = ((SearchResult) answer.next()).getAttributes();
				empEmail = attrs.get("mail").get(0).toString();				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return empEmail;
	}
	
}
