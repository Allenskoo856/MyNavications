# 常见问题 FAQ

## Q1: 如何修改默认端口？
在 `src/main/resources/application.yml` 修改 `server.port` 即可。

## Q2: 如何重置管理员密码？
使用以下 SQL 更新密码：
```sql
UPDATE sys_user SET password = '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918' WHERE account = 'admin';
```
（该哈希对应密码：`admin`）

## Q3: 启动后页面404？
- 检查 `src/main/webapp/WEB-INF/view` 下是否存在对应模板
- 检查应用启动日志是否有错误

## Q4: 数据库导入报错
- 确认 MySQL 版本为 8.0+
- 检查 SQL 文件编码为 UTF8MB4

## Q5: 如何开启 Swagger？
在 `application.yml` 中将 `guns.swagger-open` 设置为 `true`。

## Q6: 静态资源加载缓慢
- 使用浏览器 Network 工具检查资源大小
- 考虑使用 CDN 或启用 gzip

---

*最后更新: 2025年9月9日*
