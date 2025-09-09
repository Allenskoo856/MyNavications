# API接口文档

## 🌐 API概述

MyNavications 提供了完整的 RESTful API 接口，支持前后端分离开发。所有API都采用JSON格式进行数据交换，使用JWT Token进行身份验证。

## 🔐 认证机制

### 获取访问令牌

**接口地址**: `POST /gunsApi/auth`

**请求参数**:
```json
{
  "username": "admin",
  "password": "111111"
}
```

**响应示例**:
```json
{
  "code": 200,
  "message": "登录成功",
  "data": {
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY5NDc2ODQwMH0...",
    "expireTime": 1694768400000,
    "user": {
      "userId": 1,
      "account": "admin",
      "name": "管理员"
    }
  }
}
```

### 使用访问令牌

在所有需要认证的API请求头中添加：
```
Authorization: Bearer {token}
```

## 👥 用户管理 API

### 获取用户列表

**接口地址**: `GET /mgr/list`

**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|------|------|
| name | String | 否 | 用户名模糊查询 |
| page | Integer | 否 | 页码，默认1 |
| size | Integer | 否 | 每页大小，默认10 |

**请求示例**:
```bash
GET /mgr/list?name=admin&page=1&size=10
Authorization: Bearer {token}
```

**响应示例**:
```json
{
  "code": 200,
  "message": "查询成功",
  "data": {
    "total": 1,
    "list": [
      {
        "userId": 1,
        "account": "admin",
        "name": "管理员",
        "email": "admin@example.com",
        "phone": "13800138000",
        "status": 1,
        "createtime": "2024-01-01 10:00:00",
        "roleName": "超级管理员",
        "deptName": "总部"
      }
    ]
  }
}
```

### 添加用户

**接口地址**: `POST /mgr/add`

**请求参数**:
```json
{
  "account": "newuser",
  "password": "123456",
  "name": "新用户",
  "email": "newuser@example.com",
  "phone": "13800138001",
  "sex": 1,
  "roleid": "2",
  "deptid": 1
}
```

**响应示例**:
```json
{
  "code": 200,
  "message": "添加成功",
  "data": null
}
```

### 编辑用户

**接口地址**: `POST /mgr/edit`

**请求参数**:
```json
{
  "userId": 2,
  "account": "newuser",
  "name": "修改后的用户名",
  "email": "modified@example.com",
  "phone": "13800138002",
  "sex": 2,
  "roleid": "3",
  "deptid": 2
}
```

### 删除用户

**接口地址**: `POST /mgr/delete`

**请求参数**:
```json
{
  "userId": 2
}
```

### 重置密码

**接口地址**: `POST /mgr/reset`

**请求参数**:
```json
{
  "userId": 2,
  "password": "newpassword"
}
```

## 🔒 角色管理 API

### 获取角色列表

**接口地址**: `GET /role/list`

**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|------|------|
| roleName | String | 否 | 角色名模糊查询 |

**响应示例**:
```json
{
  "code": 200,
  "message": "查询成功",
  "data": [
    {
      "roleId": 1,
      "name": "超级管理员",
      "tips": "超级管理员角色",
      "num": 1,
      "deptName": "总部"
    }
  ]
}
```

### 添加角色

**接口地址**: `POST /role/add`

**请求参数**:
```json
{
  "name": "编辑员",
  "tips": "内容编辑角色",
  "deptid": 1
}
```

### 分配权限

**接口地址**: `POST /role/setAuthority`

**请求参数**:
```json
{
  "roleId": 2,
  "ids": "1,2,3,4,5"
}
```

## 🌐 网站管理 API

### 获取网站列表

**接口地址**: `GET /site/list`

**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|------|------|
| title | String | 否 | 网站标题模糊查询 |
| categoryId | Integer | 否 | 分类ID |
| page | Integer | 否 | 页码，默认1 |
| size | Integer | 否 | 每页大小，默认10 |

**请求示例**:
```bash
GET /site/list?title=百度&categoryId=1&page=1&size=10
Authorization: Bearer {token}
```

