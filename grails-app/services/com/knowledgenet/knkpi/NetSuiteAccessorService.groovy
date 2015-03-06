package com.knowledgenet.knkpi

import grails.transaction.Transactional
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method

@Transactional
class NetSuiteAccessorService {
    def grailsApplication

    private static final String AUTH_HEADER = 'Authorization'

    def getSavedSearch(String endPoint, String joiner, String dateFieldId, String dateFilter) {

        def result = null
        log.info("Field Values - endPoint: ${endPoint}, joiner: ${joiner}, dateFieldId: ${dateFieldId}, dateFilter: ${dateFilter}")
        HTTPBuilder httpBuilder = new HTTPBuilder(grailsApplication.config.grails.netSuite.baseUrl)
        try {
            httpBuilder.request(Method.POST) {
                uri.path = "${grailsApplication.config.grails.netSuite.restletUrl}"
                headers."${AUTH_HEADER}" = getAuthHeader()
                requestContentType = ContentType.JSON
                body = [internalId: endPoint, dateFieldId: dateFieldId, joiner: joiner, dateFilter: dateFilter]

                response.success = { resp, json ->
                    assert resp.status == 200

                    switch (resp.status) {
                        case 200:
                            result = json.recordList
                            log.info("Successfully fetched Saved Search ${endPoint}. ${json.recordCount} records pulled.")
                            break
                        default:
                            log.info("Status code ${resp.status} was returned.")
                            throw new Exception()
                            break
                    }
                }
            }
        } catch (Exception e) {
            log.error("Unable to pull Saved Search: ${e}")
        }

        return result
    }

    String getAuthHeader() {
        return grailsApplication.config.grails.netSuite.auth
    }
}
