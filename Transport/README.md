__Transport framework for Dojo-like games__
==============

Encapsulates transportation details between client and server.

How to use
-------
Just add following section to your repositories tag in pom.xml

```{xml}
    <repositories>
        <repository>
            <id>dojo-releases</id>
            <url>https://github.com/tdd-elevator-training/mvn-repo/releases</url>
        </repository>
        <repository>
            <id>dojo-snapshots</id>
            <url>https://github.com/tdd-elevator-training/mvn-repo/snapshots</url>
        </repository>
    </repositories>
```
Then add a dependency into <dependencies> tag of your pom.xml
```{xml}
        <dependency>
            <groupId>com.codenjoy</groupId>
            <artifactId>dojo-transport</artifactId>
            <version>1.2-SNAPSHOT</version>
        </dependency>
```

How to build & deploy
------------
* Make sure you have write access to repository *tdd-elevator-training/mvn-repo*
* Clone tdd-elevator-training/mvn-repo to your local path (In following examples the local path is E:\codenjoy). 
```
git clone git@github.com:tdd-elevator-training/mvn-repo.git
```
* cd to your local copy of dojo-transport project
```
cd E:\workspace\projects\dojo-transport\dojo-transport
```
* run deploy-snapshot.cmd with local path to your local mvn-repo location
```
deploy-snapshot.cmd E:\codenjoy\mvn-repo
```
* Add new files into mvn-repo repository. You can use either Gig GUI or command line:
```
cd e:\codenjoy\mvn-repo
git add *
git commit -m "deploy dojo-transport"
git push origin master
```
