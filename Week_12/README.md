# 学习笔记

## Week12 作业题目：
### 1.（必做）配置 redis 的主从复制，sentinel 高可用，Cluster 集群。
```
redis-server redis6379.conf
redis-server redis6381.conf
redis-server redis6382.conf
redis-server redis6383.conf
redis-server redis6384.conf

redis-cli --cluster create 127.0.0.1:6379  127.0.0.1:6381 127.0.0.1:6382  127.0.0.1:6383 127.0.0.1:6384 --cluster-replicas 0

>>> Performing hash slots allocation on 5 nodes...
Master[0] -> Slots 0 - 3276
Master[1] -> Slots 3277 - 6553
Master[2] -> Slots 6554 - 9829
Master[3] -> Slots 9830 - 13106
Master[4] -> Slots 13107 - 16383
M: 048f9872188cb6653d4a36b73972aa7e5a23a294 127.0.0.1:6379
slots:[0-3276] (3277 slots) master
M: fcb36e08ffd0a9396901384971b1cf418ebee026 127.0.0.1:6381
slots:[3277-6553] (3277 slots) master
M: 731b7fd46e17a68d99047905410c3b72d325f986 127.0.0.1:6382
slots:[6554-9829] (3276 slots) master
M: a7faa06bbaa44ed5ad4b1abd9281986e28f6ece5 127.0.0.1:6383
slots:[9830-13106] (3277 slots) master
M: 9bea36df122c7440dcf6a1bc5240894e6d68ce60 127.0.0.1:6384
slots:[13107-16383] (3277 slots) master
Can I set the above configuration? (type 'yes' to accept): yes
>>> Nodes configuration updated
>>> Assign a different config epoch to each node
>>> Sending CLUSTER MEET messages to join the cluster
Waiting for the cluster to join
..
>>> Performing Cluster Check (using node 127.0.0.1:6379)
M: 048f9872188cb6653d4a36b73972aa7e5a23a294 127.0.0.1:6379
slots:[0-3276] (3277 slots) master
M: 731b7fd46e17a68d99047905410c3b72d325f986 127.0.0.1:6382
slots:[6554-9829] (3276 slots) master
M: a7faa06bbaa44ed5ad4b1abd9281986e28f6ece5 127.0.0.1:6383
slots:[9830-13106] (3277 slots) master
M: fcb36e08ffd0a9396901384971b1cf418ebee026 127.0.0.1:6381
slots:[3277-6553] (3277 slots) master
M: 9bea36df122c7440dcf6a1bc5240894e6d68ce60 127.0.0.1:6384
slots:[13107-16383] (3277 slots) master
[OK] All nodes agree about slots configuration.
>>> Check for open slots...
>>> Check slots coverage...
[OK] All 16384 slots covered.

```
