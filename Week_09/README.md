# 学习笔记

##作业

### 1 必做 改造自定义 RPC 的程序，提交到 GitHub

- [x] 1）尝试将服务端写死查找接口实现类变成泛型和反射
- [x] 2）尝试将客户端动态代理改成AOP，添加异常处理
- [x] 3）尝试使用Netty+HTTP作为client端传输方式

 [exercise link](https://github.com/allen1128/JAVA-000/tree/main/Week_09/rpcfx)
 
 
### 2 必做 结合 dubbo+hmily，实现一个 TCC 外汇交易处理，代码提交到 GitHub:
 
 
- [x] 1）用户 A 的美元账户和人民币账户都在 A 库，使用 1 美元兑换 7 人民币
- [x] 2）用户 B 的美元账户和人民币账户都在 B 库，使用 7 人民币兑换 1 美元
- [x] 3）设计账户表，冻结资产表，实现上述两个本地事务的分布式事务
          
 [exercise link](https://github.com/allen1128/JAVA-000/tree/main/Week_09/dubbo-hmily)
 