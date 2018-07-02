# messageboard

A RESTful message board demo. (c) Jaakko Saaristo 2014

## Prerequisites

- have network connection
- Linux: put bin/lein into your path
- Windows (untested): put bin/lein.bat into your path
- execute lein (or lein.bat) to have Leiningen self install

## Running

To start a local Jetty web server (port 3000, context root = /) for the application, run:

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

## License

Copyright © 2014 Jaakko Saaristo

