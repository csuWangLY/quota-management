# 额度管理系统

## 模型设计

1.  设计用户模型，该模型绑定平台的单个用户。
2.  额度账户模型，该模型和用户模型n：1的关系，即一个用户可创建多个不同类型的额度账户。

## 业务流程

### 新增/修改额度账户

```mermaid
flowchart LR
检查请求参数--> 开启事务--> 对用户加锁 --> 判断用户是否当前已有该类型额度账户 --> 新增或修改额度账户

```

### 新增额度账户

```mermaid
flowchart LR
组装额度账户信息 --> 插入额度账户信息
```

### 修改额度账户

```mermaid
flowchart LR
锁额度账户--> 检查修改前后对于已使用额度和可用额度影响 --> 修改数据库中数据
```

