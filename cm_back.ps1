# same thing than cm_back.sh but for windows

function build {
  docker-compose up --build maven
}

function restart {
  docker-compose restart back
}

function logs {
  docker-compose logs -f back
}

function clear {
  docker-compose down -v --remove-orphans
  docker volume prune --force
  docker image prune --force
}

function up {
  build
  docker-compose up -d bdd
  docker-compose up --no-start back
  restart
}

function help {
  write-host "usage: ./cm_back.ps1 [option]"
  write-host "available options:"
  write-host "build     re-compiles the code, should be followed by a restart."
  write-host "restart   restarts the java container - is needed to take modifications into account."
  write-host "up        triggers build then a restart."
  write-host "logs      gets java's logs."
  write-host "clear     WARNING: hard reset on everything. Kills containers, volumes (including database data), images and kittens."
  write-host "help      displays this message."
}

if ($args.Count -lt 1)
{
    help
    exit 0
}

switch ($args[0])
{

  {$_ -in "build", "restart", "logs", "clear", "up", "help"} { &$args[0] }
  default { help }
}
