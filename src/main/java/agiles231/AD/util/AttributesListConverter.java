package agiles231.AD.util;

import java.util.List;
import java.util.stream.Collectors;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;


public class AttributesListConverter {

	public static List<agiles231.AD.attribute.Attribute> convertToList(Attributes attributes) throws NamingException {
		List<? extends Attribute> attrs =  NamingEnumerationListConverter.listifyNamingEnumeration(attributes.getAll());
		return attrs.stream().map(a -> new agiles231.AD.attribute.Attribute(a)).collect(Collectors.toList());
	}
	
	public static Attributes convertToAttributes(List<agiles231.AD.attribute.Attribute> attrs) throws NamingException {
		Attributes attributes = new BasicAttributes();
		for (agiles231.AD.attribute.Attribute attr : attrs) {
			Attribute attribute = new BasicAttribute(attr.getID(), attr.isOrdered());
			if (attr.isOrdered()) {
				List<Object> values = attr.getAll();
				for (Object value : values) {
					attribute.add(value);
				}
			} else {
				Object value = attr.get();
				attribute.add(value);
			}
			attributes.put(attribute);
		}
		return attributes;
	}
}
