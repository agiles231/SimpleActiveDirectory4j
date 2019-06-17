package agiles231.AD.util;

import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

public class NamingEnumerationListConverter {
	public static <T> List<T> listifyNamingEnumeration(NamingEnumeration<T> enumeration) throws NamingException {
		List<T> ret = new LinkedList<T>();
		while (enumeration.hasMoreElements()) {
			T result = enumeration.next();
			ret.add(result);
		}
		return ret;
	}
}
