package org.sbbs.base.webapp.search;

public enum SearchOperator {
    /**
     * com.googlecode.genericdao.search.Filter中的操作符定义
     * 
     * public static final int OP_EQUAL = 0; 
     * public static final int OP_NOT_EQUAL = 1; 
     * public static final int OP_LESS_THAN = 2; 
     * public static final int OP_GREATER_THAN = 3; 
     * public static final int OP_LESS_OR_EQUAL = 4;
     * public static final int OP_GREATER_OR_EQUAL = 5; 
     * public static final int OP_LIKE = 6; 
     * public static final int OP_ILIKE = 7; 
     * public static final int OP_IN = 8; 
     * public static final int OP_NOT_IN = 9; 
     * public static final int OP_NULL = 10; 
     * public static final int OP_NOT_NULL = 11; 
     * public static final int OP_EMPTY = 12; 
     * public static final int OP_NOT_EMPTY = 13; 
     * public static final int OP_AND = 100; 
     * public static final int OP_OR = 101; 
     * public static final int OP_NOT = 102; 
     * public static final int OP_SOME = 200; 
     * public static final int OP_ALL = 201;
     * public static final int OP_NONE = 202; 
     * public static final int OP_CUSTOM = 999;
     */

    eq( "eq", "equal",0 ),
    ne( "ne", "not equal" ,1),
    lt( "lt", "less" ,2),
    gt( "gt", "greater",3 ),
    le( "le", "less or equal",4 ),
    ge( "ge", "greater or equal",5 ),
    like( "like", "contains" ,7),
    cn( "cn", "contains" ,70),
    bw( "bw", "begins with" ,71),
    ew( "ew", "ends with" ,72),

    in( "in", "is in" ,8)
    /*,
    ni( "ni", "is not in" ),

    bn( "bn", "does not begin with" ),
    en( "en", "does not end with" ),
    nc( "nc", "does not contain" ),*/
    
    ;
    SearchOperator( String opr, String discription ,int intValue) {

        this.opr = opr;
        this.discription = discription;
        this.intValue=intValue;
    }

    private int intValue;

    private String opr;

    private String discription;

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue( int intValue ) {
        this.intValue = intValue;
    }

    public String getOpr() {
        return opr;
    }

    public void setOpr( String opr ) {
        this.opr = opr;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription( String discription ) {
        this.discription = discription;
    }

    public static SearchOperator getByName() {

        return null;
    }

    public static void main( String[] args ) {
        SearchOperator opr;

        opr = SearchOperator.valueOf( "like".toLowerCase() );

        System.out.println( opr.toString() );
    }
}
