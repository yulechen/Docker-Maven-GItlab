#!/usr/bin/env bash
getPid() {
  pid=$(ps -fwwC java|grep railorder.jar |awk '{print $2}')
  if [[ -z $pid ]];then
     pid=1
  fi
  echo $pid
}

stop(){
    echo "stop.."
    pid=$(getPid)
    if [[ $pid == 1 ]];then
        echo -e "\e[1;31m$APP already stop\e[0m"
    else
           kill $pid && sleep 2
    fi
 }
start(){
  echo "start.."
  cd /app
  nohup java -jar  -Djava.security.egd=file:/dev/./urandom  sexam-0.0.1-SNAPSHOT.jar --spring.profiles.active=$RUN_ENV >> output.log 2>&1 &
 }
stop
start