package com.knowledgenet.knkpi

import grails.transaction.Transactional
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method

@Transactional
class NetSuiteAccessorService {
    def grailsApplication

    private static final String AUTH_HEADER = 'Authorization'

    def getSavedSearch(String endPoint, String joiner, String dateFieldId, String dateFilter, String repFieldId, String repId) {

        def result = null
        log.info("Field Values - endPoint: ${endPoint}, joiner: ${joiner}, dateFieldId: ${dateFieldId}, dateFilter: ${dateFilter}")
        HTTPBuilder httpBuilder = new HTTPBuilder(Setting.findByBaseUrl(NetSuiteUtil.BASE_URL)?.baseUrl) // < lol
        try {
            httpBuilder.request(Method.POST) {
                uri.path = "${Setting.findByBaseUrl(NetSuiteUtil.BASE_URL)?.searchesUrl}"
                headers."${AUTH_HEADER}" = getAuthHeader()
                requestContentType = ContentType.JSON
                body = [internalId: endPoint, dateFieldId: dateFieldId, joiner: joiner, dateFilter: dateFilter, repFieldId: repFieldId, repFilter: repId]

                response.success = { resp, json ->
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

    def getEmployees(String repId) {
        def result = null
        log.info("Getting Employees...")
        HTTPBuilder httpBuilder = new HTTPBuilder(Setting.findByBaseUrl(NetSuiteUtil.BASE_URL)?.baseUrl) // < lol
        try {
            httpBuilder.request(Method.POST) {
                uri.path = "${Setting.findByBaseUrl(NetSuiteUtil.BASE_URL)?.employeesUrl}"
                headers."${AUTH_HEADER}" = getAuthHeader()
                requestContentType = ContentType.JSON
                body = [repId: repId]

                response.success = { resp, json ->
                    switch (resp.status) {
                        case 200:
                            result = json.recordList
                            log.info("Successfully fetched Employees. ${json.recordCount} records pulled.")
                            break
                        default:
                            log.info("Status code ${resp.status} was returned.")
                            throw new Exception()
                            break
                    }
                }
            }
        } catch (Exception e) {
            log.error("Unable to pull Employees")
        }

        return result
    }

    String getAuthHeader() {
        return Setting.findByBaseUrl(NetSuiteUtil.BASE_URL)?.auth
    }
}
