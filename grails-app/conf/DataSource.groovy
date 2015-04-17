dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    username = "eric"
    password = "eric5425"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
//    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
    flush.mode = 'manual' // OSIV session flush mode outside of transactional context
}

// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:mysql://localhost:3306/knkpi_dev"
            username = "eric"
            password = "eric5425"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:file:~/settingData/db;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    }

    production {
        dataSource {
            username = "KNetGitHub2015"
            password = "KN3t2015"
            pooled = true
            dbCreate = "update"
            driverClassName = "com.mysql.jdbc.Driver"
            url = "jdbc:mysql://aa1g9pcvrl2c191.cc2zevgvgvnc.us-west-1.rds.amazonaws.com:3306/ebdb?user=KNetGitHub2015&password=KN3t2015"
            dialect = org.hibernate.dialect.MySQL5InnoDBDialect
            properties {
                validationQuery = "SELECT 1"
                testOnBorrow = true
                testOnReturn = true
                testWhileIdle = true
                timeBetweenEvictionRunsMillis = 1800000
                numTestsPerEvictionRun = 3
                minEvictableIdleTimeMillis = 1800000
            }
        }
    }
//    production {
//        dataSource {
//            dbdir = "${System.properties['catalina.base']}/knkpidb/db"
//
//            dbCreate = "update"
//            url = "jdbc:h2:file:${dbdir};MVCC=TRUE;LOCK_TIMEOUT=10000"
//            pooled = true
//            username = "sa"
//            password = ""
//
//            properties {
//                maxActive = -1
//                minEvictableIdleTimeMillis=1800000
//                timeBetweenEvictionRunsMillis=1800000
//                numTestsPerEvictionRun=3
//                testOnBorrow=true
//                testWhileIdle=true
//                testOnReturn=true
//                validationQuery="SELECT 1"
//            }
//        }
//    }
}
