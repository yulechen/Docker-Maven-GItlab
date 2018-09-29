- 打包
```
mvn clean package -DskipTests

```
- 构建docker 镜像
```
mvn clean package -DskipTests docker:build 
```

- 推送远程docker 仓库
```
 mvn clean package -DskipTests docker:build -DpushImage
```