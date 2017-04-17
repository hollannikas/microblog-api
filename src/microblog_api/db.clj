(ns microblog-api.db
  (:import com.mchange.v2.c3p0.ComboPooledDataSource)
  (:require [clojure.java.jdbc :as j]
            [jdbc.pool.c3p0 :as pool]))

(def spec
  (pool/make-datasource-spec
    {:classname   "org.h2.Driver"
     :subprotocol "h2"
     :subname     "mem:documents"
     :user        ""
     :password    ""}))

(j/db-do-commands spec
                  (j/create-table-ddl :posts
                                      [[:id "varchar(256)" "primary key"]
                                       [:message "varchar(140)"]
                                       [:date :varchar]
                                       [:user "varchar(100)"]]))