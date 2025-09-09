# 二次开发指南

本指南说明如何基于 MyNavications 进行二次开发：添加新模块、扩展 API、定制前端等。

## 新增模块步骤

1. 在 `modular/` 下创建新模块目录，例如 `modular/blog`。
2. 创建实体（Model）、Mapper、Service、Controller 等标准层次结构。
3. 在 `WebConfig` 中如有必要注册路由或静态资源。

## 标准代码示例

### 实体示例
```java
@TableName("blog")
public class Blog extends BaseEntity {
    private Integer id;
    private String title;
    private String content;
}
```

### Mapper 示例
```java
public interface BlogMapper extends BaseMapper<Blog> {
}
```

### Service 示例
```java
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {
}
```

### Controller 示例
```java
@Controller
@RequestMapping("/blog")
public class BlogController extends BaseController {
    @Autowired
    private IBlogService blogService;

    @RequestMapping("/list")
    @ResponseBody
    public Object list() {
        return blogService.selectList(null);
    }
}
```

## 注意事项

- 参数校验请使用 `@Valid` 与 `BindingResult`
- 返回结果统一使用项目的 Result 封装类
- 对涉及权限的接口添加 `@Permission` 注解

---

*最后更新: 2025年9月9日*
