# Spring Security 3.0.x J2EE Pre-Authentication Example with REST API

The [pre-authentication sample](http://static.springsource.org/spring-security/site/docs/3.0.x/reference/sample-apps.html)
application provided by [spring-security](http://static.springsource.org/spring-security/site/) is confusing.
It seems to use an outdated XML configuration.
I wanted to find a simple example, but I was only able to piece together the various configuration options
from forum posts and the spring-security documentation.  This sample is more of what I was looking for.
Hopefully it's useful to others.

I also discovered an issue with [Tomcat](http://tomcat.apache.org) and spring-security's session fixation protection.
I had to disable spring-security's session fixation protection in order to get the authentication working as expected.

### Jetty

    mvn jetty:run

Point your web browser to http://localhost:8080/spring-security-j2ee-preauth-example - log in as either jimi, fred or john.
The difference is the following:
 - jimi is ADMIN and USER
 - john is ADMIN only (but can see also USER content)
 - fred is USER

### Tomcat

Edit $CATALINA_HOME/conf/tomcat-users.xml add the following:

    <role rolename="ROLE_USER"/>
    <role rolename="ROLE_ADMIN"/>
    <user username="jimi" password="jimipw" roles="ROLE_USER,ROLE_ADMIN"/>
    <user username="fred" password="fredpw" roles="ROLE_USER"/>
    <user username="john" password="johnpw" roles="ROLE_ADMIN"/>

Deploy the war file:

  cp target/spring-security-j2ee-preauth-example-1.0-SNAPSHOT.war $CATALINA_HOME/webapps/preauth.war

Point your web browser to http://localhost:8080/preauth - log in as either jimi, fred or john.

## API

There are four API entries:

1. /api/public (public access from anybody which is either ADMIN or USER)

2. /api/confidential (restricted access only to ADMIN)

3. /api/mixed (access policy as /api/public, but the content is filtered if not authorized - fred will see less information)

4. /api/public2 (public access, but specified only the lower role, access to ADMIN is provided by the hierarchyRole implementation)