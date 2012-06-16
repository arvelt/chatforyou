function checkinput( str ){
	
	if ( str.indexOf("<") != -1 ){ return false ; }
	if ( str.indexOf(">") != -1 ){ return false ; }
	if ( str.indexOf(",") != -1 ){ return false ; }
	if ( str.indexOf(".") != -1 ){ return false ; }
	if ( str.indexOf("?") != -1 ){ return false ; }
	if ( str.indexOf("/") != -1 ){ return false ; }
	if ( str.indexOf("\\") != -1 ){ return false ; }
	if ( str.indexOf("_") != -1 ){ return false ; }
	if ( str.indexOf("!") != -1 ){ return false ; }
	if ( str.indexOf("\"") != -1 ){ return false ; }
	if ( str.indexOf("#") != -1 ){ return false ; }
	if ( str.indexOf("$") != -1 ){ return false ; }
	if ( str.indexOf("'") != -1 ){ return false ; }
	if ( str.indexOf("(") != -1 ){ return false ; }
	if ( str.indexOf(")") != -1 ){ return false ; }
	if ( str.indexOf("=") != -1 ){ return false ; }
	if ( str.indexOf("-") != -1 ){ return false ; }
	if ( str.indexOf("^") != -1 ){ return false ; }
	if ( str.indexOf("~") != -1 ){ return false ; }
	if ( str.indexOf("\\") != -1 ){ return false ; }
}