# 实体关系与ER图

<cite>
**本文档引用的文件**  
- [auction_database.sql](file://auction_database.sql)
- [User.java](file://src/main/java/com/qkl/auctionsystem/pojo/entity/User.java)
- [Item.java](file://src/main/java/com/qkl/auctionsystem/pojo/entity/Item.java)
- [BidRecord.java](file://src/main/java/com/qkl/auctionsystem/pojo/entity/BidRecord.java)
- [Order.java](file://src/main/java/com/qkl/auctionsystem/pojo/entity/Order.java)
- [BidRecordMapper.java](file://src/main/java/com/qkl/auctionsystem/mapper/BidRecordMapper.java)
- [ItemMapper.xml](file://src/main/resources/mapper/ItemMapper.xml)
- [OrderMapper.xml](file://src/main/resources/mapper/OrderMapper.xml)
</cite>

## 目录
1. [引言](#引言)
2. [核心实体与字段映射](#核心实体与字段映射)
3. [实体关系分析](#实体关系分析)
4. [ER图（实体关系图）](#er图实体关系图)
5. [外键约束与业务含义](#外键约束与业务含义)
6. [结论](#结论)

## 引言
本文档基于拍卖系统数据库脚本 `auction_database.sql` 和 Java 实体类，详细分析 `user`、`auction_item`、`auction_record` 和 `auction_order` 四张核心表之间的关联关系。通过实体关系图（ER图）清晰展示各实体间的基数与依赖方向，并结合 Java 实体类中的字段映射，解释外键约束的实际业务意义。

**Section sources**  
- [auction_database.sql](file://auction_database.sql#L1-L78)

## 核心实体与字段映射
系统包含四个核心实体：用户（User）、拍品（Item）、竞拍记录（BidRecord）和订单（Order）。每个实体在数据库表与 Java 类之间保持字段映射一致。

### 用户（User）
对应表 `user`，是系统中所有用户的载体，支持普通用户与管理员角色区分。

**Section sources**  
- [auction_database.sql](file://auction_database.sql#L13-L23)
- [User.java](file://src/main/java/com/qkl/auctionsystem/pojo/entity/User.java#L1-L26)

### 拍品（Item）
对应表 `auction_item`，表示一个拍卖物品，包含起拍价、时间范围、当前最高出价等状态信息。

**Section sources**  
- [auction_database.sql](file://auction_database.sql#L25-L45)
- [Item.java](file://src/main/java/com/qkl/auctionsystem/pojo/entity/Item.java#L1-L34)

### 竞拍记录（BidRecord）
对应表 `auction_record`，记录每一次用户对拍品的出价行为，形成历史轨迹。

**Section sources**  
- [auction_database.sql](file://auction_database.sql#L47-L58)
- [BidRecord.java](file://src/main/java/com/qkl/auctionsystem/pojo/entity/BidRecord.java#L1-L19)

### 订单（Order）
对应表 `auction_order`，表示一次拍卖成功后的交易订单，与拍品唯一绑定。

**Section sources**  
- [auction_database.sql](file://auction_database.sql#L60-L72)
- [Order.java](file://src/main/java/com/qkl/auctionsystem/pojo/entity/Order.java#L1-L24)

## 实体关系分析
以下为各实体之间的主要关系分析：

### Item 与 BidRecord：一对多关系
一个拍品（Item）可被多个用户多次出价，形成多条竞拍记录（BidRecord），构成 **一对多** 关系。  
- 数据库层面：`auction_record.item_id` 外键指向 `auction_item.id`
- Java 映射：`BidRecord` 类中 `itemId` 字段对应 `Item` 的 `id`
- 业务含义：支持多人竞拍同一物品，保留完整出价历史

**Section sources**  
- [auction_database.sql](file://auction_database.sql#L50)
- [BidRecord.java](file://src/main/java/com/qkl/auctionsystem/pojo/entity/BidRecord.java#L15)
- [BidRecordMapper.java](file://src/main/java/com/qkl/auctionsystem/mapper/BidRecordMapper.java#L17)

### Item 与 Order：一对一关系
一个拍品（Item）在拍卖结束后若成交，则生成唯一订单（Order），构成 **一对一** 关系。  
- 数据库层面：`auction_order.item_id` 是外键，且有唯一约束 `uk_item_id`
- Java 映射：`Order` 类中 `itemId` 字段对应 `Item` 的 `id`
- 业务含义：确保每件拍品最多只能成交一次，防止重复下单

**Section sources**  
- [auction_database.sql](file://auction_database.sql#L63-L69)
- [Order.java](file://src/main/java/com/qkl/auctionsystem/pojo/entity/Order.java#L17)
- [OrderMapper.xml](file://src/main/resources/mapper/OrderMapper.xml#L10)

### User 与 BidRecord：多对多关系（通过 BidRecord 关联）
用户（User）可对多个拍品出价，同时一个拍品也可被多个用户出价，通过 `BidRecord` 表实现 **多对多** 关联。  
- 数据库层面：`auction_record.user_id` 指向 `user.id`
- Java 映射：`BidRecord` 类中 `userId` 字段对应 `User` 的 `id`
- 业务含义：支持用户参与多个拍卖活动，记录其所有出价行为

**Section sources**  
- [auction_database.sql](file://auction_database.sql#L51)
- [BidRecord.java](file://src/main/java/com/qkl/auctionsystem/pojo/entity/BidRecord.java#L16)
- [BidServiceImpl.java](file://src/main/java/com/qkl/auctionsystem/service/impl/BidServiceImpl.java#L32)

### User 与 Order：多对多关系（通过 Order 关联）
用户（User）可购买多个拍品生成多个订单，同时一个订单仅属于一个买家，构成 **多对多** 关系（实际为用户与订单的一对多 + 订单与用户的多对一）。  
- 数据库层面：`auction_order.user_id` 指向 `user.id`
- Java 映射：`Order` 类中 `userId` 字段表示买家 ID
- 业务含义：支持用户购买多个拍品，管理个人订单

**Section sources**  
- [auction_database.sql](file://auction_database.sql#L64)
- [Order.java](file://src/main/java/com/qkl/auctionsystem/pojo/entity/Order.java#L18)
- [OrderMapper.xml](file://src/main/resources/mapper/OrderMapper.xml#L12)

## ER图（实体关系图）
以下是基于数据库结构和 Java 实体类生成的 Mermaid ER 图，清晰展示四张表之间的关系、基数和依赖方向。

```mermaid
erDiagram
USER {
bigint id PK
varchar username UK
varchar password
tinyint role
datetime create_time
datetime update_time
}
AUCTION_ITEM {
bigint id PK
varchar title
varchar image
decimal initial_price
varchar description
datetime start_time
datetime end_time
decimal current_max_price
bigint current_max_user_id FK
tinyint status
tinyint listing_status
datetime create_time
datetime update_time
}
AUCTION_RECORD {
bigint id PK
bigint item_id FK
bigint user_id FK
decimal bid_price
datetime bid_time
}
AUCTION_ORDER {
bigint id PK
bigint item_id FK UK
bigint user_id FK
decimal deal_price
tinyint status
datetime update_time
}
USER ||--o{ AUCTION_RECORD : "1对多"
USER ||--o{ AUCTION_ORDER : "1对多"
USER ||--o{ AUCTION_ITEM : "创建"
AUCTION_ITEM ||--o{ AUCTION_RECORD : "1对多"
AUCTION_ITEM ||--|| AUCTION_ORDER : "1对1"
```

**Diagram sources**  
- [auction_database.sql](file://auction_database.sql#L13-L72)
- [User.java](file://src/main/java/com/qkl/auctionsystem/pojo/entity/User.java)
- [Item.java](file://src/main/java/com/qkl/auctionsystem/pojo/entity/Item.java)
- [BidRecord.java](file://src/main/java/com/qkl/auctionsystem/pojo/entity/BidRecord.java)
- [Order.java](file://src/main/java/com/qkl/auctionsystem/pojo/entity/Order.java)

## 外键约束与业务含义
外键不仅保证数据完整性，也承载关键业务逻辑：

### `auction_item.current_max_user_id` → `user.id`
- **字段说明**：记录当前最高出价用户的 ID
- **业务含义**：用于展示“当前领先者”，在前端显示“您已被用户XXX超越”
- **更新时机**：每次成功出价后，通过 `ItemMapper.updateCurrentMaxPrice` 更新

**Section sources**  
- [auction_database.sql](file://auction_database.sql#L35)
- [Item.java](file://src/main/java/com/qkl/auctionsystem/pojo/entity/Item.java#L27)
- [ItemMapper.xml](file://src/main/resources/mapper/ItemMapper.xml#L100-L102)

### `auction_record.item_id` → `auction_item.id`
- **字段说明**：关联竞拍记录与拍品
- **业务含义**：确保每条出价都明确指向某个拍品，支持按拍品查询所有出价记录
- **查询应用**：`BidRecordMapper.selectByItemId()` 用于加载某拍品的出价历史

**Section sources**  
- [auction_database.sql](file://auction_database.sql#L50)
- [BidRecordMapper.java](file://src/main/java/com/qkl/auctionsystem/mapper/BidRecordMapper.java#L18)

### `auction_order.item_id` → `auction_item.id`（唯一约束）
- **字段说明**：订单与拍品的唯一绑定
- **业务含义**：防止同一拍品生成多个订单，确保拍卖结果的唯一性
- **约束类型**：外键 + 唯一索引（`uk_item_id`）

**Section sources**  
- [auction_database.sql](file://auction_database.sql#L69)
- [Order.java](file://src/main/java/com/qkl/auctionsystem/pojo/entity/Order.java#L17)

## 结论
本系统通过合理的数据库设计和 Java 实体映射，实现了拍卖业务的核心数据模型。关键关系包括：
- **Item 与 BidRecord 的一对多**：支持多人竞拍，保留出价历史
- **Item 与 Order 的一对一**：确保拍品成交的唯一性
- **User 与 BidRecord/Order 的多对多**：支持用户参与多个拍卖并管理订单

ER图清晰展示了实体间的基数与依赖方向，外键字段如 `current_max_user_id` 不仅是技术约束，更承载了“当前领先者”等重要业务语义。该设计具备良好的可扩展性与数据一致性保障。