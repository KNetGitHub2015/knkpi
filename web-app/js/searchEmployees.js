function getEmployees(datain) {
    nlapiLogExecution('DEBUG', 'Attempting to fetch Employees', null);
    var o = new Object();

    var columns = new Array();
    columns[0] = new nlobjSearchColumn('internalid');
    columns[1] = new nlobjSearchColumn('entityid');
    columns[2] = new nlobjSearchColumn('title');
    columns[3] = new nlobjSearchColumn('supervisor');
    columns[4] = new nlobjSearchColumn('hiredate');
    columns[5] = new nlobjSearchColumn('birthdate');

    var search = nlapiLoadSearch(null, 'customsearch_kpiemployees');
    var results = search.runSearch();

    o['recordList'] = results.getResults(0,1000);
    o['recordCount'] = results.length;
    o['success'] = true;
    o['errorMessage'] = null;

    return o;
}


//TODO: Need to pass department in so we can do this for any department. below is fail log if they dont pass a department

//var error = "department parameter required.";
//nlapiLogExecution('DEBUG', error, department);
//
//o['recordList'] = null;
//o['recordCOunt'] = 0;
//o['success'] = false;
//o['errorMessage'] = error;