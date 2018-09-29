- 打包
```
mvn clean package -DskipTests

```
- 构建docker 镜像（开发）
```
mvn clean package  docker:build -DskipTests
```

- 推送远程docker仓库(测试)
```
 mvn clean package  docker:build -DpushImage -DskipTests
```
- 打标签（生产）
```
mvn clean package docker:build -DskipTests -DpushImageTags -DdockerImageTags=v0.0.1

```