# Name Game

This is a Grails 3.3 rest profile web server. Grails uses Groovy on top of Spring with Hibernate and gson views.

Requires Java to be installed, database is embedded h2.

To start server:
```
./gradlew bootRun
```

HTTP requests are intercepted to authenticate using Spring Security.

Most of the relevant source code can be found in ./grails-app/ under the domain/, controllers/, services/, and views/ directories.

#### Testing
- Spock Framework with unit and integration tests
- Unit tests added for profile and picture endpoints
- Integration tests added for Game/Question/Statistics endpoints
```
./gradlew clean check
open build/reports/tests/index.html

```
There is an included postman collection for testing at https://raw.githubusercontent.com/bmolz/mimir-grails/master/mimir-postman.json

Game Integration tests at https://github.com/bmolz/mimir-grails/blob/master/src/integration-test/groovy/mimir/grails/QuestionFunctionalSpec.groovy

You can view the database data at <http://localhost:8080/dbconsole/> using JDBC URL:
`jdbc:h2:file:./build/h2db` to Connect.

The api profiles are automatically consumed/imported on bootstrap or can be POST to /api/import.

You can change users by navigating to /login/auth/{new username}

#### [Relevant Endpoints](https://github.com/bmolz/mimir-grails/blob/master/grails-app/controllers/mimir/grails/UrlMappings.groovy)
```
"/api/question" POST: new question, GET existing question
"/api/question/answer/$id" POST: answer question with id of answer

"/api/leaderboard" GET Top 10 user rankings, supports pagination and sort
"/api/import" [admin] POST body: {url: "http://something_to_import"}
"/login/auth/$id" POST id to authenticate as desired user

Rest endpoints for accessing profiles with search/sort/paginate:
"/api/profile/$id?search=Matt" GET
"/api/profile/$id/social" GET
"/api/profile/$id/picture" GET
```
