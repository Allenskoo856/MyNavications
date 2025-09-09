# 缓存策略

## 概述

项目默认使用 EhCache 作为本地缓存，配置文件为 `src/main/resources/ehcache.xml`。EhCache 适用于单机或小规模部署场景。对于分布式部署，推荐将缓存迁移到 Redis。

## EhCache 配置说明

- 缓存主要用于：菜单列表、字典数据、热门网站列表、权限信息等读多写少的数据。
- 推荐对热数据使用较长的过期策略（例如 1 小时），而对临时数据使用短过期策略（例如 5 分钟）。

## 常用缓存键设计

- `menu_all` - 所有菜单树（按角色过滤后缓存）
- `dict_{dictName}` - 单个字典数据
- `category_all` - 分类树
- `site_hot_{categoryId}` - 分类的热门网站列表
- `user_{userId}_permissions` - 用户权限集合

## 缓存穿透与击穿处理

- 缓存穿透：对不存在的 key 设置空对象缓存并设置短期失效，避免频繁查询数据库。
- 缓存击穿：对热点 key 使用互斥锁或双层缓存策略（本地 + 远程）防止DB瞬时访问压力。

## 分布式部署建议（Redis）

- 使用 Redis 作为集中式缓存，支持集群模式提高可用性。
- 在分布式场景下，使用 Redisson 或 Spring Cache（Redis）替代 EhCache。
- 使用缓存一致性策略：事件通知（Pub/Sub）或基于版本号的失效策略。

## 缓存失效策略

- 主动失效：在修改数据（如网站、分类、菜单、权限）时，手动清除或更新相关缓存。
- 被动失效：依赖 TTL 设置自动过期。

## 代码示例（缓存注解）

```java
// 获取菜单并缓存
@Cacheable(value = "menu_all", key = "#root.methodName")
public List<Menu> getAllMenus() {
    return menuMapper.selectList(null);
}

// 修改菜单时清除缓存
@CacheEvict(value = "menu_all", allEntries = true)
public void addMenu(Menu menu) {
    menuMapper.insert(menu);
}
```

## 监控与容量规划

- 监控缓存命中率、内存使用量和慢查询日志。
- 根据访问量和数据量调整缓存容量（EhCache）或 Redis 内存设置。

---

*最后更新: 2025年9月9日*
