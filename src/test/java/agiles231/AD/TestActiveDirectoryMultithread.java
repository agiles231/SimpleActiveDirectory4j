package agiles231.AD;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import javax.naming.NamingException;
import javax.naming.directory.SearchControls;

import agiles231.AD.attribute.Attribute;
import agiles231.AD.attribute.UserAccountControlAttribute;
import agiles231.AD.authentication.ActiveDirectoryAuthenticationMethods;
import agiles231.AD.authentication.Principal;
import agiles231.AD.naming.ActiveDirectoryName;
import agiles231.AD.search.ActiveDirectoryFilter;
import agiles231.AD.search.ActiveDirectoryFilterBuilder;
import agiles231.AD.search.SearchResult;
import agiles231.AD.util.UserAccountControlAttributeExtractor;

public class TestActiveDirectoryMultithread {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ActiveDirectoryContextProvider provider = new ActiveDirectoryContextProvider(Optional.empty());
		int numThreads = 10;
		String providerUrl = args[0];
		String principalIn = args[1];
		String password = args[2];
		String searchBase = args[3];
		String[] searchVals = args[4].split(",");
		try {
			Principal principal = new Principal(principalIn);
			List<ActiveDirectoryContext> contexts = new LinkedList<>();
			for (int i = 0; i < numThreads; i++) {
				contexts.add(provider.getActiveDirectoryContext(providerUrl, Optional.of(ActiveDirectoryAuthenticationMethods.SIMPLE)
					, principal, password));
			}
			List<Future<List<SearchResult>>> futures = new LinkedList<>();
			ExecutorService service = Executors.newFixedThreadPool(numThreads);
			for (int i = 0; i < numThreads; i++) {
				ActiveDirectoryContext context = contexts.get(i);
				futures.add(service.submit(new LookupUserTask(context, new ActiveDirectoryName(searchBase), searchVals[i])));
			}
			List<SearchResult> results = new LinkedList<>();
			for (Future<List<SearchResult>> future : futures) {
				while (!future.isDone()) {
					Thread.sleep(20l);
				}
				results.addAll(future.get());
			}
			for (SearchResult result : results) {
				System.out.println("--------------------------------------------------------------------------------------");
				System.out.println("Name : " + result.getName());
				List<Attribute> attributes = result.getAttributes();
				Optional<UserAccountControlAttribute> userAccountControl = UserAccountControlAttributeExtractor.extractUserAccountControlAttribute(attributes);
				userAccountControl.ifPresent(u -> System.out.println(u));
				for (Attribute attr : attributes) {
					System.out.println(String.format("ATTRIBUTE_NAME :: %1$32s :: VALUES :: %2$s", attr.getID(), attr.getAll().stream().map(v -> v.getClass() + ";; " + v.toString()).collect(Collectors.toList())));
				}
			}
			service.shutdown();
		} catch (NamingException e) {
			System.out.println("FAILED: " + e.getMessage());
		}
	}
}
class LookupUserTask implements Callable<List<SearchResult>> {
	
	private final ActiveDirectoryContext context;
	private final ActiveDirectoryName name;
	private final String upn;
	public LookupUserTask(ActiveDirectoryContext context, ActiveDirectoryName name, String upn) {
		this.context = context;
		this.name = name;
		this.upn = upn;
	}

	@Override
	public List<SearchResult> call() throws Exception {
		ActiveDirectoryFilterBuilder builder = new ActiveDirectoryFilterBuilder();
		ActiveDirectoryFilter filter = builder.buildEqualsFilter("userPrincipalName", upn);
		SearchControls controls = new SearchControls();
		String returnedAtts[]={"*", "+" }; //"sn", "ObjectClass", "givenName", "samAccountName"};
		controls.setReturningAttributes(returnedAtts);
		controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		List<SearchResult> results = context.search(name
				, filter, controls);
		return results;
	}
	
}
