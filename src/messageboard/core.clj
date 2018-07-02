(ns messageboard.core
	(:use compojure.core)
	(:use [clojure.data.json :only [read-str write-str]])
	(:use [clojure.data.xml :only [element emit-str]])
	(:require [compojure.route :as route]
	[compojure.handler :as handler]))

(def messagelist '())

(defn fields-found [record]
	(if (every? #(contains? record %) ["title" "content" "sender" "url"])
		record	
		(throw (Exception. "Required fields: title, content, sender, url")))
)

(defn url-valid [record]
	(def url (java.net.URL. (get record "url")))
	(.toURI url)
	record
)

(defn validate [record]
	(-> record fields-found url-valid)
)

(defn create-message [jsonstr]
	(try
		(def messagelist (conj messagelist (validate (read-str jsonstr))))
		{:status 200 :body (str "New message added. Nr. of messages: " (.size messagelist))}
	(catch Throwable e {:status 400 :body (.getMessage e)}))
)

(defn record-as-xml [record]
	(element :message {}
		(map
			(fn [key] (element key {} (str (get record key))))
			(keys record))
	)
)

(defn full-list [type] 
	(case type
		(nil "json") (write-str messagelist)
		"xml" (emit-str (element :messages {}
			(map #(record-as-xml %) messagelist)
		))
		"Invalid type parameter"
	)
)

(defn short-list []
	(write-str (map #(dissoc % "url") messagelist))
)

(defn list-messages [params]
	(case (get params :version)
		nil {:status 400 :body "Missing version parameter"}
		"short" (if (= 1 (.size params))
			{:status 200 :body (short-list)}
			{:status 400 :body "Short version request must not contain extra parameters"})
		"full" {:status 200 :body (full-list (get params :type))}
		{:status 400 :body "Invalid version parameter"}
	)
)

(defroutes app-routes
  (PUT "/messageboard" {body :body} (create-message (slurp body)))
  (GET "/messageboard" {params :params} [] (list-messages params))
  (route/not-found "Not found")
)

(def app (handler/site app-routes))
