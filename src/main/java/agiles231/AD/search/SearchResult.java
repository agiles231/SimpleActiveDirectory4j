package agiles231.AD.search;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import agiles231.AD.util.AttributesListConverter;

public class SearchResult {

	private final javax.naming.directory.SearchResult result;
	public SearchResult(javax.naming.directory.SearchResult result) {
		this.result = result;
	}
	public int hashCode() {
		return result.hashCode();
	}
	public boolean equals(Object obj) {
		return result.equals(obj);
	}
	public String getName() {
		return result.getName();
	}
	public String getClassName() {
		return result.getClassName();
	}
	public void setName(String name) {
		result.setName(name);
	}
	public void setClassName(String name) {
		result.setClassName(name);
	}
	public List<agiles231.AD.attribute.Attribute> getAttributes() throws NamingException {
		return AttributesListConverter.convertToList(result.getAttributes());
	}
	public Object getObject() {
		return result.getObject();
	}
	public void setAttributes(List<agiles231.AD.attribute.Attribute> attrs) throws NamingException {
		result.setAttributes(AttributesListConverter.convertToAttributes(attrs));
	}
	public boolean isRelative() {
		return result.isRelative();
	}
	public void setObject(Object obj) {
		result.setObject(obj);
	}
	public String toString() {
		return result.toString();
	}
	public void setRelative(boolean r) {
		result.setRelative(r);
	}
	public String getNameInNamespace() {
		return result.getNameInNamespace();
	}
	public void setNameInNamespace(String fullName) {
		result.setNameInNamespace(fullName);
	}
}
