package agiles231.AD.modification;

import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.LdapName;

public class ModificationItemBuilder {

	public ModificationItemBuilder() {
		
	}
	
	public ModificationItem buildRemoveAttributeModification(LdapName name, String attributeName) {
		Attribute attribute = new BasicAttribute(attributeName);
		return new ModificationItem(DirContext.REMOVE_ATTRIBUTE, attribute);
	}
	public ModificationItem buildAddAttributeModification(LdapName name, String attributeName, Object value) {
		Attribute attribute = new BasicAttribute(attributeName, value);
		return new ModificationItem(DirContext.ADD_ATTRIBUTE, attribute);
	}
	public ModificationItem buildReplaceAttributeModification(LdapName name, String attributeName, Object value) {
		Attribute attribute = new BasicAttribute(attributeName, value);
		return new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attribute);
	}
}
