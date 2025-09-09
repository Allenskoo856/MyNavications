# 系统架构

## 总览

MyNavications 遵循经典的三层/分层架构：控制器（Controller）- 服务（Service）- 数据访问（DAO/Mapper），并在核心模块中使用缓存、消息队列（可选）和权限模块来提升系统的稳定性与扩展性。

## 组件图

- Web 层：Beetl 模板 + 静态资源
- API 层：RESTful 接口，使用 JWT 进行鉴权
- 服务层：业务逻辑、事务管理
- 持久层：MyBatis-Plus Mapper
- 缓存层：EhCache
- 安全：Shiro + JWT

## 流量路径

1. 浏览器/客户端发起 HTTP 请求
2. Web 层路由到 Controller，进行参数校验
3. Controller 调用 Service 完成业务逻辑
4. Service 调用 Mapper 与数据库交互，并使用 EhCache 缓存热点数据
5. 结果返回到 Controller，渲染视图或返回 JSON

## 部署架构建议

- 小规模部署（单机）
  - 单个应用实例 + MySQL
  - EhCache 本地缓存

- 中大型部署（可扩展）
  - 多个应用实例（容器化部署）
  - MySQL 主从或集群（高可用）
  - Redis 替代 EhCache 实现分布式缓存（若需要）
  - 配置中心（例如：Spring Cloud Config）
  - Service Mesh 或 API 网关（可选）

## 扩展点

- 支持多数据源：`config/datasource/MultiDataSourceConfig.java`
- 支持分布式会话：`SpringSessionConfig.java`（使用 Redis）
- 插件化点：模块化的 `modular/` 目录，方便按需扩展

## 性能考虑

- 使用 EhCache 缓存热点数据，减少数据库压力
- 使用 MyBatis-Plus 分页查询和索引优化
- 静态资源使用 CDN 或 Nginx 静态服务
- 数据库连接池（Hikari）参数调优

---

*最后更新: 2025年9月9日*
