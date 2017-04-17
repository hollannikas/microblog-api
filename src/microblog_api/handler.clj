(ns microblog-api.handler
  (:use ring.util.response)
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.middleware.json :as middleware]
            [microblog-api.db :as db]
            [clojure.java.jdbc :as j])
  (:import (java.util UUID)))


(defn get-all-posts []
  (response
    (j/query db/spec
             ["select * from posts"])))

(defn get-post [id]
  (let [result (j/query db/spec ["select * from posts where id = ?" id])]
    (cond
      (empty? result) {:status 404}
      :else (response (first result)))))

(defn uuid [] (str (UUID/randomUUID)))

(defn create-new-post [post]
  (let [id (uuid)]
    (let [post (assoc post "id" id)]
      (j/insert! db/spec :posts post))
    (get-post id)))

(defn delete-post [id]
  (j/delete! db/spec :posts ["id=?" id])
  {:status 204})

(defroutes app-routes
           (context "/api/microblogs" []
             (defroutes microblog-routes
                        (GET "/" [] (get-all-posts))
                        (POST "/" {body :body} (create-new-post body))
                        (context "/:id" [id] (defroutes microblog-routes
                                                        (GET "/" [] (get-post id))
                                                        (DELETE "/" [] (delete-post id))))))
           (route/not-found "Not Found"))

(def app
  (-> (handler/api app-routes)
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)))
