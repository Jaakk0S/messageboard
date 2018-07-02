# messageboard

A RESTful messageboard with Clojure--demo. Jaakko Saaristo 2014

This creates a local web server into port 3000 that handles message objects with content (title, sender, content, url). You can:
- PUT objects as JSON
- GET objects as versions (short, full) and formats (JSON, XML)

Fields are validated. URL needs to be a valid URL.

## Prerequisites

- have network connection
- Linux: put bin/lein into your path
- Windows (untested): put bin/lein.bat into your path
- execute lein (or lein.bat) to have Leiningen self install

## Running

To start the application, run:

    lein clean      (only needed if you have created the WAR archive)
    lein ring server-headless

To create a WAR archive (into directory target, with default context root = archive name), run:

    lein ring uberwar

## Using the interface

    - PUT /messageboard
          where the body should contain a JSON object
              - the object must contain fields title, sender, content and url
              - the url must be valid
              - sample object: {
                  "title": "lala",
                  "sender": "Pena",
                  "content": "some content",
                  "url": "http://www.google.com"
              }

    - GET /messageboard?version=short

    - GET /messageboard?version=full&type=TYPE
          where TYPE -> json|xml

    - Example: curl -XPUT localhost:3000/messageboard -d @testdata.dat -H 'Content-Type:application/json'

## Run functional tests

    lein spec


