call mvn clean 
cd Transport
call mvn install
cd ..
cd Server
start http://127.0.0.1:8080/tdd-trainer/
call mvn jetty:run
