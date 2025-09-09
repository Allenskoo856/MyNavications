# 快速开始

## 📋 环境要求

在开始之前，请确保您的开发环境满足以下要求：

### 必须组件
- **JDK**: 1.8 或更高版本
- **Maven**: 3.x 版本
- **MySQL**: 8.0+ 版本
- **Git**: 用于代码管理

### 可选组件
- **Docker**: 用于容器化部署
- **Docker Compose**: 用于一键部署
- **IDE**: IntelliJ IDEA 或 Eclipse
- **Postman**: 用于API测试

## 🚀 本地开发部署

### 1. 克隆项目

```bash
# 克隆项目到本地
git clone https://github.com/Allenskoo856/MyNavications.git

# 进入项目目录
cd MyNavications
```

### 2. 数据库配置

#### 创建数据库
```bash
# 登录MySQL
mysql -u root -p

# 创建数据库
CREATE DATABASE webstack CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

# 退出MySQL
EXIT;
```

#### 导入初始数据
```bash
# 导入数据库脚本
mysql -u root -p webstack < sql/webstack.sql
```

#### 验证数据导入
```bash
# 再次登录MySQL
mysql -u root -p

# 使用数据库
USE webstack;

# 查看表结构
SHOW TABLES;

# 检查用户表数据
SELECT * FROM sys_user LIMIT 5;
```

### 3. 配置应用

编辑 `src/main/resources/application.yml` 文件：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/webstack?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=CTT&allowPublicKeyRetrieval=true
    username: your_username    # 替换为您的MySQL用户名
    password: your_password    # 替换为您的MySQL密码
    filters: wall,mergeStat
```

### 4. 构建项目

```bash
# 清理并编译项目
mvn clean compile

# 运行测试
mvn test

# 打包项目
mvn clean package
```

### 5. 启动应用

```bash
# 方式一：直接运行jar包
java -jar target/Webstack-Guns-nkt-1.0.jar

# 方式二：使用Maven插件运行
mvn spring-boot:run
```

### 6. 验证部署

应用启动成功后，可以通过以下URL访问：

- **前台导航**: http://localhost:8000
- **后台管理**: http://localhost:8000/admin
- **API文档**: http://localhost:8000/swagger-ui.html

#### 默认账号信息
| 账号类型 | 用户名 | 密码 | 权限 |
|---------|--------|------|------|
| 超级管理员 | admin | 111111 | 所有权限 |

## 🐳 Docker 部署

### 1. 使用预构建镜像

```bash
# 拉取并运行容器
docker run -d -p 8000:8000 \
  -e "DB_IP=your_mysql_ip" \
  -e "DB_PORT=3306" \
  -e "DB_USERNAME=your_username" \
  -e "DB_PASSWORD=your_password" \
  --name mynavications \
  xcallen/mynavications:latest
```

### 2. 本地构建镜像

```bash
# 构建项目
mvn clean package

# 构建Docker镜像
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

### 3. Docker Compose 一键部署

创建 `docker-compose.yml` 文件：

```yaml
version: '3.8'

services:
  mysql:
    image: mysql:8.0
    container_name: mynavications-mysql
    environment:
      MYSQL_ROOT_PASSWORD: rootpassword
      MYSQL_DATABASE: webstack
      MYSQL_CHARACTER_SET_SERVER: utf8mb4
      MYSQL_COLLATION_SERVER: utf8mb4_unicode_ci
    volumes:
      - ./sql/webstack.sql:/docker-entrypoint-initdb.d/webstack.sql
      - mysql_data:/var/lib/mysql
    ports:
      - "3306:3306"
    networks:
      - mynavications-network

  app:
    image: xcallen/mynavications:latest
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
    networks:
      - mynavications-network
    restart: unless-stopped

volumes:
  mysql_data:

networks:
  mynavications-network:
    driver: bridge
```

启动服务：

```bash
# 启动所有服务
docker-compose up -d

# 查看服务状态
docker-compose ps

# 查看日志
docker-compose logs -f app
```

## 🔧 开发环境配置

### IDE 配置

#### IntelliJ IDEA
1. 导入项目：`File -> Open -> 选择项目根目录`
2. 配置JDK：`File -> Project Structure -> Project -> Project SDK`
3. 配置Maven：`File -> Settings -> Build Tools -> Maven`
4. 安装插件：
   - Lombok Plugin
   - MyBatis Plugin
   - Swagger Plugin

#### Eclipse
1. 导入项目：`File -> Import -> Existing Maven Projects`
2. 配置JDK：`Project -> Properties -> Java Build Path`
3. 安装插件：
   - Lombok Plugin
   - Spring Tools Suite

### 代码风格配置

导入代码格式化配置文件：
- [IntelliJ IDEA 配置](../config/idea-code-style.xml)
- [Eclipse 配置](../config/eclipse-code-style.xml)

## 🔍 验证安装

### 1. 检查应用状态

```bash
# 检查应用是否正常启动
curl http://localhost:8000

# 检查健康状态
curl http://localhost:8000/actuator/health
```

### 2. 测试API接口

```bash
# 获取认证Token
curl -X POST "http://localhost:8000/gunsApi/auth" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "username=admin&password=111111"

# 使用Token访问受保护的API
curl -X GET "http://localhost:8000/mgr/list" \
  -H "Authorization: Bearer YOUR_TOKEN"
```

### 3. 验证前台功能

访问 http://localhost:8000，确认：
- [ ] 页面正常加载
- [ ] 搜索功能正常
- [ ] 分类展示正常
- [ ] 网站链接可点击

### 4. 验证后台功能

访问 http://localhost:8000/admin，确认：
- [ ] 登录页面正常
- [ ] 可以正常登录
- [ ] 管理界面正常显示
- [ ] 各功能模块可访问

## ⚠️ 常见问题

### 数据库连接失败
**问题**: `Communications link failure`

**解决方案**:
1. 检查MySQL服务是否启动
2. 确认连接参数正确
3. 检查防火墙设置
4. 验证MySQL用户权限

### 端口占用问题
**问题**: `Port 8000 was already in use`

**解决方案**:
```bash
# 查找占用端口的进程
lsof -i:8000

# 终止进程
kill -9 PID

# 或修改配置文件中的端口
server:
  port: 8080
```

### 编译错误
**问题**: Maven编译失败

**解决方案**:
1. 检查JDK版本是否正确
2. 清理Maven缓存：`mvn clean`
3. 重新下载依赖：`mvn dependency:resolve`
4. 检查网络连接

### 权限问题
**问题**: 无法访问管理功能

**解决方案**:
1. 确认使用正确的管理员账号
2. 检查用户权限配置
3. 清理浏览器缓存
4. 重新登录系统

## 📝 下一步

安装完成后，建议您：

1. 📖 阅读 [项目结构文档](./03-project-structure.md)
2. 🔧 查看 [开发环境配置](./04-development-setup.md)
3. 📊 了解 [数据库设计](./05-database-design.md)
4. 🔌 参考 [API接口文档](./06-api-documentation.md)

## 🆘 获取帮助

如果在安装过程中遇到问题：

1. 查看 [常见问题FAQ](./18-faq.md)
2. 搜索 [GitHub Issues](https://github.com/Allenskoo856/MyNavications/issues)
3. 提交新的Issue描述问题
4. 发送邮件至：mrzonglunli@gmail.com

---

*最后更新: 2025年9月9日*
