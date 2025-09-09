# 安全机制

## 认证与授权

### 认证
- 使用 Apache Shiro 作为认证框架，配合自定义 `ShiroDbRealm` 从数据库加载用户信息。
- 提供 REST API 登录接口 `/gunsApi/auth`，登录后返回 JWT Token，前端在请求头中携带 `Authorization: Bearer {token}`。
- 支持会话管理，单机模式使用内置会话，分布式可启用 `SpringSessionConfig`（Redis）实现会话共享。

### 授权
- 基于角色的访问控制（RBAC）：用户关联角色，角色关联权限（Menu/Permission）。
- 支持注解式权限校验：`@Permission`、`@RequiresPermissions` 等自定义注解。
- 支持按钮级权限控制，前端通过菜单/按钮权限决定是否渲染对应UI。

## 安全措施

- 密码存储采用带盐的哈希（MD5 + salt 或更安全的PBKDF2/BCrypt/SCrypt建议替换）。
- JWT Token 设置合理的过期时间，并支持刷新策略。
- 所有敏感接口需做权限校验并记录操作日志（`sys_operation_log`）。
- 防止 SQL 注入：使用 MyBatis-Plus 参数化查询，避免字符串拼接。
- 防止 XSS：模板层对用户输入进行 HTML 转义，必要时使用 CSP（Content Security Policy）。

## 日志与审计

- 所有关键操作通过 AOP 记录到 `sys_operation_log`，包括用户、操作类型、类名、方法、时间、IP 地址等信息。
- 登录行为记录到 `sys_login_log`，支持按时间范围查询和导出。

## 常见安全改进建议

- 使用 HTTPS：在生产环境强制使用 TLS/HTTPS，使用 Let’s Encrypt 自动签发证书。
- 使用更强的密码哈希算法：推荐使用 BCrypt 或 Argon2；如果要兼容旧用户，可在下次登录时升级存储格式。
- 开启多因子认证（MFA）：通过短信/邮箱/TOTP 提升账户安全。
- 引入 WAF：抵御常见Web攻击
- 定期安全扫描和依赖漏洞检查（例如使用 Snyk、Dependabot）

---

*最后更新: 2025年9月9日*
