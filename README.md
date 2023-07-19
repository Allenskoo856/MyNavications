# WebStack-Guns
 ❤️ 一个开源的网址导航网站项目

一个开源的网址导航网站项目，具备完整的前后台，您可以拿来制作自己的网址导航，前端懒加载。

## 运行

### Docker-compose 快速部署（推荐）



### 本地运行开发
克隆代码：

```shell
git clone git@github.com:Nikati/WebStack-XML-Guns.git
```

编辑配置：

```
application.yml
```

```
上传文件路径，注意windows环境和linux环境：
file-upload-path
如需显示初始网站图标请把Webstack-Guns/src/main/webapp/static/tmp下的图片复制到上传文件路径
```

```
c:/tmp

数据库连接，用户名密码：
url
username
password
...
```

新建数据库(utf8mb4)，导入数据：

```shell
webstack.sql
```

启动完成：http://127.0.0.1:8000

### 使用

后台地址：http://127.0.0.1:8000/admin

默认用户：admin

默认密码：111111

### Docker 快速部署
```shell
$ docker pull xcallen/mynavications:latest

$ docker run -d -p 8080:8000  -e "DB_IP=yourIp DB_USERNAME=yourName DB_PASSWORD=yourPass" xcallen/mynavications:latest
```




