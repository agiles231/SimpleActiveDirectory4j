package agiles231.AD;

import java.security.Security;
import java.util.Hashtable;
import java.util.Optional;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import agiles231.AD.authentication.ActiveDirectoryAuthenticationMethods;
import agiles231.AD.authentication.Principal;

public class ActiveDirectoryContextProvider {

	DirContext ldapContext;
	Hashtable<String, String> ldapEnvironment;
	private final Optional<String> keystore;
	public ActiveDirectoryContextProvider(Optional<String> keystore) {
		this.keystore = keystore;
	}
	public synchronized ActiveDirectoryContext getActiveDirectoryContext(String providerUrl, Optional<String> authenticationMethod, Principal principal, String credentials) throws NamingException {
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		keystore.ifPresent(k -> System.setProperty("javax.net.ssl.trustStore", k));
		ldapEnvironment = new Hashtable<String, String>();
		ldapEnvironment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		ldapEnvironment.put(Context.PROVIDER_URL, providerUrl);
		ldapEnvironment.put(Context.REFERRAL, "ignore");
		ldapEnvironment.put(Context.SECURITY_AUTHENTICATION, authenticationMethod.orElse(ActiveDirectoryAuthenticationMethods.SIMPLE));
		ldapEnvironment.put(Context.SECURITY_PRINCIPAL, principal.getName());
		ldapEnvironment.put(Context.SECURITY_CREDENTIALS, credentials);
		ldapEnvironment.put(Context.SECURITY_PROTOCOL, "ssl");
		ldapContext = new InitialDirContext(ldapEnvironment);
		ActiveDirectoryContext context = new ActiveDirectoryContext(ldapContext);
		return context;
	}
}
