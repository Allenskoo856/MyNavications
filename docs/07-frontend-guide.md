# 前端开发指南

该文档介绍前端资源组织、常见开发流程和静态资源构建建议。

## 目录说明

- `src/main/webapp/static/css` - 样式文件
- `src/main/webapp/static/js` - 脚本文件
- `src/main/webapp/WEB-INF/view` - Beetl 模板页面
- `src/main/webapp/static/modular` - 各模块独立资源

## 开发规范

- 使用 BEM 或语义化类名编写 CSS
- 所有 JS 采用模块化管理，尽量避免全局变量污染
- 静态资源更新后清理浏览器缓存或使用版本号查询参数

## 常见任务

### 引入新的第三方库
1. 将库文件放到 `static/plugins` 或使用 CDN 引入
2. 在对应模板或页面 `_header.html` 中引入脚本或样式

### 调试静态资源
- 前端文件变更后，直接刷新页面即可（若使用 devtools，模板变更会自动加载）

### 图片与多媒体
- 将图片放入 `static/img`，并使用懒加载库 `jquery.lazyload` 优化加载性能

## 构建与压缩建议

虽然项目未使用前端构建工具（如 Webpack），建议在生产环境中：
- 合并并压缩 CSS/JS
- 对图片做压缩处理
- 使用 CDN 托管常用第三方库

## 与后端交互

- 所有数据交互通过 REST API 完成
- 登录和需要权限的接口需在请求头中携带 `Authorization: Bearer {token}`
- 推荐使用 Fetch / Axios（若引入）进行异步请求

---

*最后更新: 2025年9月9日*
