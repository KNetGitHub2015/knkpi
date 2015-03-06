function getSavedSearch(datain) {
    // This script requires that saved searches with 1000+ records to be sorted by internalId (Asc)
    nlapiLogExecution('DEBUG', 'Gathering data from saved search: ', datain.internalId)
    var o = new Object();
    var filters = new Array();
    var scriptStartTime = new Date().getTime();
    var limitReached = false;
    var parsedLastInternalId = parseInt(datain.lastInternalId);
    var lastInternalId = parsedLastInternalId > 0 ? parsedLastInternalId : 0;
    var joiner = datain.joiner

    if (!joiner) {
        joiner = null
    }

    nlapiLogExecution('DEBUG', 'Search Parameters:', "Date Field Id: " + datain.dateFieldId + ", Joined Record: " + joiner + ", Date Range: " + datain.dateFilter)

    var response = [];

    do {
        filters[0] = new nlobjSearchFilter(datain.dateFieldId, joiner, 'within', datain.dateFilter);

        if (lastInternalId > 0) {
            filters[1] = new nlobjSearchFilter('internalIdNumber', null, 'greaterthan', lastInternalId);
        }

        var pullStartTime = new Date().getTime();

        var search = nlapiLoadSearch(null, datain.internalId);
        search.addFilters(filters);
        var resultSet = search.runSearch();

        var resultSlice = resultSet.getResults(0, 1000);
        for (var rs in resultSlice) {
            response.push(resultSlice[rs]);
            lastInternalId = resultSlice[rs].id;
        }

        var exit = false;
        var currentTime = new Date().getTime();
        var bufferTime = currentTime - pullStartTime;
        if ((currentTime - scriptStartTime) + bufferTime > 240000) { //4 minutes
            limitReached = true;
            exit = true;
        }
        nlapiLogExecution('DEBUG', 'Completed processing up to: ', lastInternalId)
    } while (resultSlice.length >= 1000 && !exit);

    o['success'] = true;
    o['recordCount'] = response.length;
    o['lastInternalId'] = lastInternalId;
    o['limitReached'] = limitReached;
    o['recordList'] = response;

    return o;
}