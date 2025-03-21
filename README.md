# Todo List API Documentation

Đây là API quản lý công việc (tasks) và phụ thuộc (dependencies) cho ứng dụng Todo List. API cung cấp các chức năng như tạo, sửa, xóa công việc, cũng như quản lý phụ thuộc giữa các công việc.

## Mục Lục
1. [Docker Setup](#docker-setup)
2. [Task Management API](#task-management-api)
   - [Create Task](#1-create-task)
   - [Get All Tasks](#2-get-all-tasks)
   - [Get Task by ID](#3-get-task-by-id)
   - [Filter Tasks](#4-filter-tasks)
   - [Update Task](#5-update-task)
   - [Delete Task](#6-delete-task)
   - [Get Overdue Tasks](#7-get-overdue-tasks)
   - [Get Upcoming Tasks](#8-get-upcoming-tasks)
3. [Dependency Management API](#dependency-management-api)
   - [Create Dependency](#1-create-dependency)
   - [Check Circular Dependency of Task](#2-check-circular-dependency-of-task)
   - [Delete Dependency](#3-delete-dependency)
   - [Get All Dependencies of a Task](#4-get-all-dependencies-of-a-task)

---
## Docker Setup

### 1. Clone repository
```bash
git clone https://github.com/nnthanh03/todo-list-api.git
```

### 2. Chuyển vào thư mục setup Docker
```bash
cd todo-list-api/todolist-docker-setup
```

### 3. Chạy ứng dụng với Docker Compose
```bash
docker compose up -d
```

### 4. Truy cập API tại địa chỉ
- [http://localhost:9000/todolist/tasks](http://localhost:9000/todolist/tasks)
- [http://localhost:9000/todolist/depend](http://localhost:9000/todolist/depend)

Sau khi chạy lệnh trên, API sẽ chạy tại [http://localhost:9000](http://localhost:9000).

---

## Task Management API

### 1. **Create Task**
- **URL**: `/todolist/tasks`
- **Method**: `POST`
- **Description**: Tạo một công việc mới.
- **Request Body**:
    ```json
    {
      "title": "Task title",
      "description": "Task description",
      "dueDate": "YYYY-MM-DD",
      "priority": "HIGH",
      "status": "PENDING"
    }
    ```
- **Response**:
    - **201 Created**: Nếu tạo công việc thành công.
    - **400 Bad Request**: Nếu có lỗi trong yêu cầu.
  
    ```json
    {
      "taskId": "123",
      "title": "Task title",
      "description": "Task description",
      "dueDate": "2025-03-21",
      "priority": "HIGH",
      "status": "PENDING"
    }
    ```

### 2. **Get All Tasks**
- **URL**: `/todolist/tasks/all`
- **Method**: `GET`
- **Description**: Lấy tất cả công việc.
- **Response**:
    ```json
    [
      {
        "taskId": "123",
        "title": "Task title",
        "description": "Task description",
        "dueDate": "2025-03-21",
        "priority": "HIGH",
        "status": "PENDING"
      },
      ...
    ]
    ```

### 3. **Get Task by ID**
- **URL**: `/todolist/tasks/get/{taskId}`
- **Method**: `GET`
- **Description**: Lấy công việc theo ID.
- **URL Params**:
  - `taskId` (required): ID của công việc.
- **Response**:
    ```json
    {
      "taskId": "123",
      "title": "Task title",
      "description": "Task description",
      "dueDate": "2025-03-21",
      "priority": "HIGH",
      "status": "PENDING"
    }
    ```

### 4. **Filter Tasks**
- **URL**: `/todolist/tasks/filter`
- **Method**: `GET`
- **Description**: Lọc các công việc dựa trên tiêu chí như tiêu đề, trạng thái, độ ưu tiên và ngày hết hạn.
- **Query Params**:
  - `title` (optional): Tiêu đề công việc.
  - `status` (optional): Trạng thái công việc (PENDING, COMPLETED).
  - `priority` (optional): Độ ưu tiên (LOW, MEDIUM, HIGH).
  - `dueDate` (optional): Ngày hết hạn.
  - `page` (default: 0): Trang kết quả.
  - `size` (default: 5): Số lượng công việc trên mỗi trang.
- **Response**:
    ```json
    {
      "content": [
        {
          "taskId": "123",
          "title": "Task title",
          "description": "Task description",
          "dueDate": "2025-03-21",
          "priority": "HIGH",
          "status": "PENDING"
        },
        ...
      ],
      "totalPages": 2,
      "totalElements": 10
    }
    ```

### 5. **Update Task**
- **URL**: `/todolist/tasks/update/{taskId}`
- **Method**: `PUT`
- **Description**: Cập nhật thông tin công việc.
- **URL Params**:
  - `taskId` (required): ID của công việc.
- **Request Body**:
    ```json
    {
      "title": "Updated title",
      "description": "Updated description",
      "dueDate": "2025-03-22",
      "priority": "MEDIUM",
      "status": "COMPLETED"
    }
    ```
- **Response**:
    ```json
    {
      "taskId": "123",
      "title": "Updated title",
      "description": "Updated description",
      "dueDate": "2025-03-22",
      "priority": "MEDIUM",
      "status": "COMPLETED"
    }
    ```

### 6. **Delete Task**
- **URL**: `/todolist/tasks/delete/{taskId}`
- **Method**: `DELETE`
- **Description**: Xóa công việc theo ID.
- **URL Params**:
  - `taskId` (required): ID của công việc.
- **Response**:
    ```json
    {
      "message": "Delete successfully"
    }
    ```

### 7. **Get Overdue Tasks**
- **URL**: `/todolist/tasks/overdue`
- **Method**: `GET`
- **Description**: Lấy tất cả công việc quá hạn.
- **Response**:
    ```json
    [
      {
        "taskId": "123",
        "title": "Overdue Task",
        "description": "This task is overdue.",
        "dueDate": "2025-03-20",
        "priority": "HIGH",
        "status": "PENDING"
      },
      ...
    ]
    ```

### 8. **Get Upcoming Tasks**
- **URL**: `/todolist/tasks/upcoming`
- **Method**: `GET`
- **Description**: Lấy các công việc sẽ đến hạn trong ngày tiếp theo.
- **Response**:
    ```json
    [
      {
        "taskId": "124",
        "title": "Upcoming Task",
        "description": "This task is upcoming.",
        "dueDate": "2025-03-22",
        "priority": "MEDIUM",
        "status": "PENDING"
      },
      ...
    ]
    ```

---

## Dependency Management API

### 1. **Create Dependency**
- **URL**: `/todolist/depend`
- **Method**: `POST`
- **Description**: Tạo một phụ thuộc giữa hai công việc.
- **Request Body**:
    ```json
    {
      "taskId": "task-id",
      "dependencyId": "dependency-id"
    }
    ```
- **Response**:
    - **201 Created**: Nếu tạo phụ thuộc thành công.
    - **400 Bad Request**: Nếu phát hiện vòng lặp phụ thuộc.

    ```json
    {
      "message": "Dependency created successfully."
    }
    ```

    Hoặc nếu có vòng lặp phụ thuộc:

    ```json
    {
      "message": "Can't create, Detect Circular Dependency"
    }
    ```

### 2. **Check Circular Dependency of Task**
- **URL**: `/todolist/depend/check/{taskId}`
- **Method**: `POST`
- **Description**: Kiểm tra xem công việc có bị phụ thuộc vòng lặp hay không.
- **URL Params**:
  - `taskId` (required): ID của công việc.
- **Response**:
    - **200 OK**: Nếu không phát hiện vòng lặp.
    - **400 Bad Request**: Nếu phát hiện vòng lặp phụ thuộc.

    ```json
    {
      "message": "OK."
    }
    ```

    Hoặc nếu có vòng lặp:

    ```json
    {
      "message": "Detect Circular Dependency"
    }
    ```

### 3. **Delete Dependency**
- **URL**: `/todolist/depend/delete/{id}`
- **Method**: `DELETE`
- **Description**: Xóa một phụ thuộc theo ID.
- **URL Params**:
  - `id` (required): ID của phụ thuộc.
- **Response**:
    ```json
    {
      "message": "Delete successfully"
    }
    ```

### 4. **Get All Dependencies of a Task**
- **URL**: `/todolist/depend/{taskId}`
- **Method**: `GET`
- **Description**: Lấy tất cả phụ thuộc của một công việc theo ID công việc.
- **URL Params**:
  - `taskId` (required): ID của công việc.
- **Response**:
    ```json
    [
      {
        "taskId": "task-id",
        "dependencyId": "dependency-id"
      },
      ...
    ]
    ```

---

## Notes

- **Circular Dependency**: Khi một công việc A phụ thuộc vào công việc B, và công việc B lại phụ thuộc vào công việc A, thì sẽ tạo ra vòng lặp (circular dependency), dẫn đến không thể tạo được phụ thuộc.

- **Dependency Example**:
  - Giả sử bạn có hai công việc: Task A và Task B.
  - Nếu Task A phụ thuộc vào Task B, bạn có thể sử dụng API để tạo phụ thuộc này.
  - Nếu Task B cũng phụ thuộc vào Task A, hệ thống sẽ phát hiện vòng lặp và không cho phép tạo phụ thuộc.
