# MyNavications - 开源网址导航管理系统

![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Java](https://img.shields.io/badge/Java-8+-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.0.1-green.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue.svg)
![Docker](https://img.shields.io/badge/Docker-支持-blue.svg)

## 📖 项目简介

MyNavications（原名WebStack-Guns）是一个功能完整的开源网址导航网站管理系统，旨在为用户提供一个易于部署和管理的个性化网址导航平台。该项目具备完整的前后台系统，支持网站分类管理、站点管理、用户权限控制等功能。

### ✨ 核心特性

- 🎨 **现代化界面** - 简洁美观的用户界面，支持响应式设计
- 🔐 **权限管理** - 基于Shiro的完整权限控制体系
- 🚀 **性能优化** - 前端懒加载技术，提升用户体验
- 📱 **移动端适配** - 完美支持各种屏幕尺寸
- 🐳 **容器化部署** - 支持Docker一键部署
- 🔍 **实时搜索** - Ajax搜索功能，无页面刷新
- 🎯 **SEO友好** - 优化的URL结构和元标签

## 🏗️ 技术架构

### 后端技术栈
- **Spring Boot 2.0.1** - 微服务框架
- **Shiro** - 安全认证框架
- **MyBatis-Plus** - 数据持久层框架
- **Beetl** - 模板引擎
- **EhCache** - 缓存框架
- **JWT** - 无状态认证
- **Swagger 2.9.2** - API文档

### 前端技术栈
- **HTML5/CSS3** - 页面结构和样式
- **jQuery** - JavaScript框架
- **Bootstrap** - 响应式UI框架
- **Font Awesome** - 图标库
- **H-ui** - 后台管理UI框架

### 数据库
- **MySQL 8.0+** - 主数据库
- **EhCache** - 本地缓存

## 🚀 快速开始

### 环境要求

- JDK 1.8+
- Maven 3.x
- MySQL 8.0+
- Git

### 本地开发部署

1. **克隆项目**
```bash
git clone https://github.com/your-username/MyNavications.git
cd MyNavications
```

2. **数据库初始化**
```bash
# 创建数据库
mysql -u root -p
CREATE DATABASE webstack CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

# 导入初始数据
mysql -u root -p webstack < sql/webstack.sql
```

3. **配置应用**
修改 `src/main/resources/application.yml` 文件：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/webstack?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8
    username: your_username
    password: your_password
```

4. **构建并运行**
```bash
# 构建项目
mvn clean package

# 运行应用
java -jar target/Webstack-Guns-nkt-1.0.jar
```

5. **访问应用**
- 前台导航：http://localhost:8000
- 后台管理：http://localhost:8000/admin
- 默认账号：admin / 123456

### Docker部署

1. **使用预构建镜像**
```bash
docker run -d -p 8000:8000 \
  -e "DB_IP=your_mysql_ip" \
  -e "DB_PORT=3306" \
  -e "DB_USERNAME=your_username" \
  -e "DB_PASSWORD=your_password" \
  xcallen/mynavications:latest
```

2. **本地构建镜像**
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
  mynavications:latest
```

## 📁 项目结构

```
MyNavications/
├── sql/                          # 数据库脚本
│   └── webstack.sql             # 初始化SQL脚本
├── src/main/
│   ├── java/com/nikati/manage/
│   │   ├── config/              # 配置类
│   │   │   ├── datasource/      # 数据源配置
│   │   │   ├── properties/      # 属性配置
│   │   │   └── web/            # Web配置
│   │   ├── core/               # 核心工具
│   │   │   ├── aop/            # AOP切面
│   │   │   ├── common/         # 通用组件
│   │   │   ├── shiro/          # Shiro配置
│   │   │   └── util/           # 工具类
│   │   ├── modular/            # 业务模块
│   │   │   ├── api/            # API接口
│   │   │   └── system/         # 系统管理模块
│   │   └── WebstackGunsApplication.java
│   ├── resources/
│   │   ├── application.yml      # 主配置文件
│   │   ├── ehcache.xml         # 缓存配置
│   │   └── logback-spring.xml  # 日志配置
│   └── webapp/
│       ├── WEB-INF/view/       # 页面模板
│       └── static/             # 静态资源
├── Dockerfile                   # Docker构建文件
├── pom.xml                     # Maven依赖配置
└── README.md                   # 项目说明
```

## 🔧 核心功能

### 前台功能
- **网站导航展示** - 分类展示各类网站链接
- **实时搜索** - Ajax搜索功能，快速定位网站
- **响应式设计** - 适配PC、平板、手机等设备
- **懒加载** - 图片懒加载，提升页面性能

### 后台管理功能
- **用户管理** - 用户增删改查、权限分配
- **角色管理** - 角色权限配置
- **部门管理** - 组织架构管理
- **菜单管理** - 动态菜单配置
- **网站管理** - 网站信息维护
- **分类管理** - 网站分类管理
- **日志管理** - 系统操作日志
- **字典管理** - 数据字典维护

## 🔌 API接口

### 认证接口
```http
POST /gunsApi/auth
Content-Type: application/x-www-form-urlencoded

username=admin&password=123456
```

### 网站管理接口
```http
# 获取网站列表
GET /site/list?title=关键词&page=1&size=10
Authorization: Bearer <token>

# 添加网站
POST /site/add
Authorization: Bearer <token>
Content-Type: application/json

{
  "categoryId": 1,
  "title": "网站标题",
  "url": "https://example.com",
  "description": "网站描述"
}
```

### 用户管理接口
```http
# 获取用户列表
GET /mgr/list?name=admin
Authorization: Bearer <token>

# 添加用户
POST /mgr/add
Authorization: Bearer <token>
```

详细API文档请参考：[API接口文档](docs/API.md)

## 🔐 安全机制

### 认证方式
- **Shiro认证** - 基于Shiro的统一认证
- **JWT Token** - 无状态Token认证
- **会话管理** - 支持分布式会话

### 权限控制
- **角色权限** - 基于角色的权限控制(RBAC)
- **细粒度权限** - 支持按钮级权限控制
- **权限注解** - 基于注解的权限校验

## 🚀 部署指南

### 生产环境部署

1. **环境准备**
```bash
# 安装JDK 8
sudo apt-get install openjdk-8-jdk

# 安装MySQL
sudo apt-get install mysql-server

# 创建部署目录
sudo mkdir -p /opt/mynavications
```

2. **配置优化**
```yaml
# application-prod.yml
spring:
  profiles: prod
  datasource:
    url: jdbc:mysql://prod-mysql:3306/webstack
    hikari:
      maximum-pool-size: 20
      minimum-idle: 5
```

3. **启动服务**
```bash
# 使用systemd管理服务
sudo systemctl enable mynavications
sudo systemctl start mynavications
```

### Docker Compose部署

创建 `docker-compose.yml`：
```yaml
version: '3.8'
services:
  mysql:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: rootpassword
      MYSQL_DATABASE: webstack
    volumes:
      - ./sql/webstack.sql:/docker-entrypoint-initdb.d/webstack.sql
      - mysql_data:/var/lib/mysql
    ports:
      - "3306:3306"

  app:
    image: xcallen/mynavications:latest
    depends_on:
      - mysql
    environment:
      DB_IP: mysql
      DB_PORT: 3306
      DB_USERNAME: root
      DB_PASSWORD: rootpassword
    ports:
      - "8000:8000"

volumes:
  mysql_data:
```

启动：
```bash
docker-compose up -d
```

## 🛠️ 开发指南

### 添加新模块

1. **创建模型类**
```java
@TableName("your_table")
public class YourModel extends BaseEntity {
    // 实体字段
}
```

2. **创建Mapper接口**
```java
public interface YourMapper extends BaseMapper<YourModel> {
    // 自定义SQL方法
}
```

3. **创建Service层**
```java
@Service
public class YourServiceImpl extends ServiceImpl<YourMapper, YourModel> implements YourService {
    // 业务逻辑
}
```

4. **创建Controller**
```java
@RestController
@RequestMapping("/your")
public class YourController extends BaseController {
    // 接口方法
}
```

### 扩展权限控制

1. **添加权限注解**
```java
@RequiresPermissions("your:module:view")
@RequestMapping("/list")
public Object list() {
    // 方法实现
}
```

2. **配置权限菜单**
在后台管理→菜单管理中添加对应的权限配置。

## 🤝 贡献指南

1. **Fork项目**
2. **创建特性分支** (`git checkout -b feature/AmazingFeature`)
3. **提交更改** (`git commit -m 'Add some AmazingFeature'`)
4. **推送分支** (`git push origin feature/AmazingFeature`)
5. **提交Pull Request**

## 📝 更新日志

### v2.0.0 (2024-01-15)
- ✨ 新增实时搜索功能
- 🎨 优化前端UI界面
- 🔧 重构权限管理模块
- 🐛 修复已知问题

### v1.5.0 (2023-12-01)
- ✨ 新增Docker支持
- 🔧 升级Spring Boot版本
- 📱 优化移动端适配

## ❓ 常见问题

### Q: 如何修改默认端口？
A: 在 `application.yml` 中修改 `server.port` 配置。

### Q: 如何重置管理员密码？
A: 连接数据库，执行：
```sql
UPDATE sys_user SET password = '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918' WHERE account = 'admin';
```
密码重置为：admin

### Q: Docker部署时数据库连接失败？
A: 检查网络配置和环境变量设置，确保数据库服务可访问。

## 📄 许可证

本项目基于 [MIT License](LICENSE) 开源协议。

## 👥 贡献者

感谢所有为本项目做出贡献的开发者！

## 🔗 相关链接

- [项目主页](https://github.com/your-username/MyNavications)
- [在线演示](https://demo.mynavications.com)
- [问题反馈](https://github.com/your-username/MyNavications/issues)
- [更新日志](CHANGELOG.md)

## 📞 联系我们

- 邮箱：mrzonglunli@gmail.com
- QQ群：838486863

---

⭐ 如果这个项目对您有帮助，请不要忘记给我们一个Star！