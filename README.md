## 高并发秒杀系统及其优化
## 开发工具 
初始为Eclipse，后期转为使用IntelliJ IDEA开发
## 开发环境				
SpringBoot+Maven+Mybatis+Redis+RabbitMQ 

目前版本Redis和RabbitMQ为本机环境

登录地址：http://localhost:8080/login/to_login

商品秒杀列表地址：http://localhost:8080/goods/to_list

## 高并发优化
1. 使用分布式Seesion，让多台服务器可以响应。
2. 使用redis做缓存提高访问速度和并发量，减少数据库的压力。
3. 使用页面静态化，缓存页面至浏览器，前后端分离降低服务器压力。
4. 使用消息队列完成异步下单，提升用户体验，削峰和降流。

## 安全性优化
1. 对用户密码使用双重MD5加密与校验，防止用户密码泄露
2. 对秒杀接口地址进行隐藏，防止恶意刷单
3. 接口限流，同一用户在一段时间内仅能点击有限次秒杀按钮，防止恶意刷接口
4. 人机验证，采用数学算式图片验证码，在缓解流量高峰的同时防止机器人刷单

## 以下图片为本机实测
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


