# 技术栈：

## 前端
- antd
- react（web端）
- Vant（微信小程序）

## 后端
- spring boot
- mysql
- mongoDB
- REST API
- Authentication
- Authorization
- Uint Test
  unittest原名为PyUnit，是由java的JUnit衍生而来。对于单元测试，需要设置预先条件，对比预期结果和实际结果  
- CQRS/读写分离
  读写分离：为了确保数据库产品的稳定性，很多数据库拥有双机热备功能。也就是，第一台数据库服务器，是对外提供增删改业务的生产服务器；第二台数据库服务器，主要进行读的操作
  CQRS：CQRS 是一个读写分离的架构思想，全称是：Command Query Responsibility Segregation，即命令查询职责分离，表示在架构层面，将一个系统分为写入（命令）和查询两部分。一个命令表示一种意图，表示命令系统做什么修改，命令的执行结果通常不需要返回；一个查询表示向系统查询数据并返回。读写两边可以用不同的架构实现，方便实现 CQ 两端的分别优化  
- Performance Test
  对代码性能进行测试
- Continuous Integration  
  持续集成，即频繁的将代码集成到主干，并且每次都通过自动化的构建来验证，从而尽快的发现集成错误，以达到让产品在保持高质量的同时快速迭代的目的。
- Microservece  
  将整个Web应用组织为一系列小的Web服务。这些小的Web服务可以独立地编译及部署，并通过各自暴露的API接口相互通讯。它们彼此相互协作，作为一个整体为用户提供功能，却可以独立地进行和进行拓展，便于团队协作开发。  
- Container Ochestration/Kubernets
