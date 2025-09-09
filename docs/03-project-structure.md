# 项目结构

## 📁 整体目录结构

```
MyNavications/
├── sql/                              # 数据库脚本
│   └── webstack.sql                 # 初始化SQL脚本
├── src/
│   └── main/
│       ├── java/                    # Java源代码
│       │   └── com/nikati/manage/   # 主包路径
│       ├── resources/               # 资源文件
│       └── webapp/                  # Web资源
├── target/                          # Maven构建输出目录
├── docs/                           # 项目文档
├── config/                         # 配置文件模板
├── Dockerfile                      # Docker镜像构建文件
├── docker-compose.yml             # Docker编排文件
├── pom.xml                        # Maven依赖配置
├── README.md                      # 项目说明文档
└── LICENSE                        # 开源协议文件
```

## 🔍 Java源码结构

### 主包结构
```
src/main/java/com/nikati/manage/
├── WebstackGunsApplication.java     # Spring Boot启动类
├── ManageServletInitializer.java    # Servlet初始化器
├── config/                          # 配置类
├── core/                           # 核心组件
├── modular/                        # 业务模块
└── util/                           # 工具类
```

### 配置模块 (config/)
```
config/
├── datasource/                      # 数据源配置
│   ├── MultiDataSourceConfig.java  # 多数据源配置
│   └── SingleDataSourceConfig.java # 单数据源配置
├── properties/                      # 属性配置
│   ├── BeetlProperties.java        # Beetl模板配置
│   └── GunsProperties.java         # 应用属性配置
├── web/                            # Web配置
│   ├── BeetlConfig.java            # Beetl配置
│   ├── ShiroConfig.java            # Shiro安全配置
│   └── WebConfig.java              # Web MVC配置
├── EhCacheConfig.java              # EhCache缓存配置
├── SpringSessionConfig.java        # Session配置
└── SwaggerConfig.java              # Swagger API文档配置
```

### 核心组件 (core/)
```
core/
├── aop/                            # 切面编程
│   ├── GlobalExceptionHandler.java # 全局异常处理器
│   └── LogAop.java                 # 日志切面
├── common/                         # 通用组件
│   ├── annotion/                   # 自定义注解
│   ├── constant/                   # 常量定义
│   ├── controller/                 # 基础控制器
│   ├── exception/                  # 异常定义
│   ├── node/                       # 树形节点
│   └── page/                       # 分页组件
├── interceptor/                    # 拦截器
│   └── RestApiInteceptor.java     # REST API拦截器
├── listener/                       # 监听器
│   └── ConfigListener.java        # 配置监听器
├── log/                           # 日志组件
│   ├── LogManager.java            # 日志管理器
│   └── LogObjectHolder.java       # 日志对象持有者
├── shiro/                         # Shiro安全组件
│   ├── ShiroDbRealm.java          # Shiro数据库认证域
│   ├── ShiroKit.java              # Shiro工具类
│   └── ShiroUser.java             # Shiro用户信息
└── util/                          # 工具类
    ├── ApiMenuFilter.java         # API菜单过滤器
    ├── JwtTokenUtil.java          # JWT Token工具
    ├── KaptchaUtil.java           # 验证码工具
    ├── Pager.java                 # 分页工具
    └── QiniuCloudUtil.java        # 七牛云工具
```

### 业务模块 (modular/)
```
modular/
├── api/                           # API接口模块
│   └── ApiController.java         # API控制器
└── system/                        # 系统管理模块
    ├── controller/                # 控制器层
    ├── dao/                      # 数据访问层
    ├── factory/                  # 工厂类
    ├── model/                    # 数据模型
    ├── service/                  # 服务层
    ├── transfer/                 # 传输对象
    └── warpper/                  # 包装器
```

## 🎛️ 系统管理模块详解

