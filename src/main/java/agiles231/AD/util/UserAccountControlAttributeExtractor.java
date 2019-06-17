package agiles231.AD.util;

import java.util.List;
import java.util.Optional;

import javax.naming.NamingException;

import agiles231.AD.attribute.Attribute;
import agiles231.AD.attribute.UserAccountControlAttribute;

public class UserAccountControlAttributeExtractor {

	public static Optional<UserAccountControlAttribute> extractUserAccountControlAttribute(List<Attribute> attributes) throws NumberFormatException, NamingException {
		Optional<UserAccountControlAttribute> option = Optional.empty();
		for (Attribute attribute : attributes) {
			if (attribute.getID().equals("userAccountControl")) {
				option = Optional.of(new UserAccountControlAttribute(Integer.parseInt((String)attribute.get())));
			}
		}
		return option;
	}
}
