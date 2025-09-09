# 本地部署

本文档说明在本地环境部署 MyNavications 的步骤，包括数据库初始化、配置文件修改、构建与启动。

## 步骤一：准备环境

- 安装 JDK 1.8+
- 安装 Maven 3.x
- 安装 MySQL 8.0+
- （可选）安装 Docker

## 步骤二：克隆仓库

```bash
git clone https://github.com/Allenskoo856/MyNavications.git
cd MyNavications
```

## 步骤三：数据库初始化

```bash
mysql -u root -p
CREATE DATABASE webstack CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
exit
mysql -u root -p webstack < sql/webstack.sql
```

## 步骤四：修改配置

编辑 `src/main/resources/application.yml`：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/webstack?autoReconnect=true&useUnicode=true&characterEncoding=utf8&serverTimezone=CTT
    username: root
    password: 你的数据库密码
```

## 步骤五：构建与运行

```bash
# 构建项目
mvn clean package -DskipTests

# 运行jar
java -jar target/Webstack-Guns-nkt-1.0.jar

# 或者使用Maven运行
gun mvn spring-boot:run
```

## 验证

- 打开前台: http://localhost:8000
- 打开后台: http://localhost:8000/admin
- Swagger: http://localhost:8000/swagger-ui.html

## 常见错误

- 端口被占用：修改 `server.port` 或停止占用进程
- 数据库连接失败：检查 `application.yml` 的连接字符串和用户名密码

---

*最后更新: 2025年9月9日*