### 控制器层 (controller/)
```
controller/
├── CategoryController.java         # 分类管理控制器
├── DeptController.java            # 部门管理控制器
├── DictController.java            # 字典管理控制器
├── LoginController.java           # 登录控制器
├── LoginLogController.java        # 登录日志控制器
├── LogController.java             # 操作日志控制器
├── MenuController.java            # 菜单管理控制器
├── NoticeController.java          # 通知管理控制器
├── RoleController.java            # 角色管理控制器
├── SiteController.java            # 网站管理控制器
└── UserMgrController.java         # 用户管理控制器
```

### 数据访问层 (dao/)
```
dao/
├── CategoryMapper.java            # 分类数据访问
├── DeptMapper.java               # 部门数据访问
├── DictMapper.java               # 字典数据访问
├── LoginLogMapper.java           # 登录日志数据访问
├── MenuMapper.java               # 菜单数据访问
├── NoticeMapper.java             # 通知数据访问
├── OperationLogMapper.java       # 操作日志数据访问
├── RelationMapper.java           # 关系数据访问
├── RoleMapper.java               # 角色数据访问
├── SiteMapper.java               # 网站数据访问
└── UserMapper.java               # 用户数据访问
```

### 数据模型 (model/)
```
model/
├── Category.java                 # 分类实体
├── Dept.java                    # 部门实体
├── Dict.java                    # 字典实体
├── LoginLog.java                # 登录日志实体
├── Menu.java                    # 菜单实体
├── Notice.java                  # 通知实体
├── OperationLog.java            # 操作日志实体
├── Relation.java                # 关系实体
├── Role.java                    # 角色实体
├── Site.java                    # 网站实体
└── User.java                    # 用户实体
```

### 服务层 (service/)
```
service/
├── impl/                        # 服务实现类
│   ├── CategoryServiceImpl.java # 分类服务实现
│   ├── DeptServiceImpl.java    # 部门服务实现
│   ├── DictServiceImpl.java    # 字典服务实现
│   ├── LoginLogServiceImpl.java # 登录日志服务实现
│   ├── MenuServiceImpl.java    # 菜单服务实现
│   ├── NoticeServiceImpl.java  # 通知服务实现
│   ├── OperationLogServiceImpl.java # 操作日志服务实现
│   ├── RoleServiceImpl.java    # 角色服务实现
│   ├── SiteServiceImpl.java    # 网站服务实现
│   └── UserServiceImpl.java    # 用户服务实现
├── ICategoryService.java        # 分类服务接口
├── IDeptService.java           # 部门服务接口
├── IDictService.java           # 字典服务接口
├── ILoginLogService.java       # 登录日志服务接口
├── IMenuService.java           # 菜单服务接口
├── INoticeService.java         # 通知服务接口
├── IOperationLogService.java   # 操作日志服务接口
├── IRoleService.java           # 角色服务接口
├── ISiteService.java           # 网站服务接口
└── IUserService.java           # 用户服务接口
```

## 📁 资源文件结构

### 配置资源 (resources/)
```
resources/
├── application.yml              # 主配置文件
├── ehcache.xml                 # EhCache缓存配置
├── logback-spring.xml          # 日志配置
└── META-INF/
    └── spring-devtools.properties # 开发工具配置
```

### Web资源 (webapp/)
```
webapp/
├── static/                     # 静态资源
│   ├── css/                   # 样式文件
│   ├── fonts/                 # 字体文件
│   ├── img/                   # 图片资源
│   ├── js/                    # JavaScript文件
│   ├── modular/               # 模块化资源
│   └── tmp/                   # 临时文件
└── WEB-INF/
    └── view/                  # 页面模板
        ├── common/            # 公共模板
        ├── system/            # 系统管理页面
        ├── user/              # 用户相关页面
        ├── admin.html         # 管理后台首页
        ├── index.html         # 前台首页
        └── login.html         # 登录页面
```

## 🎨 前端资源结构

### 样式文件 (css/)
```
css/
├── app.css                    # 应用样式
├── bootstrap.min.css          # Bootstrap框架
├── font-awesome.css           # Font Awesome图标
├── H-ui.css                   # H-ui框架样式
├── login.css                  # 登录页样式
├── search.css                 # 搜索功能样式
├── style.css                  # 主样式文件
├── toastr.css                 # 消息提示样式
├── linecons/                  # 线性图标
├── patterns/                  # 图案样式
└── plugins/                   # 插件样式
```

