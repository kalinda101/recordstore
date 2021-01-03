-- /////////////////////////////////////////////////////////
--  创建数据库
-- /////////////////////////////////////////////////////////

-- 创建字符集为utf-8的RecordStore数据库
CREATE SCHEMA `RecordStore` DEFAULT CHARACTER SET utf8;

-- /////////////////////////////////////////////////////////
-- 用户模块
-- /////////////////////////////////////////////////////////

-- 创建用户表
create table `RecordStore`.t_user(
`id` int primary key auto_increment,
`username` varchar(20) not null unique,
`password` varchar(32) not null,
`email` varchar(200),
`registtime` varchar(50) not null,
`updatatime` varchar(50)
);

-- 插入管理员的数据
insert into t_user(`username`,`password`,`email`,`registtime`) values ('admin','admin','admin@test.com',now());

-- /////////////////////////////////////////////////////////
-- 唱片管理模块
-- /////////////////////////////////////////////////////////

-- 创建唱片表
create table `RecordStore`.t_record(
`id` int primary key auto_increment COMMENT 'ID',
`name` varchar(50) not null COMMENT '名称',
`author` varchar(50) not null COMMENT '作者',
`price` decimal(11,2) not null COMMENT '价格',
`sales` int(11) not null COMMENT '销量',
`stock` int(11) not null COMMENT '库存',
`image_path` varchar(200) not null COMMENT '图片地址',
`delflg` char(1) not null default '0' COMMENT '删除标记',
`createtime` TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`updatetime` TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '唱片表';

-- 插入数据
insert into RecordStore.t_record(name,author,price,sales,stock,image_path) values ('Java程序设计','张孝祥','99.99','1','100','static/img/default.jpg');

-- /////////////////////////////////////////////////////////
-- 订单模块
-- /////////////////////////////////////////////////////////

-- 创建订单表
create table RecordStore.t_order(
	`orderid` varchar(50) primary key COMMENT '订单号',
    `createtime` varchar(50) COMMENT '订单创建时间',
    `price` decimal(11,2) COMMENT '订单总金额',
    `status` int COMMENT '订单状态',
    `userid` int COMMENT '用户ID',
    foreign key(`userid`) references t_user(`id`)
);

-- 5.创建订单项表
create table RecordStore.t_orderItem(
	`id` int primary key auto_increment COMMENT 'ID',
    `goodsname` varchar(100) COMMENT '商品名称',
    `goodscount` varchar(100) COMMENT '商品件数',
    `price` decimal(11,2) COMMENT '商品单价',
    `totalprice` decimal(11,2) COMMENT '商品总价格',
    `orderid` varchar(50) COMMENT '订单号',
    foreign key(`orderid`) references t_order(`orderid`)
);


-- /////////////////////////////////////////////////////////
-- 开发过程中可能会用到的一些SQL文
-- /////////////////////////////////////////////////////////

delete from t_user where username = 'gust';

-- 设定当前数据库
use RecordSop;

-- 查询插入后结果
select * from RecordStore.t_user;

-- 修改createtime数据类型
alter  table RecordStore.t_order modify  column `createtime`  varchar(50) COMMENT '订单创建时间';

-- 修改列名称
alter table t_record change `prince` `price` decimal(11,2) not null COMMENT '价格';

-- 删除表结构
drop table `RecordStore`.t_user;

alter  table RecordStore.t_orderItem modify  column `orderid`  varchar(50) COMMENT '订单号';

-- 订单表中插入数据
insert into RecordStore.t_order(orderid, createtime, price, status, userid) values ('1000-20201124160000-00001',current_date,20,0,1);

-- 订单项中插入数据
insert into RecordStore.t_orderItem(goodsname, goodscount, price, totalprice, orderid) values("java入门1",2,10,20,'1000-20201124160000-00001');

select * from t_order;

-- 查询当前用户所有订单
select orderid,createtime,price,status,userid from t_order where userid = 1;

-- 通过订单号查询订单的商品信息
select id, goodsname, goodscount, price, totalprice, orderid from t_orderItem where orderid ='16062948375884';

-- 管理员查询所有订单
select orderid,createtime,price,status,userid from t_order;

-- 更新订单状态
update t_order set status = 2 where orderid = '16062937401044';
