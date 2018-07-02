(defproject messageboard "0.1.0-SNAPSHOT"
  :description "Simple messageboard app"
  :dependencies [[org.clojure/clojure "1.5.1"]
		[compojure "1.1.5"]
		[org.clojure/data.json "0.2.4"]
		[org.clojure/data.xml "0.0.7"]
		[speclj "2.3.4"]]
  :plugins [[lein-ring "0.8.10"] [speclj "2.3.4"]]
  :ring {:handler messageboard.core/app}
)
