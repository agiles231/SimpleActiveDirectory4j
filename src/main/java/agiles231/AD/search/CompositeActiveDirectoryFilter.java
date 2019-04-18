package agiles231.AD.search;

import java.util.List;
import java.util.stream.Collectors;

public class CompositeActiveDirectoryFilter implements ActiveDirectoryFilter {

	private final ActiveDirectoryFilterLogicalOperator operator;
	private final List<ActiveDirectoryFilter> subFilters;

	public CompositeActiveDirectoryFilter(ActiveDirectoryFilterLogicalOperator operator, List<ActiveDirectoryFilter> subFilters) {
		super();
		this.operator = operator;
		this.subFilters = subFilters;
	}

	public String toLdapString() {
		return "(" + FilterLogicalOperatorStringifier.toString(operator)
			+ String.join("", subFilters.stream().map(f -> f.toLdapString()).collect(Collectors.toList())) + ")";
	}
	
	
}

class FilterLogicalOperatorStringifier {
	private static String AND = "&";
	private static String OR = "|";
	private static String NOT = "!";

	public static String toString(ActiveDirectoryFilterLogicalOperator op) {
		String val = (op == ActiveDirectoryFilterLogicalOperator.AND) ? AND: 
			(op == ActiveDirectoryFilterLogicalOperator.OR) ? OR : NOT;
		return val;
	}
}