**响应示例**:
```json
{
  "code": 200,
  "message": "查询成功",
  "data": {
    "total": 1,
    "list": [
      {
        "id": 1,
        "categoryId": 1,
        "title": "百度",
        "url": "https://www.baidu.com",
        "description": "全球最大的中文搜索引擎",
        "logo": "https://www.baidu.com/favicon.ico",
        "sort": 1,
        "status": 1,
        "categoryName": "常用推荐",
        "createTime": "2024-01-01 10:00:00"
      }
    ]
  }
}
```

### 添加网站

**接口地址**: `POST /site/add`

**请求参数**:
```json
{
  "categoryId": 1,
  "title": "Google",
  "url": "https://www.google.com",
  "description": "全球最大的搜索引擎",
  "logo": "https://www.google.com/favicon.ico",
  "sort": 2
}
```

**响应示例**:
```json
{
  "code": 200,
  "message": "添加成功",
  "data": null
}
```

### 编辑网站

**接口地址**: `POST /site/edit`

**请求参数**:
```json
{
  "id": 1,
  "categoryId": 1,
  "title": "百度搜索",
  "url": "https://www.baidu.com",
  "description": "百度是拥有强大互联网基础的领先AI公司",
  "logo": "https://www.baidu.com/favicon.ico",
  "sort": 1,
  "status": 1
}
```

### 删除网站

**接口地址**: `POST /site/delete`

**请求参数**:
```json
{
  "id": 1
}
```

### 批量删除网站

**接口地址**: `POST /site/batchDelete`

**请求参数**:
```json
{
  "ids": "1,2,3"
}
```

## 📂 分类管理 API

### 获取分类列表

**接口地址**: `GET /category/list`

**响应示例**:
```json
{
  "code": 200,
  "message": "查询成功",
  "data": [
    {
      "id": 1,
      "parentId": 0,
      "title": "常用推荐",
      "icon": "fa-star-o",
      "sort": 1,
      "levels": 1,
      "children": [
        {
          "id": 5,
          "parentId": 1,
          "title": "搜索引擎",
          "icon": "fa-search",
          "sort": 1,
          "levels": 2
        }
      ]
    }
  ]
}
```

### 获取分类树

**接口地址**: `GET /category/tree`

**响应示例**:
```json
{
  "code": 200,
  "message": "查询成功",
  "data": [
    {
      "id": 1,
      "pId": 0,
      "name": "常用推荐",
      "open": true,
      "checked": false
    }
  ]
}
```

### 添加分类

**接口地址**: `POST /category/add`

**请求参数**:
```json
{
  "parentId": 0,
  "title": "开发工具",
  "icon": "fa-code",
  "sort": 5
}
```

### 编辑分类

**接口地址**: `POST /category/edit`

**请求参数**:
```json
{
  "id": 1,
  "parentId": 0,
  "title": "常用推荐",
  "icon": "fa-star",
  "sort": 1
}
```

### 删除分类

**接口地址**: `POST /category/delete`

**请求参数**:
```json
{
  "id": 1
}
```

## 📋 菜单管理 API

### 获取菜单列表

**接口地址**: `GET /menu/list`

**响应示例**:
```json
{
  "code": 200,
  "message": "查询成功",
  "data": [
    {
      "menuId": 1,
      "code": "system",
      "name": "系统管理",
      "icon": "fa-cogs",
      "url": "#",
      "num": 1,
      "levels": 1,
      "ismenu": 1,
      "status": 1
    }
  ]
}
```

### 获取菜单树

**接口地址**: `GET /menu/tree`

### 添加菜单

**接口地址**: `POST /menu/add`

**请求参数**:
```json
{
  "code": "newmenu",
  "pcode": "system", 
  "name": "新菜单",
  "icon": "fa-list",
  "url": "/newmenu",
  "num": 10,
  "ismenu": 1
}
```

## 🏢 部门管理 API

### 获取部门列表

**接口地址**: `GET /dept/list`

### 获取部门树

**接口地址**: `GET /dept/tree`

### 添加部门

**接口地址**: `POST /dept/add`

**请求参数**:
```json
{
  "simplename": "技术部",
  "fullname": "技术研发部",
  "num": 1,
  "pid": 0,
  "tips": "负责技术研发工作"
}
```

## 📝 日志管理 API

### 获取操作日志

**接口地址**: `GET /log/list`

**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|------|------|
| logName | String | 否 | 日志名称 |
| startTime | String | 否 | 开始时间 |
| endTime | String | 否 | 结束时间 |

