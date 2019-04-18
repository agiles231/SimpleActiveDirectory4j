package agiles231.AD.naming;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;

public class ActiveDirectoryName {
	
	private final String name;
	private final LdapName ldapName;
	private final List<String> cn;
	private final List<String> ou;
	private final List<String> dc;
	public ActiveDirectoryName(String name) throws InvalidNameException {
		this.ldapName =  new LdapName(name); // use this so that validation is performed
		String[] parts = name.split(",");
		List<String> cn = new LinkedList<>();
		List<String> ou = new LinkedList<>();
		List<String> dc = new LinkedList<>();
		for (String part : parts) {
			if (part.toLowerCase().contains("cn=")) {
				cn.add(part.toLowerCase().replace("cn=", ""));
			}
			if (part.toLowerCase().contains("ou=")) {
				ou.add(part.toLowerCase().replace("ou=", ""));
			}
			if (part.toLowerCase().contains("dc=")) {
				dc.add(part.toLowerCase().replace("dc=", ""));
			}
		}
		this.cn = cn;
		this.ou = ou;
		this.dc = dc;
		this.name = getNameFromParts(cn, ou, dc);
	}
	public ActiveDirectoryName(List<String> cn, List<String> ou, List<String> dc) throws InvalidNameException {
		this.cn = cn;
		this.ou = ou;
		this.dc = dc;
		this.name = getNameFromParts(cn, ou, dc);
		this.ldapName =  new LdapName(name); // use this so that validation is performed
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	/**
	 * Returns the cn values
	 * @return
	 */
	public List<String> getCn() {
		return cn;
	}
	/**
	 * Returns the ou values
	 * @return
	 */
	public List<String> getOu() {
		return ou;
	}
	/**
	 * Returns the dc values
	 * @return
	 */
	public List<String> getDc() {
		return dc;
	}
	
	private final String getNameFromParts(List<String> cn, List<String> ou, List<String> dc) {
		
		List<String> cnName = cn.stream().filter(e -> e != null).map(n -> "cn=" + n).collect(Collectors.toList());
		List<String> ouName = ou.stream().filter(e -> e != null).map(n -> "ou=" + n).collect(Collectors.toList());
		List<String> dcName = dc.stream().filter(e -> e != null).map(n -> "dc=" + n).collect(Collectors.toList());
		List<String> allName = new ArrayList<>(cnName.size() + ouName.size() + dcName.size());
		allName.addAll(cnName);
		allName.addAll(ouName);
		allName.addAll(dcName);
		return String.join(",", allName);
	}

}
