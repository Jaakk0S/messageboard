(ns messageboard.core-spec
  (:use messageboard.core
        speclj.core))

(describe "Request body validator"

	(it "fails on missing fields"
		(should-throw Exception
		(fields-found {"title" "lala"})))

	(it "returns the record itself on correct fields found"
		(def record {"title" "a", "content" "a", "sender" "a", "url" "a"})
		(should=
		record
		(fields-found record)))

	(it "accepts a correct url, returns the record itself"
		(def record {"url" "http://www.google.com"})
		(should=
		record	
		(url-valid record)))
	
	(it "fails on malformed url"
		(should-throw Exception
		(url-valid {"url" "httlp:/www.google.com"})))

)

