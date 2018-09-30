# Name Game

This is a Grails 3.3 rest profile web server. Grails uses Groovy on top of Spring with Hibernate and gson views.

Requires Java to be installed, database is embedded h2.

To start server:
```
./gradlew tasks bootRun
```
There are .jar wrappers so no additional install is required.
On windows replace with ./gradlew.bat

When you see `Grails application running at http://localhost:8080 in environment: development
` that will indicate the website is available

The first http request to the webserver will be intercepted to authenticate as player using Spring Security. All subsequent requests should be valid.

Most of the relevant source code can be found in ./grails-app/ under the domain/, controllers/, services/, and views/ directories.

The api profiles are automatically consumed/imported on bootstrap or can be POST to /api/import.

You can change users by navigating to /login/auth/{new username}


#### Testing
To run unit tests: (just scaffolded tests for now)
```
./gradlew tasks test

```
There is an included postman collection for testing at https://raw.githubusercontent.com/bmolz/mimir-grails/master/mimir-postman.json

You can view the database data at <http://localhost:8080/dbconsole/> using JDBC URL:
`jdbc:h2:file:./build/h2db` to Connect.

#### [Relevant Endpoints](https://github.com/bmolz/mimir-grails/blob/master/grails-app/controllers/mimir/grails/UrlMappings.groovy)
```
"/api/question" POST: new question, GET existing question
"/api/question/answer/$id" POST: answer question with id of answer

"/api/leaderboard" GET Top 10 user rankings, supports pagination and sort
"/api/import" [admin] POST body: {url: "http://something_to_import"}
"/login/auth/$id" POST id to authenticate as desired user

Rest endpoints for accessing profiles with search/sort/paginate:
"/api/profile/$id?search=Matt" GET, [admin] POST, UPDATE, DELETE
"/api/profile/$id/social" GET
"/api/profile/$id/picture" GET
```
