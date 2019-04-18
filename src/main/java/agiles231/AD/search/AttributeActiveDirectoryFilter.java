package agiles231.AD.search;

public class AttributeActiveDirectoryFilter implements ActiveDirectoryFilter {
	
	String attribute;
	ActiveDirectoryFilterRelationalOperator operator;
	String value;
	
	public AttributeActiveDirectoryFilter(String attribute, ActiveDirectoryFilterRelationalOperator operator, String value) {
		super();
		this.attribute = attribute;
		this.operator = operator;
		this.value = value;
	}

	public String toLdapString() {
		return "(" + attribute + FilterRelationalOperatorStringifier.toString(operator) + value + ")";
	}

}
class FilterRelationalOperatorStringifier {
	private static String EQUALS = "=";
	private static String GREATER_THAN = ">";
	private static String LESS_THAN = "<";
	private static String GREATER_THAN_OR_EQUAL = ">=";
	private static String LESS_THAN_OR_EQUAL  = "<=";

	public static String toString(ActiveDirectoryFilterRelationalOperator op) {
		String val = (op == ActiveDirectoryFilterRelationalOperator.EQUALS) ? EQUALS: 
			(op == ActiveDirectoryFilterRelationalOperator.GREATER_THAN) ? GREATER_THAN :  
			(op == ActiveDirectoryFilterRelationalOperator.LESS_THAN) ? LESS_THAN :  
			(op == ActiveDirectoryFilterRelationalOperator.GREATER_THAN_OR_EQUAL) ? GREATER_THAN_OR_EQUAL : LESS_THAN_OR_EQUAL;
		return val;
	}
}
