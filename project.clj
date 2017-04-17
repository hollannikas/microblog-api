(defproject microblog-api "0.1.0-SNAPSHOT"
  :description "REST api for microblogs"
  :url "https://github.com/hollannikas/microblog-api.git"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.2"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-json "0.4.0"]
                 [clojure.jdbc/clojure.jdbc-c3p0 "0.3.1"]
                 [org.clojure/java.jdbc "0.6.1"]
                 [com.h2database/h2 "1.4.194"]
                 [ring-cors "0.1.9"]]
  :plugins [[lein-ring "0.11.0"]]
  :ring {:handler microblog-api.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
