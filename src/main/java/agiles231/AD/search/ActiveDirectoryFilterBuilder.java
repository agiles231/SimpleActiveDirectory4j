package agiles231.AD.search;

import java.util.Arrays;

public class ActiveDirectoryFilterBuilder {
	private static final String SEARCH_WILDCARD = "*";

	public ActiveDirectoryFilter buildPresenceFilter(String attribute) { return new AttributeActiveDirectoryFilter(attribute, ActiveDirectoryFilterRelationalOperator.EQUALS, SEARCH_WILDCARD); }
	public ActiveDirectoryFilter buildEqualsFilter(String attribute, String value) { return new AttributeActiveDirectoryFilter(attribute, ActiveDirectoryFilterRelationalOperator.EQUALS, value); }
	public ActiveDirectoryFilter buildGreaterThanFilter(String attribute, String value) { return new AttributeActiveDirectoryFilter(attribute, ActiveDirectoryFilterRelationalOperator.GREATER_THAN, value); }
	public ActiveDirectoryFilter buildLessThanFilter(String attribute, String value) { return new AttributeActiveDirectoryFilter(attribute, ActiveDirectoryFilterRelationalOperator.LESS_THAN, value); }
	public ActiveDirectoryFilter buildGreaterThanOrEqualFilter(String attribute, String value) { return new AttributeActiveDirectoryFilter(attribute, ActiveDirectoryFilterRelationalOperator.GREATER_THAN_OR_EQUAL, value); }
	public ActiveDirectoryFilter buildLessThanOrEqualFilter(String attribute, String value) { return new AttributeActiveDirectoryFilter(attribute, ActiveDirectoryFilterRelationalOperator.LESS_THAN_OR_EQUAL, value); }

	public ActiveDirectoryFilter buildAndFilter(ActiveDirectoryFilter... filters) { return new CompositeActiveDirectoryFilter(ActiveDirectoryFilterLogicalOperator.AND, Arrays.asList(filters)); }
	public ActiveDirectoryFilter buildOrFilter(ActiveDirectoryFilter... filters) { return new CompositeActiveDirectoryFilter( ActiveDirectoryFilterLogicalOperator.OR, Arrays.asList(filters)); }
	public ActiveDirectoryFilter buildNotFilter(ActiveDirectoryFilter... filters) { return new CompositeActiveDirectoryFilter(ActiveDirectoryFilterLogicalOperator.NOT, Arrays.asList(filters)); }
}
