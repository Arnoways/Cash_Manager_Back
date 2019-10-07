# Cash_Manager_Back

## How to run our server

Since we are using docker, you can check out our [docker-compose](docker-compose.yml) file for the containers configuration.  
We currently have 3 containers:
```
- bdd :     Our postgres database.
- maven:    The container we are using to compile our java code into an executable .jar file.
- java:     The container that will run our code.
```

For convenience reasons, we made a script that allows us to run one or multiple docker-compose commands which will be useful during our development process.  
The script is available [here](cm_back.sh) for Linux and Mac, and [here](cm_back.ps1) for Windows (yet untested).

```shell
usage: ./cm_back.sh [option]"
available options:"
build       re-compiles the code, should be followed by a restart."
restart     restarts the java container - is needed to take modifications into account."
up          triggers build then a restart."
logs        gets java's logs."
clear       WARNING: hard reset on everything. Kills containers, volumes (including database data), images and kittens."
help        displays this message."

```