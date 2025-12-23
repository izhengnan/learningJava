# API接口文档

<cite>
**本文档引用的文件**  
- [说明.md](file://说明.md)
- [UserController.java](file://src/main/java/com/qkl/auctionsystem/controller/UserController.java)
- [CommonController.java](file://src/main/java/com/qkl/auctionsystem/controller/CommonController.java)
- [ItemController.java](file://src/main/java/com/qkl/auctionsystem/controller/ItemController.java)
- [BidController.java](file://src/main/java/com/qkl/auctionsystem/controller/BidController.java)
- [OrderController.java](file://src/main/java/com/qkl/auctionsystem/controller/OrderController.java)
- [AdminController.java](file://src/main/java/com/qkl/auctionsystem/controller/AdminController.java)
- [UserDTO.java](file://src/main/java/com/qkl/auctionsystem/pojo/dto/UserDTO.java)
- [ItemDTO.java](file://src/main/java/com/qkl/auctionsystem/pojo/dto/ItemDTO.java)
- [BidDTO.java](file://src/main/java/com/qkl/auctionsystem/pojo/dto/BidDTO.java)
- [Result.java](file://src/main/java/com/qkl/auctionsystem/result/Result.java)
- [JwtUtils.java](file://src/main/java/com/qkl/auctionsystem/utils/JwtUtils.java)
- [PermissionChecker.java](file://src/main/java/com/qkl/auctionsystem/utils/PermissionChecker.java)
- [request.js](file://html/utils/request.js)
</cite>

## 目录
1. [认证机制](#认证机制)
2. [通用响应格式](#通用响应格式)
3. [用户模块](#用户模块)
4. [通用模块](#通用模块)
5. [拍品模块](#拍品模块)
6. [竞拍模块](#竞拍模块)
7. [订单模块](#订单模块)

## 认证机制

所有需要身份验证的接口均使用JWT（JSON Web Token）进行认证。客户端在请求头中携带Token，格式如下：

```
Authorization: Bearer <token>
```

或在请求头中使用自定义的 `token` 字段：

```
token: <token>
```

Token由登录接口生成，包含用户ID和角色信息（role），有效期为12小时。前端应将Token存储在 `localStorage` 中，并在每次请求时自动附加。

**Section sources**
- [UserController.java](file://src/main/java/com/qkl/auctionsystem/controller/UserController.java#L30-L48)
- [AdminController.java](file://src/main/java/com/qkl/auctionsystem/controller/AdminController.java#L30-L51)
- [JwtUtils.java](file://src/main/java/com/qkl/auctionsystem/utils/JwtUtils.java#L18-L23)
- [request.js](file://html/utils/request.js#L14-L16)

## 通用响应格式

所有API接口返回统一的JSON格式响应：

```json
{
  "code": 1,
  "msg": "操作成功",
  "data": {}
}
```

- `code`: 响应码，1表示成功，0表示失败
- `msg`: 错误信息（仅在失败时返回）
- `data`: 返回的数据对象（仅在成功时返回）

**Section sources**
- [Result.java](file://src/main/java/com/qkl/auctionsystem/result/Result.java#L7-L38)

## 用户模块

### 用户注册
- **请求方式**: POST
- **接口路径**: `/user/register`
- **请求参数**: 在请求体中传递JSON对象
- **请求体结构**:
  ```json
  {
    "username": "用户名",
    "password": "密码"
  }
  ```
- **响应格式**:
  ```json
  { "code": 1, "msg": "", "data": null }
  ```
- **错误码**:
  - `0`: 注册失败（用户名已存在等）
- **curl示例**:
  ```bash
  curl -X POST http://localhost:8081/auction/user/register \
       -H "Content-Type: application/json" \
       -d '{"username":"testuser","password":"123456"}'
  ```
- **JavaScript调用**:
  ```javascript
  import { post } from './utils/request.js';
  post('/user/register', { username: 'test', password: '123456' });
  ```

**Section sources**
- [UserController.java](file://src/main/java/com/qkl/auctionsystem/controller/UserController.java#L23-L28)
- [UserDTO.java](file://src/main/java/com/qkl/auctionsystem/pojo/dto/UserDTO.java#L6-L11)

### 用户登录
- **请求方式**: POST
- **接口路径**: `/user/login`
- **请求体结构**:
  ```json
  {
    "username": "用户名",
    "password": "密码"
  }
  ```
- **响应格式**:
  ```json
  {
    "code": 1,
    "data": {
      "id": 1,
      "role": 1,
      "token": "eyJhbGciOiJIUzI1NiJ9..."
    }
  }
  ```
- **JavaScript调用**:
  ```javascript
  post('/user/login', { username: 'user', password: 'pass' }).then(res => {
    setToken(res.data.token); // 存储token
  });
  ```

**Section sources**
- [UserController.java](file://src/main/java/com/qkl/auctionsystem/controller/UserController.java#L30-L48)
- [UserDTO.java](file://src/main/java/com/qkl/auctionsystem/pojo/dto/UserDTO.java#L6-L11)

### 管理员登录
- **请求方式**: POST
- **接口路径**: `/admin/login`
- **请求体结构**:
  ```json
  {
    "username": "管理员用户名",
    "password": "密码"
  }
  ```
- **响应格式**: 同用户登录，但 `role` 为 `0`
- **权限说明**: 仅管理员可登录，后端自动设置角色为0

**Section sources**
- [AdminController.java](file://src/main/java/com/qkl/auctionsystem/controller/AdminController.java#L30-L51)
- [UserDTO.java](file://src/main/java/com/qkl/auctionsystem/pojo/dto/UserDTO.java#L6-L11)

## 通用模块

### 文件上传
- **请求方式**: POST
- **接口路径**: `/admin/common/upload`
- **请求头**: `token`（管理员Token）
- **请求参数**: `file`（multipart/form-data）
- **响应格式**:
  ```json
  { "code": 1, "data": "https://oss.example.com/image.jpg" }
  ```
- **权限校验**: 必须为管理员
- **JavaScript调用**:
  ```javascript
  const formData = new FormData();
  formData.append('file', fileInput.files[0]);
  postFormData('/admin/common/upload', formData);
  ```

**Section sources**
- [CommonController.java](file://src/main/java/com/qkl/auctionsystem/controller/CommonController.java#L25-L50)
- [PermissionChecker.java](file://src/main/java/com/qkl/auctionsystem/utils/PermissionChecker.java#L15-L18)
- [request.js](file://html/utils/request.js#L95-L105)

## 拍品模块

### 添加拍品
- **请求方式**: PUT
- **接口路径**: `/item/add`
- **权限**: 管理员
- **请求体结构**:
  ```json
  {
    "title": "iPhone 15",
    "image": "https://oss.example.com/phone.jpg",
    "initialPrice": 5000,
    "description": "全新未拆封",
    "startTime": "2025-01-01T10:00:00",
    "endTime": "2025-01-07T10:00:00"
  }
  ```

**Section sources**
- [ItemController.java](file://src/main/java/com/qkl/auctionsystem/controller/ItemController.java#L24-L34)

### 修改拍品
- **请求方式**: PUT
- **接口路径**: `/item/update`
- **限制**: 仅当拍品下架时可修改

**Section sources**
- [ItemController.java](file://src/main/java/com/qkl/auctionsystem/controller/ItemController.java#L68-L78)

### 拍品列表（用户）
- **请求方式**: GET
- **接口路径**: `/item/list`
- **查询参数**:
  - `pageNum`: 页码
  - `pageSize`: 每页数量
  - `status`: 拍品状态（0-未开始，1-竞拍中，2-已结束）
  - `minPrice`, `maxPrice`: 价格范围
  - `keyword`: 关键词搜索
- **响应格式**:
  ```json
  {
    "code": 1,
    "data": {
      "total": 10,
      "records": [/* 拍品列表 */]
    }
  }
  ```

**Section sources**
- [ItemController.java](file://src/main/java/com/qkl/auctionsystem/controller/ItemController.java#L36-L41)

### 管理员拍品列表
- **接口路径**: `/item/list/admin`
- **权限**: 管理员
- **其他参数**: 同用户列表

**Section sources**
- [ItemController.java](file://src/main/java/com/qkl/auctionsystem/controller/ItemController.java#L43-L48)

### 查询拍品详情
- **请求方式**: GET
- **接口路径**: `/item/{itemId}`
- **无需登录**

**Section sources**
- [ItemController.java](file://src/main/java/com/qkl/auctionsystem/controller/ItemController.java#L50-L55)

### 批量删除拍品
- **请求方式**: DELETE
- **接口路径**: `/item/`
- **请求参数**: `ids`（拍品ID数组）
- **限制**: 仅当拍品下架时可删除

**Section sources**
- [ItemController.java](file://src/main/java/com/qkl/auctionsystem/controller/ItemController.java#L56-L66)

### 修改上架状态
- **请求方式**: PUT
- **接口路径**: `/item/{listingStatus}`
- **路径参数**: `listingStatus`（0-下架，1-上架）
- **请求参数**: `itemId`

**Section sources**
- [ItemController.java](file://src/main/java/com/qkl/auctionsystem/controller/ItemController.java#L80-L85)

## 竞拍模块

### 出价竞拍
- **请求方式**: POST
- **接口路径**: `/bid`
- **请求体结构**:
  ```json
  {
    "itemId": 1,
    "bidPrice": 5500
  }
  ```
- **校验规则**:
  - 拍品必须处于“竞拍中”状态
  - 出价必须大于当前最高价

**Section sources**
- [BidController.java](file://src/main/java/com/qkl/auctionsystem/controller/BidController.java#L29-L34)
- [BidDTO.java](file://src/main/java/com/qkl/auctionsystem/pojo/dto/BidDTO.java#L6-L9)

### 查询竞拍记录
- **请求方式**: GET
- **接口路径**: `/bid/records/{itemId}`
- **响应格式**:
  ```json
  {
    "code": 1,
    "data": {
      "itemInfo": { /* 拍品信息 */ },
      "bidRecords": [ /* 出价记录 */ ]
    }
  }
  ```

**Section sources**
- [BidController.java](file://src/main/java/com/qkl/auctionsystem/controller/BidController.java#L41-L46)

## 订单模块

### 订单付款
- **请求方式**: POST
- **接口路径**: `/order/pay/{orderId}`
- **请求参数**: `address`（收货地址）
- **功能**: 将订单状态从“待付款”改为“已完成”

**Section sources**
- [OrderController.java](file://src/main/java/com/qkl/auctionsystem/controller/OrderController.java#L26-L31)

### 我的订单列表
- **请求方式**: GET
- **接口路径**: `/order/my`
- **响应格式**: 返回当前用户的所有订单列表

**Section sources**
- [OrderController.java](file://src/main/java/com/qkl/auctionsystem/controller/OrderController.java#L37-L42)