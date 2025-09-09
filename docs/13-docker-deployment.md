# Docker 部署

本文档介绍如何使用 Docker 将 MyNavications 容器化并通过 Docker Compose 启动整个栈。

## 使用官方镜像（或第三方镜像）

```bash
# 拉取镜像并运行
docker run -d -p 8000:8000 \
  -e "DB_IP=your_mysql_ip" \
  -e "DB_PORT=3306" \
  -e "DB_USERNAME=your_username" \
  -e "DB_PASSWORD=your_password" \
  --name mynavications \
  xcallen/mynavications:latest
```

## 本地构建镜像

```bash
# 构建jar
mvn clean package -DskipTests

# 构建镜像
docker build -t mynavications:latest .

# 运行容器
docker run -d -p 8000:8000 \
  -e "DB_IP=localhost" \
  -e "DB_PORT=3306" \
  -e "DB_USERNAME=root" \
  -e "DB_PASSWORD=123456" \
  --name mynavications \
  mynavications:latest
```

## Docker Compose

创建 `docker-compose.yml`：

```yaml
version: '3.8'
services:
  mysql:
    image: mysql:8.0
    container_name: mynavications-mysql
    environment:
      MYSQL_ROOT_PASSWORD: rootpassword
      MYSQL_DATABASE: webstack
    volumes:
      - ./sql/webstack.sql:/docker-entrypoint-initdb.d/webstack.sql
      - mysql_data:/var/lib/mysql
    ports:
      - "3306:3306"

  app:
    image: mynavications:latest
    container_name: mynavications-app
    depends_on:
      - mysql
    environment:
      DB_IP: mysql
      DB_PORT: 3306
      DB_USERNAME: root
      DB_PASSWORD: rootpassword
    ports:
      - "8000:8000"
    restart: unless-stopped

volumes:
  mysql_data:
```

启动服务：

```bash
# 启动
docker-compose up -d

# 查看日志
docker-compose logs -f app
```

## 持续集成建议

- 在 CI 中构建并推送 Docker 镜像到私有仓库（Docker Hub / GitHub Packages）
- 使用 GitHub Actions / GitLab CI 实现自动化构建和推送

---

*最后更新: 2025年9月9日*
