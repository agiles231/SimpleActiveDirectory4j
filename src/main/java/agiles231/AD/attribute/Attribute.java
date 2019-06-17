package agiles231.AD.attribute;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import agiles231.AD.ActiveDirectoryContext;
import agiles231.AD.exception.AttributeNotOrderedException;
import agiles231.AD.util.NamingEnumerationListConverter;

public class Attribute {

	private final javax.naming.directory.Attribute attribute;
	
	public Attribute(javax.naming.directory.Attribute attribute) {
		this.attribute = attribute;
	}
	
	public Attribute(String name) {
		this.attribute = new javax.naming.directory.BasicAttribute(name);
	}

	public Attribute(String name, List<Object> objects) {
		this.attribute = new javax.naming.directory.BasicAttribute(name);
		addAll(objects);
	}
	
	public Attribute(String name, boolean ordered) {
		this.attribute = new javax.naming.directory.BasicAttribute(name, ordered);
	}
	public Attribute(String name, boolean ordered, List<Object> objects) {
		this(name, ordered);
		addAll(objects);
	}
	
	public Object get() throws NamingException {
		return attribute.get();
	}

	public List<Object> getAll() throws NamingException {
		return NamingEnumerationListConverter.listifyNamingEnumeration((NamingEnumeration<Object>)attribute.getAll());
	}

	public Object getNth(int i) throws NamingException, AttributeNotOrderedException {
		if (isOrdered()) {
			return attribute.get(i);
		} else {
			throw new AttributeNotOrderedException("Cannot retrieve Nth element from an unordered attribute");
		}
	}

	public int size() {
		return attribute.size();
	}

	public String getID() {
		return attribute.getID();
	}

	public boolean contains(Object attrVal) {
		return attribute.contains(attrVal);
	}

	public boolean add(Object attrVal) {
		return attribute.add(attrVal);
	}
	
	public List<Boolean> addAll(List<Object> objects) {
		List<Boolean> success = new ArrayList<>(objects.size());
		for (Object obj : objects) {
			success.add(this.attribute.add(obj));
		}
		return success;
	}

	public boolean remove(Object attrval) {
		return attribute.remove(attrval);
	}

	public void clear() {
		attribute.clear();
	}

	public ActiveDirectoryContext getAttributeSyntaxDefinition() throws NamingException {
		return new ActiveDirectoryContext(attribute.getAttributeSyntaxDefinition());
	}

	public ActiveDirectoryContext getAttributeDefinition() throws NamingException {
		return new ActiveDirectoryContext(attribute.getAttributeDefinition());
	}

	public boolean isOrdered() {
		return attribute.isOrdered();
	}
	
	
}
