## 高并发秒杀系统及其优化
## 开发工具 
初始为Eclipse，后期转为使用IntelliJ IDEA开发
## 开发环境				
SpringBoot+Maven+Mybatis+Redis+RabbitMQ 

| JDK |Maven | Mysql |SpringBoot | redis |RabbitMQ|
|--|--|--|--|--|--|
|1.8 | 3.2.2 | 5.5 | 1.5.9.RELEASE | 3.2 |3.7.14| 

目前版本Redis和RabbitMQ为本地实现
启动Main后：
登录地址：http://localhost:8080/login/to_login
商品秒杀列表地址：http://localhost:8080/goods/to_list

## 项目描述
1. 使用分布式Seesion，让多台服务器可以响应。
2. 使用redis做缓存提高访问速度和并发量，减少数据库压力。
3. 使用页面静态化，缓存页面至浏览器，前后端分离降低服务器压力。
4. 使用消息队列完成异步下单，提升用户体验，削峰和降流。
5. 安全性优化：双重md5密码校验，秒杀接口地址的隐藏，接口限流防刷，数学公式验证码。

## 图片演示
登录页面

![Image text](https://github.com/nkai141119/seckill_pro/blob/master/showimgs/login.png)

商品列表页面

![Image text](https://github.com/nkai141119/seckill_pro/blob/master/showimgs/list.png)

商品详情页面

![Image text](https://github.com/nkai141119/seckill_pro/blob/master/showimgs/goodsdetail.png)

商品秒杀倒计时

![Image text](https://github.com/nkai141119/seckill_pro/blob/master/showimgs/wait.png)

成功秒杀页面

![Image text](https://github.com/nkai141119/seckill_pro/blob/master/showimgs/miaoshasuccess.png)


