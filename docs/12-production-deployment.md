# 生产环境部署

本文档给出在生产环境部署 MyNavications 的建议和操作步骤，包含性能、安全与运维建议。

## 环境准备

- JDK 1.8+
- Maven 3.x
- MySQL 8.0+（主从或主主架构）
- Nginx（反向代理、静态资源）
- Docker（推荐容器化部署）
- Redis（可选，替代 EhCache 实现分布式缓存）

## 基本部署步骤

1. 构建 JAR 包：
```bash
mvn clean package -DskipTests
```
2. 拷贝 JAR 到服务器：
```bash
scp target/Webstack-Guns-nkt-1.0.jar user@server:/opt/mynavications/
```
3. 创建 systemd 服务（可选）：
```ini
[Unit]
Description=MyNavications Service
After=network.target

[Service]
User=www-data
WorkingDirectory=/opt/mynavications
ExecStart=/usr/bin/java -jar /opt/mynavications/Webstack-Guns-nkt-1.0.jar
SuccessExitStatus=143
Restart=on-failure
RestartSec=10

[Install]
WantedBy=multi-user.target
```
4. 配置 Nginx：
```nginx
server {
    listen 80;
    server_name mynavications.example.com;

    location / {
        proxy_pass http://127.0.0.1:8000;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }

    location /static/ {
        alias /opt/mynavications/static/;
    }
}
```

## 性能建议

- 使用负载均衡器（Nginx/HAProxy）分发流量
- 数据库使用读写分离、慢查询分析和索引优化
- 使用 Redis 缓存热数据，减少数据库压力
- 静态资源使用 CDN

## 安全建议

- 启用 HTTPS（TLS）并强制 HSTS
- 采用防火墙限制数据库访问，仅允许应用服务器访问
- 定期更新依赖并进行漏洞扫描

## 灰度与回滚

- 使用蓝绿 / 灰度发布策略逐步切换流量
- 保持历史版本的 JAR，提供快速回滚方法

## 运维监控

- 指标：CPU、内存、响应时间、QPS、错误率
- 日志：集中采集（ELK/EFK）
- 告警：Prometheus + Alertmanager 或其他告警系统

---

*最后更新: 2025年9月9日*
