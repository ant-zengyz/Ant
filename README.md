## ANT:团结的蚂蚁

----------
## 项目说明：
 - 该项目仅仅作为学习使用
 - 一边学习，一边搭脚手架，不喜勿喷

----------
## 项目结构：
 - Ant[项目]
	 - ant-back[后台服务]
	 - ant-core[核心包]
	 - ant-data[数据服务]
	 - ant-eureka[服务注册中心]
	 - pom.xml[主POM文件]

----------
## 包含技术
 - 服务注册中心
	 - springcloud eureka
 - 后台服务
	 - springcloud feign 调用服务接口
	 - springcloudhystric 断路器
	 - shiro 权限框架
	 - shiro-ehcache 权限缓存
	 - thymeleaf 模板引擎
	 - x-admin 前端后台模板
	 - layui UI框架
	 - redis 缓存
 - 数据服务
	 - springcloud eureka-client 服务注册
	 - Mysql 数据库驱动
	 - druid 连接池
	 - jta-atomikos 分布式事务
	 - mybatis-generator 插件
	 - 多数据源

TIPS:慢慢完善中

