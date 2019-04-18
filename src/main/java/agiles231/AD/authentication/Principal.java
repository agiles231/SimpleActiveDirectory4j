package agiles231.AD.authentication;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.naming.InvalidNameException;

import agiles231.AD.naming.ActiveDirectoryName;

public class Principal extends ActiveDirectoryName {

	public Principal(String name) throws InvalidNameException {
		super(name);
	}
	public Principal(List<String> cn, List<String> ou, List<String> dc)
			throws InvalidNameException {
		super(cn, ou, dc);
	}

	
}
