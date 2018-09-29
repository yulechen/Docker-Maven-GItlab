#!/usr/bin/env bash
# 启动docker
docker-compose up
# 容器自动后执行
docker exec -d sexam bash /app/startup.sh