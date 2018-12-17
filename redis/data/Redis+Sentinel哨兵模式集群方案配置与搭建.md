# 搭建Redis-Sentinel 集群环境
## 环境准备
- linux 系统 centOS 7.3 虚拟机一台(也可以放在多个虚拟机上)
- 端口分配

| 实例名称 | ip | 端口 |
|---|---|---|
| redis-master | 127.0.0.1 | 7501|
| redis-slave1| 127.0.0.1 | 7502|
| redis-slave2 | 127.0.0.1 | 7503|
| redis-slave3 | 127.0.0.1 | 7504|
| redis-sentinel1 | 127.0.0.1 | 7505|
| redis-sentinel2| 127.0.0.1 | 7506|
| redis-sentinel3| 127.0.0.1 | 7507|

- 整体架构拓扑图

![在这里插入图片描述](https://img-blog.csdnimg.cn/2018121617191038.?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzI2Nzg4NTkz,size_16,color_FFFFFF,t_70)

## 安装Redis

1. 进入到想安装的文件路径中

```
cd /root/jiachang/redis
```
2.  下载redis
```
wget http://download.redis.io/releases/redis-3.2.1.tar.gz
```
3. 解压

```
tar -xzf redis-3.2.1.tar.gz 
```

4. 进入解压后的文件夹

```
cd redis-3.2.1 
```
5. 检查是否安装gcc,没有的话进行安装，并编译redis源码

```
// 检查是否安装
yum list installed | grep gcc
// 安装
yum -y install gcc
// 编译
make [MALLOC=libc]
make install 
```
6. 在/usr/local/ 下新建一个目录redis-sentinel，然后在此目录下新建7501/ 7502/ 7503/ 7504/ 7505/ 7506/ 7507/七个目录

```
mkdir /usr/local/redis-sentinel 
mkdir /usr/local/redis-sentinel/{7501,7502,7503,7504,7505,7506,7507}
```

7. 将redis安装目录下的reids.conf，拷贝到前4个目录下，分别命名为：redis-7501.conf redis-7502.conf redis-7503.conf redis-7504.conf

```
cp /root/redis-3.2.1/redis.conf /usr/local/redis-sentinel/7501/redis-7501.conf
cp /root/redis-3.2.1/redis.conf /usr/local/redis-sentinel/7502/redis-7502.conf
cp /root/redis-3.2.1/redis.conf /usr/local/redis-sentinel/7503/redis-7503.conf
cp /root/redis-3.2.1/redis.conf /usr/local/redis-sentinel/7504/redis-7504.conf
```

8. 修改redis的conf配置文件，主从的配置几乎都一样(有些地方还是有区别的)，以7501端口的redis实例为例进行分析

```
## 开启守护模式 
daemonize yes
pidfile /var/run/redis_7501.pid
## 端口号
port 7501
bind 127.0.0.1
## redis运行产生的日志
logfile "./redis-7501.log"
#数据目录，数据库的写入会在这个目录。rdb、aof文件也会写在这个目录
dir "/usr/local/redis-sentinel/7501"
## redis配置密码的话，需要以下配置
masterauth "123456"
requirepass "123456"
## 打开aof持久化
appendonly yes
```
其他子节点对应的conf文件也要进行修改，跟上面差不多，不一样的值：`port、pidfile 、logfile 、dir`  需要新增的地方：

```
# 指定所属的master  
slaveof 127.0.0.1 7501
# 指定slave只读  
slave-read-only yes
```

9.   启动redis实例

```
redis-server /usr/local/redis-sentinel/7501/redis-7501.conf
redis-server /usr/local/redis-sentinel/7502/redis-7502.conf
redis-server /usr/local/redis-sentinel/7503/redis-7503.conf
redis-server /usr/local/redis-sentinel/7504/redis-7504.conf
```

 10. 检查Redis是否启动成功
1> 使用ping命令
```
redis-cli -h 127.0.0.1 -p 7501 ping
PONG
```
2> 查看redis进程

```
ps -ef | grep redis

root      7795     1  0 16:34 ?        00:00:17 redis-server 127.0.0.1:7501
root     31756     1  0 12:09 ?        00:00:25 ./redis-server 127.0.0.1:7502
root     31766     1  0 12:09 ?        00:00:29 ./redis-server 127.0.0.1:7503
root     31777     1  0 12:09 ?        00:00:25 ./redis-server 127.0.0.1:7504
```
11. 启动成功后，查看Redis的主从关系
- 使用`redis-cli -h 127.0.0.1 -p 7501` 登录到Redis实例上
- 使用 `info replication ` 查看主从关系信息
主节点信息如下：
```
[root@localhost ~]# redis-cli -h 127.0.0.1 -p 7501
127.0.0.1:7501> info replication
# Replication
role:master // 代表当前节点是从节点
connected_slaves:3
// 下面是从节点的信息
slave0:ip=127.0.0.1,port=7504,state=online,offset=12880468,lag=1
slave1:ip=127.0.0.1,port=7502,state=online,offset=12880600,lag=1
slave2:ip=127.0.0.1,port=7503,state=online,offset=12880600,lag=1
master_repl_offset:12880732
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:11832157
repl_backlog_histlen:1048576
```

从节点信息如下：

```
[root@localhost ~]# redis-cli -h 127.0.0.1 -p 7502
127.0.0.1:7502> info replication
# Replication
role:slave // 代表当前节点是从节点
// 下面是主节点的信息
master_host:127.0.0.1
master_port:7501
master_link_status:up
master_last_io_seconds_ago:1
master_sync_in_progress:0
slave_repl_offset:12935376
slave_priority:100
slave_read_only:1
connected_slaves:0
master_repl_offset:0
repl_backlog_active:0
repl_backlog_size:1048576
repl_backlog_first_byte_offset:0
repl_backlog_histlen:0
```
12. 其他测试

```
1. 从节点只读权限
127.0.0.1:7502> set zhangsan www
(error) READONLY You can't write against a read only slave.
2. 主节点更新数据后，从节点进行查看
 [root@localhost ~]# redis-cli -h 127.0.0.1 -p 7501
127.0.0.1:7501> set key1 value1
OK
127.0.0.1:7501> exit
[root@localhost ~]# redis-cli -h 127.0.0.1 -p 7502
127.0.0.1:7502> get key1
"value1"

```

## 搭建Sentinel
1. 将redis安装目录下的sentinel.conf拷贝到7505/ 7506/和7507/目录下分别命名： sentinel-7505.conf sentinel-7506.conf sentinel-7507.conf

```
cp /root/jiachang/redis/redis-3.2.1/sentinel.conf /usr/local/redis-sentinel/7505/sentinel-7505.conf
cp /root/jiachang/redis/redis-3.2.1/sentinel.conf /usr/local/redis-sentinel/7505/sentinel-7506.conf
cp /root/jiachang/redis/redis-3.2.1/sentinel.conf /usr/local/redis-sentinel/7505/sentinel-7507.conf
```
2. 修改配置项

```
daemonize yes 
port 7505
#指定工作目录
dir "/usr/local/redis-sentinel/7505"
logfile "./sentinel-7505.log" 
#指定别名  主节点地址  端口  哨兵个数（有几个哨兵监控到主节点宕机执行转移 判断主节点失败至少需要2个Sentinel节点节点同意）
sentinel monitor mymaster 127.0.0.1 7501 2
#如果哨兵3s内没有收到主节点的心跳，哨兵就认为主节点宕机了，默认是30秒  
sentinel down-after-milliseconds mymaster 3000
## 当Sentinel节点集合对主节点故障判定达成一致时，Sentinel领导者节点会做故障转移操作，选出新的主节点，原来的从节点会向新的主节点发起复制操作，限制每次向新的主节点发起复制操作的从节点个数为1
sentinel parallel-syncs mymaster 1
# 故障转移超时时间
sentinel failover-timeout mymaster 10000 
#配置连接redis主节点密码  
sentinel auth-pass mymaster 123456  
```
3. 启动3个Sentinel

```
redis-sentinel /usr/local/redis-sentinel/7505/sentinel-7505.conf
redis-sentinel /usr/local/redis-sentinel/7506/sentinel-7506.conf
redis-sentinel /usr/local/redis-sentinel/7507/sentinel-7507.conf
```
还有一种启动命令
```
redis-server /usr/local/redis-sentinel/7505/sentinel-7505.conf --sentinel
```
4. 此时再去查看进程，发现sentinel已经启动成功

```
root      6390     1  0 15:48 ?        00:00:29 redis-sentinel *:7505 [sentinel]
root      6426     1  0 15:49 ?        00:00:28 redis-sentinel *:7506 [sentinel]
root      6434     1  0 15:49 ?        00:00:29 redis-sentinel *:7507 [sentinel]
root      7795     1  0 16:34 ?        00:00:17 redis-server 127.0.0.1:7501
root     16257 29348  0 21:28 pts/1    00:00:00 grep --color=auto redis
root     31756     1  0 12:09 ?        00:00:25 ./redis-server 127.0.0.1:7502
root     31766     1  0 12:09 ?        00:00:29 ./redis-server 127.0.0.1:7503
root     31777     1  0 12:09 ?        00:00:25 ./redis-server 127.0.0.1:7504
```
5. 启动成功后的变化
- 自动发现了其他的sentinel节点
	1>  sentinel 启动日志查看
```
tail -200f /usr/local/redis-sentinel/7505/sentinel-7505.log
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181216111538499.)
   2> 在sentinel的配置文件最下面会多出如下配置
![](https://img-blog.csdnimg.cn/20181216161031839.)

6. Sentinel一些命令介绍 
要想使用Sentinel，必须使用redis-cli命令进入到Sentinel

```
redis-cli -h 127.0.0.1 -p 7505
如果有密码 后面再加上 -a 密码
```
- info ： sentinel的基本状态信息 (例如监控的redis主节点的信息)
- sentinel masters  ： 监控的主节点的信息

```
 1) "name"
 2) "mymaster"
 3) "ip"
 4) "127.0.0.1"
 5) "port"
 6) "7501" （这里就是主节点的端口信息）
 7) "runid"
 8) "06fce2f40c76e0a984d304d519fe6e437ff88aa6"
 9) "flags"
10) "master"
.........
```
- sentinel slaves mymaster 	： 监控的从节点的信息(下面会依次列出3个从节点的详细信息)

## 故障转移试验
1. 我们将master节点-7501 强制kill掉

```
ps -ef|grep redis
kill -9 pid
```
2. 查看一下Sentinel节点监控的主节点信息
- 当将Redis主节点kill掉以后，Redis Sentinel对主节点进行客观下线（Objectively Down， 简称 ODOWN）的判断，确认主节点不可达，则通知从节点中止复制主节点的操作。
```
127.0.0.1:7505> sentinel masters
1)  1) "name"
    2) "mymaster"
    3) "ip"
    4) "127.0.0.1"
    5) "port"
    6) "7503"
    7) "runid"
    8) "06fce2f40c76e0a984d304d519fe6e437ff88aa6"
    9) "flags"
   10) "master"
   11) "link-pending-commands"
	........
```
此时发现 7503已经升级为master
sentinel日志也可以看得出来，这个时候Sentinel的配置文件也修改了。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181216163734611.?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzI2Nzg4NTkz,size_16,color_FFFFFF,t_70)

3.   继续启动7501节点后，变为7503的子节点，跟正常一样。
