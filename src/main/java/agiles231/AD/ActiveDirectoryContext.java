package agiles231.AD;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import agiles231.AD.naming.ActiveDirectoryName;
import agiles231.AD.search.ActiveDirectoryFilter;

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
	public synchronized void addObjectWithAttributes(ActiveDirectoryName name, Serializable object, Attribute[] attributes) throws NamingException {
		Attributes attrs = getAttributesFromArray(attributes);
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
	public synchronized void addOrReplaceObjectWithAttributes(ActiveDirectoryName name, Serializable object, Attribute[] attributes) throws NamingException {
		Attributes attrs = getAttributesFromArray(attributes);
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
		return listifyResults(context.search(name.getName(), filter.toLdapString(), cons));
	}
	
	/**
	 * Search for objects that have attributes matching parameter attributes
	 * @param name
	 * @param filter
	 * @param cons
	 * @return
	 * @throws NamingException
	 */
	public synchronized List<SearchResult> search(ActiveDirectoryName name, Attribute[] attributes) throws NamingException {
		Attributes attrs = getAttributesFromArray(attributes);
		return listifyResults(context.search(name.getName(), attrs));
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
	public synchronized List<SearchResult> search(ActiveDirectoryName name, Attribute[] attributes, String[] returnValues) throws NamingException {
		Attributes attrs = getAttributesFromArray(attributes);
		return listifyResults(context.search(name.getName(), attrs, returnValues));
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
	
	private final List<SearchResult> listifyResults(NamingEnumeration<SearchResult> results) throws NamingException {
		List<SearchResult> ret = new LinkedList<SearchResult>();
		while (results.hasMoreElements()) {
			SearchResult result = results.next();
			ret.add(result);
		}
		return ret;
	}
	
	private final Attributes getAttributesFromArray(Attribute[] attributes) throws NamingException {
		Attributes attrs = new BasicAttributes();
		for (Attribute attribute : attributes) {
			attrs.put(attribute.getID(), attribute.get());
		}
		return attrs;
	}
}
