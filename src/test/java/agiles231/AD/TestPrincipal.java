package agiles231.AD;

import java.util.LinkedList;
import java.util.List;

import javax.naming.InvalidNameException;

import agiles231.AD.authentication.Principal;

public class TestPrincipal {
	
	public static void main(String[] args) throws InvalidNameException {
		List<String> cn = new LinkedList<String>();
		List<String> ou = new LinkedList<String>();
		List<String> dc = new LinkedList<String>();
		cn.add("a_cn");
		cn.add("b_cn");
		cn.add("c_cn");
		ou.add("a_ou");
		ou.add("b_ou");
		ou.add("c_ou");
		dc.add("a_dc");
		dc.add("b_dc");
		dc.add("c_dc");
		Principal principal = new Principal(cn, ou, dc);
		assert principal.toString().equals("cn=a_cn,cn=b_cn,cn=c_cn,ou=a_ou,ou=b_ou,ou=c_ou,dc=a_dc,dc=b_dc,dc=c_dc") : "FAILED: " + principal.toString();
	}

}