**响应示例**:
```json
{
  "code": 200,
  "message": "查询成功",
  "data": {
    "total": 10,
    "list": [
      {
        "id": 1,
        "logname": "用户登录",
        "userid": 1,
        "username": "admin",
        "createtime": "2024-01-01 10:00:00",
        "succeed": "成功",
        "message": "用户登录成功"
      }
    ]
  }
}
```

### 获取登录日志

**接口地址**: `GET /loginLog/list`

**响应示例**:
```json
{
  "code": 200,
  "message": "查询成功", 
  "data": {
    "total": 5,
    "list": [
      {
        "id": 1,
        "logname": "管理员登录",
        "userid": 1,
        "username": "admin",
        "createtime": "2024-01-01 10:00:00",
        "succeed": "成功",
        "ip": "192.168.1.100"
      }
    ]
  }
}
```

## 📢 通知管理 API

### 获取通知列表

**接口地址**: `GET /notice/list`

### 添加通知

**接口地址**: `POST /notice/add`

**请求参数**:
```json
{
  "title": "系统维护通知",
  "content": "系统将于今晚进行维护，请提前保存工作。"
}
```

## 📚 字典管理 API

### 获取字典列表

**接口地址**: `GET /dict/list`

### 添加字典

**接口地址**: `POST /dict/add`

**请求参数**:
```json
{
  "name": "用户状态",
  "tips": "用户账号状态字典"
}
```

## 🔍 搜索 API

### 网站搜索

**接口地址**: `GET /search`

**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|------|------|
| keyword | String | 是 | 搜索关键词 |
| categoryId | Integer | 否 | 分类ID |

**请求示例**:
```bash
GET /search?keyword=百度&categoryId=1
```

**响应示例**:
```json
{
  "code": 200,
  "message": "搜索成功",
  "data": [
    {
      "id": 1,
      "title": "百度",
      "url": "https://www.baidu.com",
      "description": "全球最大的中文搜索引擎",
      "logo": "https://www.baidu.com/favicon.ico",
      "categoryName": "常用推荐"
    }
  ]
}
```

## 📊 统计 API

### 获取统计数据

**接口地址**: `GET /statistics`

**响应示例**:
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "userCount": 10,
    "siteCount": 150,
    "categoryCount": 20,
    "todayLoginCount": 5
  }
}
```

## ❌ 错误码说明

| 错误码 | 说明 |
|-------|------|
| 200 | 请求成功 |
| 400 | 请求参数错误 |
| 401 | 未授权，需要登录 |
| 403 | 权限不足 |
| 404 | 资源不存在 |
| 500 | 服务器内部错误 |
| 1001 | 用户名或密码错误 |
| 1002 | Token已过期 |
| 1003 | 权限不足 |
| 2001 | 参数验证失败 |
| 2002 | 数据已存在 |
| 2003 | 数据不存在 |

## 📝 请求示例

### cURL 示例

```bash
# 获取Token
curl -X POST "http://localhost:8000/gunsApi/auth" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "username=admin&password=111111"

# 获取用户列表
curl -X GET "http://localhost:8000/mgr/list" \
  -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9..."

# 添加网站
curl -X POST "http://localhost:8000/site/add" \
  -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9..." \
  -H "Content-Type: application/json" \
  -d '{
    "categoryId": 1,
    "title": "GitHub",
    "url": "https://github.com",
    "description": "全球最大的代码托管平台"
  }'
```

### JavaScript 示例

```javascript
// 获取Token
const loginResponse = await fetch('/gunsApi/auth', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded',
  },
  body: 'username=admin&password=111111'
});

const loginData = await loginResponse.json();
const token = loginData.data.token;

// 获取网站列表
const sitesResponse = await fetch('/site/list', {
  headers: {
    'Authorization': `Bearer ${token}`
  }
});

const sitesData = await sitesResponse.json();
console.log(sitesData.data.list);
```

## 🔧 Swagger 文档

访问 `http://localhost:8000/swagger-ui.html` 可以查看完整的 Swagger API 文档，包含：

- 所有接口的详细说明
- 参数类型和约束
- 响应示例
- 在线测试功能

---

*最后更新: 2025年9月9日*