### JavaScript文件 (js/)
```
js/
├── app.js                     # 应用脚本
├── bootstrap.min.js           # Bootstrap脚本
├── content.js                 # 内容处理脚本
├── echarts.min.js             # 图表库
├── H-ui.js                    # H-ui框架脚本
├── jquery.min.js              # jQuery库
├── toastr.js                  # 消息提示脚本
├── common/                    # 公共脚本
└── plugins/                   # 插件脚本
```

### 模块化资源 (modular/)
```
modular/
├── code/                      # 代码生成模块
├── flowable/                  # 工作流模块
├── system/                    # 系统管理模块
└── user/                      # 用户模块
```

## 📄 页面模板结构

### 公共模板 (common/)
```
common/
├── _container.html            # 容器模板
├── _footer.html              # 页脚模板
├── _header.html              # 页头模板
├── _menu.html                # 菜单模板
├── _script.html              # 脚本模板
└── _sidebar.html             # 侧边栏模板
```

### 系统管理页面 (system/)
```
system/
├── dept/                     # 部门管理页面
├── dict/                     # 字典管理页面
├── log/                      # 日志管理页面
├── menu/                     # 菜单管理页面
├── notice/                   # 通知管理页面
├── role/                     # 角色管理页面
├── site/                     # 网站管理页面
└── user/                     # 用户管理页面
```

## 🗃️ 数据库结构

### 主要数据表
| 表名 | 描述 | 主要字段 |
|------|------|----------|
| category | 网站分类表 | id, parent_id, title, icon, sort |
| site | 网站信息表 | id, category_id, title, url, description |
| sys_user | 系统用户表 | user_id, account, name, password, salt |
| sys_role | 系统角色表 | role_id, name, description, tips |
| sys_menu | 系统菜单表 | menu_id, name, url, levels, ismenu |
| sys_dept | 系统部门表 | dept_id, name, fullname, description |
| sys_dict | 系统字典表 | dict_id, name, description |
| sys_notice | 系统通知表 | notice_id, title, content, create_time |
| sys_operation_log | 操作日志表 | id, logtype, logname, userid, classname |
| sys_login_log | 登录日志表 | id, logname, userid, succeed, message |
| sys_relation | 关系表 | id, menuid, roleid |

## 🔧 核心配置文件

### application.yml
主要配置项：
- 服务器端口配置
- 数据源配置
- Guns框架配置
- Beetl模板配置
- Spring配置
- MyBatis-Plus配置

### pom.xml
主要依赖：
- Spring Boot Starter 依赖
- Shiro 安全框架
- MyBatis-Plus ORM
- Beetl 模板引擎
- EhCache 缓存
- Swagger API文档
- JWT Token支持
- MySQL 驱动

## 📦 部署相关文件

### Dockerfile
定义了Docker镜像的构建过程：
- 基础镜像选择
- 应用jar包复制
- 端口暴露
- 启动命令

### docker-compose.yml
定义了完整的应用栈：
- MySQL数据库服务
- 应用服务
- 网络配置
- 数据卷配置

## 🏗️ 架构设计原则

### 分层架构
- **控制器层(Controller)**: 处理HTTP请求，参数验证
- **服务层(Service)**: 业务逻辑处理，事务管理
- **数据访问层(DAO)**: 数据库操作，SQL映射
- **实体层(Model)**: 数据实体定义

### 模块划分
- **系统管理模块**: 用户、角色、权限、菜单等基础功能
- **网站管理模块**: 网站信息、分类管理等核心功能
- **日志模块**: 操作日志、登录日志等审计功能
- **通知模块**: 系统通知、消息推送等功能

### 设计模式
- **工厂模式**: UserFactory等工厂类
- **包装器模式**: 各种Wrapper类用于数据转换
- **观察者模式**: 事件监听和通知机制
- **策略模式**: 不同的缓存策略实现

---

*最后更新: 2025年9月9日*
