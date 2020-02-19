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

import com.ip.erss.competency.iamautomation.model.SkillAttestation;
import com.ip.erss.competency.iamautomation.model.SkillDetails;
import com.ip.erss.competency.iamautomation.service.AdUtilService;
import com.ip.erss.competency.iamautomation.service.SkillAttestationService;

@Repository
public class AdUtilServiceImpl implements AdUtilService {
	
	private final Logger logger = LoggerFactory.getLogger(AdUtilServiceImpl.class);

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
	
	@Autowired
	private SkillAttestationService skillAttestationService;
	
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

	public List<SkillAttestation> getSupervisorDetails(String userName) {
		List<SkillAttestation> skillAttestationList = new ArrayList<SkillAttestation>();
		try {
			LdapContext ctx = getLdapContext();
			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String[] attrIDs = { "sAMAccountName", "cn", "directReports"};
			constraints.setReturningAttributes(attrIDs);
			NamingEnumeration answer = ctx.search(baseDN, "sAMAccountName="+ userName, constraints);
			String supervisorId = "";
			String supervisorName = "";
			List<SkillAttestation> skillAttestationExisting = skillAttestationService.findAllBySupervisorId(userName);
			if (answer.hasMore()) {
				Attributes attrs = ((SearchResult) answer.next()).getAttributes();
				supervisorId = attrs.get("sAMAccountName").get(0).toString();
				supervisorName = attrs.get("cn").get(0).toString();
				Attribute directReports = attrs.get("directReports");
				if(directReports != null) {
				NamingEnumeration e = directReports.getAll();
				while (e.hasMore()) {
					
					SkillAttestation skillAttestation = new SkillAttestation();
					skillAttestation.setSupervisorId(supervisorId);
					skillAttestation.setSupervisorName(supervisorName);
					String distinguishedName = e.next().toString();
					logger.info("distinguishedName  ::  " + distinguishedName);
					skillAttestation = getAssociateDetails(skillAttestation, distinguishedName);
					skillAttestation.setCreatedDate(new Date());
					if(!skillAttestationService.existsByAssociateId(skillAttestation.getAssociateId())) {
						skillAttestationService.addSkillAttestation(skillAttestation);
					}
					skillAttestationList.add(getAssociateDetails(skillAttestation, distinguishedName));
				}
				}
			}else{
				throw new Exception("Invalid User");
			}
			logger.info("supervisorAssociateMappingList  ::  "+skillAttestationList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return skillAttestationList;
	}
	
	public SkillAttestation getAssociateDetails(SkillAttestation skillAttestation, String distinguishedName) throws NamingException {
		//try {
			LdapContext ctx = getLdapContext();
			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String[] attrIDs = { "sAMAccountName", "cn", "description", "l", "co"};
			constraints.setReturningAttributes(attrIDs);
			NamingEnumeration answer = ctx.search(baseDN, "distinguishedName="+ distinguishedName, constraints);
			if (answer.hasMore()) {
				Attributes attrs = ((SearchResult) answer.next()).getAttributes();
				String associateId = "";
				String associateName = "";
				String description = "";
				String location = "";
				String country = "";
				if(attrs.get("sAMAccountName") != null) {
					associateId = String.valueOf(attrs.get("sAMAccountName").get(0));
				}
				skillAttestation.setAssociateId(associateId);
				if(attrs.get("cn") != null) {
					associateName = String.valueOf(attrs.get("cn").get(0));
				}
				skillAttestation.setAssociateName(associateName);
				if(attrs.get("description") != null) {
					description = String.valueOf(attrs.get("description").get(0));
				}
				skillAttestation.setGrade(description);
				if(attrs.get("l") != null) {
					location = String.valueOf(attrs.get("l").get(0));
				}
				skillAttestation.setLocation(location);
				if(attrs.get("co") != null) {
					country = String.valueOf(attrs.get("co").get(0));
				}
				skillAttestation.setRegion(country);
				
			}

		/*} catch (Exception ex) {
			ex.printStackTrace();
		}*/
		return skillAttestation;
	}
	
	/*public User fetchLDAPUser(String name) {
		User user = new User();
		try {
			user.setUsername(name);
			Filter filter = new EqualsFilter(loginAttr, name);
			LdapTemplate ldapTemplate = getLdapTemplate();
			user = (User) ldapTemplate.search(baseDN, filter.encode(), new UserAttributesMapper());
			user.setUsername(name);
		} catch (Exception e) {
			user = new User();
		}
		return user;
	}

	@SuppressWarnings("rawtypes")
	private class UserAttributesMapper implements AttributesMapper {
		public User mapFromAttributes(Attributes attrs) throws NamingException {
			User user = new User();
			user.setFname(attrs.get(userGivenName).get().toString());
			user.setLname(attrs.get(userSN).get().toString());
			user.setEmail(attrs.get(mail).get().toString());
			user.setDesignation(attrs.get("title").get().toString());
			user.setActive(true);
			user.setRole("Associate");
			return user;
		}
	}*/

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

	@Override
	public List<SkillAttestation> getSupervisorDetails(List<String> supervisorList) throws NamingException {
		List<SkillAttestation> skillAttestationList = new ArrayList<SkillAttestation>();
		//try {
			for(String userName : supervisorList) {
			LdapContext ctx = getLdapContext();
			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String[] attrIDs = { "sAMAccountName", "cn", "directReports"};
			constraints.setReturningAttributes(attrIDs);
			NamingEnumeration answer = ctx.search(baseDN, "sAMAccountName="+ userName, constraints);
			String supervisorId = "";
			String supervisorName = "";
			if (answer.hasMore()) {
				Attributes attrs = ((SearchResult) answer.next()).getAttributes();
				supervisorId = attrs.get("sAMAccountName").get(0).toString();
				supervisorName = attrs.get("cn").get(0).toString();
				Attribute directReports = attrs.get("directReports");
				if(directReports != null) {
				NamingEnumeration e = directReports.getAll();
				while (e.hasMore()) {
					SkillAttestation skillAttestation = new SkillAttestation();
					skillAttestation.setSupervisorId(supervisorId);
					skillAttestation.setSupervisorName(supervisorName);
					String distinguishedName = e.next().toString();
					logger.info("distinguishedName  ::  " + distinguishedName);
					skillAttestation = getAssociateDetails(skillAttestation, distinguishedName);
					skillAttestation.setCreatedDate(new Date());
					if(!skillAttestationService.existsByAssociateId(skillAttestation.getAssociateId())) {
						skillAttestationService.addSkillAttestation(skillAttestation);
					}
					skillAttestationList.add(getAssociateDetails(skillAttestation, distinguishedName));
				}
				}
			/*}else{
				throw new Exception("Invalid User");
			}*/
			}
			logger.info("supervisorAssociateMappingList  ::  "+skillAttestationList);
		/*} catch (Exception ex) {
			ex.printStackTrace();
		}*/
		
	}
			return skillAttestationList;
}
}
