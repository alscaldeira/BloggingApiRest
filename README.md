# BloggingApiRest
This is the back end of a Blog service - version 0.0.1-RELEASE

### Want to test the app online?
Go to [blogging web site](https://bloggingapirest.herokuapp.com/swagger-ui.html)
Feel free to test it but the software is still in development so may have some bugs.

### Want to install the software?
#### Database
First you need to [download and install mysql](https://dev.mysql.com/downloads/workbench/)

Run this command in the terminal to download the code source:

`git clone git@github.com:alscaldeira/BloggingApiRest.git`

To use the database you'll need to go to go to root path of the projct src/main/resources/application.properties
And change the canfiguration of the database to your local database. I'll show an example:

\
`spring.datasource.url=jdbc:mysql://localhost:3306/bloggingdb?timeZone=UTC&createDatabaseIfNotExist=true` \
`spring.datasource.username=root` \
`spring.datasource.password=root`

You can change the localhost for the host and the "3306" for the port that your database responses
Username and password are really simple, just put the username and the password of your database

#### Application
Then you need to install the project with maven. So run:

`mvn package`

And finally to run the project just run the command:

`java -jar target/blog-0.0.1-SNAPSHOT.war`

### How to use the Blogging Rest API?
The get operations are token free, it means that you don't need to authenticate to use the operation get of each endpoint.
But if you want to post, delete or put something you need to be authenticated.

#### Authentication
You can authenticate in the first time by going in the /auth/signup

Just fill the fields with your data and if the response is the data you sent, then you can authenticate with the username and the password in /auth endpoint

Now you have full access to the others endpoints, feel free to send me an email about what you think about the system `anderson0caldeira@gmail.com` , I'm always open to comments. ;)
