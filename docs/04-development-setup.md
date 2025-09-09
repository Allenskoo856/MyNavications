# 开发环境配置

本文件说明如何在本地搭建开发环境、常用工具和编码约定，帮助开发者快速上手项目。

## 环境依赖

- JDK 1.8+
- Maven 3.x
- MySQL 8.0+
- Git
- 推荐IDE：IntelliJ IDEA Ultimate（或Community）

## 本地调试步骤

1. 克隆仓库并进入项目目录：
```bash
git clone https://github.com/Allenskoo856/MyNavications.git
cd MyNavications
```
2. 导入数据库：
```bash
mysql -u root -p
CREATE DATABASE webstack CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
exit
mysql -u root -p webstack < sql/webstack.sql
```
3. 在 `src/main/resources/application.yml` 中配置数据库连接。
4. 使用 IDE 导入为 Maven 项目，设置 JDK，启用 Lombok 插件。
5. 运行：
```bash
mvn clean package
mvn spring-boot:run
```

## 推荐插件与工具

- Lombok（代码简化）
- MyBatis/Mapper 插件（代码提示）
- CheckStyle/EditorConfig（代码规范）
- Postman（接口调试）

## 代码规范

- Java 代码遵循 Google Java Style（或团队自定义样式）
- 类注释和方法注释使用 Javadoc
- Controller 层校验参数使用 Spring Validation 注解
- 所有 SQL 操作通过 MyBatis-Plus Mapper 实现，避免手写拼接 SQL

## 提交规范

- 分支策略：feature/*、bugfix/*、hotfix/*
- 提交信息格式：类型(scope): 描述
  - feat(site): 添加网站管理接口
  - fix(user): 修复用户登录bug
- 每个 PR 需包含描述、复现步骤和截图（若有）

## 本地调试技巧

- 使用 `application.yml` 的 `guns.swagger-open` 打开 Swagger，便于接口测试
- devtools 已引入，修改代码后可自动重启（需在 IDE 设置中启用）
- 静态资源位于 `src/main/webapp/static`，模板在 `WEB-INF/view`

---

*最后更新: 2025年9月9日*
