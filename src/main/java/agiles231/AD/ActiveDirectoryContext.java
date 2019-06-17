package agiles231.AD;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;

import agiles231.AD.naming.ActiveDirectoryName;
import agiles231.AD.search.ActiveDirectoryFilter;
import agiles231.AD.search.SearchResult;
import agiles231.AD.util.AttributesListConverter;
import agiles231.AD.util.NamingEnumerationListConverter;

/***
 * Wrapper around JNDI DirContext. DirContext provides
 * too many overloaded methods and uses strange types
 * to accomplish some things. This class has less
 * overloaded methods, more precies method names in
 * the context of ActiveDirectory, and uses more standard
 * types.
 * @author anng
 *
 */
public class ActiveDirectoryContext {
	private final DirContext context;

	public ActiveDirectoryContext(DirContext context) {
		this.context = context;
	}
	
	public List<agiles231.AD.attribute.Attribute> getAttributes(ActiveDirectoryName name) throws NamingException {
		return AttributesListConverter.convertToList(context.getAttributes(name.getName()));
	}
	
	public ActiveDirectoryContext getSchema(ActiveDirectoryName name) throws NamingException {
		return new ActiveDirectoryContext(context.getSchema(name.getName()));
	}
	
	/**
	 * Create the object identified by name. If already exists, exception is thrown.
	 * Object must serializable because this context may be remote, and the object
	 * would need to be serialized to be sent to it.
	 * @param name
	 * @param object
	 * @throws NamingException
	 */
	public synchronized void addObject(ActiveDirectoryName name, Serializable object) throws NamingException {
		context.bind(name.getName(), object);
	}

	/**
	 * Create the object identified by name, and add attributes to it.
	 * If already exists, exception is thrown.
	 * Object must serializable because this context may be remote, and the object
	 * would need to be serialized to be sent to it.
	 * @param name
	 * @param object
	 * @throws NamingException
	 */
	public synchronized void addObjectWithAttributes(ActiveDirectoryName name, Serializable object, List<agiles231.AD.attribute.Attribute> attributes) throws NamingException {
		Attributes attrs = AttributesListConverter.convertToAttributes(attributes);
		context.bind(name.getName(), object, attrs);
	}
	
	/**
	 * Create the object identified by name.
	 * If already exists, the existing item is replaced.
	 * Object must serializable because this context may be remote, and the object
	 * would need to be serialized to be sent to it.
	 * @param name
	 * @param object
	 * @throws NamingException
	 */
	public synchronized void addOrReplaceObject(ActiveDirectoryName name, Serializable object) throws NamingException {
		context.rebind(name.getName(), object);
	}
	
	/**
	 * Create the object identified by name, and add attributes to it.
	 * If already exists, the existing item is replaced.
	 * Object must serializable because this context may be remote, and the object
	 * would need to be serialized to be sent to it.
	 * @param name
	 * @param object
	 * @throws NamingException
	 */
	public synchronized void addOrReplaceObjectWithAttributes(ActiveDirectoryName name, Serializable object, List<agiles231.AD.attribute.Attribute> attributes) throws NamingException {
		Attributes attrs = AttributesListConverter.convertToAttributes(attributes);
		context.rebind(name.getName(), object, attrs);
	}
	
	/**
	 * Remove the object identified by name
	 * @param name
	 * @throws NamingException
	 */
	public synchronized void removeObject(ActiveDirectoryName name) throws NamingException {
		context.unbind(name.getName());
	}

	/**
	 * Search for objects that meet filter within name, with cons governing search features
	 * such as returned attributes and where to begin search relative to name.
	 * @param name
	 * @param filter
	 * @param cons
	 * @return
	 * @throws NamingException
	 */
	public synchronized List<SearchResult> search(ActiveDirectoryName name, ActiveDirectoryFilter filter, SearchControls cons) throws NamingException {
		List<javax.naming.directory.SearchResult> results = NamingEnumerationListConverter.listifyNamingEnumeration(context.search(name.getName(), filter.toLdapString(), cons));
		return results.stream().map(r -> new SearchResult(r)).collect(Collectors.toList());
	}
	
	/**
	 * Search for objects that have attributes matching parameter attributes
	 * @param name
	 * @param filter
	 * @param cons
	 * @return
	 * @throws NamingException
	 */
	public synchronized List<SearchResult> search(ActiveDirectoryName name, List<agiles231.AD.attribute.Attribute> attributes) throws NamingException {
		Attributes attrs = AttributesListConverter.convertToAttributes(attributes);
		List<javax.naming.directory.SearchResult> results = NamingEnumerationListConverter.listifyNamingEnumeration(context.search(name.getName(), attrs));
		return results.stream().map(r -> new SearchResult(r)).collect(Collectors.toList());
	}

	/**
	 * Search for objects that have attributes matching parameter attributes, returning only
	 * the attributes in returnValues
	 * @param name
	 * @param filter
	 * @param cons
	 * @return
	 * @throws NamingException
	 */
	public synchronized List<SearchResult> search(ActiveDirectoryName name, List<agiles231.AD.attribute.Attribute> attributes, String[] returnValues) throws NamingException {
		Attributes attrs = AttributesListConverter.convertToAttributes(attributes);
		List<javax.naming.directory.SearchResult> results = NamingEnumerationListConverter.listifyNamingEnumeration(context.search(name.getName(), attrs, returnValues));
		return results.stream().map(r -> new SearchResult(r)).collect(Collectors.toList());
	}
	
	/**
	 * Perform modifications to name. Modifications may be adding, removing or replacement
	 * of attributes.
	 * @param name
	 * @param mods
	 * @throws NamingException
	 */
	public synchronized void modify(ActiveDirectoryName name, ModificationItem[] mods) throws NamingException {
		context.modifyAttributes(name.getName(), mods);
	}
	
	/**
	 * Close this context
	 * @throws NamingException
	 */
	public synchronized void close() throws NamingException {
		context.close();
	}
	
}
