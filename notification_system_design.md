# Stage 1

## Notification APIs

### Get Notifications
GET /api/notifications

Response:
{
   "notifications":[
      {
         "id":"1",
         "title":"Placement Update",
         "message":"TCS shortlisted students",
         "type":"Placement",
         "isRead":false,
         "createdAt":"2026-05-16T10:00:00"
      }
   ]
}

### Mark Notification Read

PUT /api/notifications/{id}/read

Response:
{
   "success": true
}

### Delete Notification

DELETE /api/notifications/{id}

Response:
{
   "success": true
}

# Stage 2

## Database Choice
I would use PostgreSQL because:

- Supports large scale structured data
- Provides indexing for fast retrieval
- Supports ACID properties
- Good for notification querying and filtering
- Handles concurrent users efficiently

## Database Schema

Table: notifications

| Field | Type |
|---------|------|
| id | UUID |
| student_id | INT |
| title | VARCHAR(255) |
| message | TEXT |
| notification_type | VARCHAR(50) |
| is_read | BOOLEAN |
| created_at | TIMESTAMP |

## SQL Query

Create table:

```sql
CREATE TABLE notifications(
id UUID PRIMARY KEY,
student_id INT,
title VARCHAR(255),
message TEXT,
notification_type VARCHAR(50),
is_read BOOLEAN DEFAULT FALSE,
created_at TIMESTAMP
);
```

Fetch notifications:

```sql
SELECT *
FROM notifications
WHERE student_id=1042
ORDER BY created_at DESC;
```

Mark notification as read:

```sql
UPDATE notifications
SET is_read=true
WHERE id='notification_id';
```

Delete notification:

```sql
DELETE FROM notifications
WHERE id='notification_id';
```

# Stage 2

## Database Choice
I would use PostgreSQL because:

- Supports large scale structured data
- Provides indexing for fast retrieval
- Supports ACID properties
- Good for notification querying and filtering
- Handles concurrent users efficiently

## Database Schema

Table: notifications

| Field | Type |
|---------|------|
| id | UUID |
| student_id | INT |
| title | VARCHAR(255) |
| message | TEXT |
| notification_type | VARCHAR(50) |
| is_read | BOOLEAN |
| created_at | TIMESTAMP |

## SQL Query

Create table:

```sql
CREATE TABLE notifications(
id UUID PRIMARY KEY,
student_id INT,
title VARCHAR(255),
message TEXT,
notification_type VARCHAR(50),
is_read BOOLEAN DEFAULT FALSE,
created_at TIMESTAMP
);
```

Fetch notifications:

```sql
SELECT *
FROM notifications
WHERE student_id=1042
ORDER BY created_at DESC;
```

Mark notification as read:

```sql
UPDATE notifications
SET is_read=true
WHERE id='notification_id';
```

Delete notification:

```sql
DELETE FROM notifications
WHERE id='notification_id';
```

# Stage 4

## Performance Improvements

Suggested solutions:

### Pagination

Retrieve limited notifications:

```sql
SELECT *
FROM notifications
WHERE student_id=1042
ORDER BY created_at DESC
LIMIT 20 OFFSET 0;
```

Advantages:
- Faster response
- Less memory usage

Disadvantages:
- Requires multiple requests

### Caching

Use Redis cache:

Advantages:
- Fast reads
- Reduced database load

Disadvantages:
- Cache invalidation complexity

### Lazy Loading

Load notifications when user scrolls.

Advantages:
- Better user experience
- Lower server load

Disadvantages:
- Additional frontend logic

# Stage 5

## Problems in Existing Implementation

Issues:

- Sequential processing is slow
- Email service failure blocks execution
- High latency
- Not scalable

Improved Design:

1. Save notifications into DB
2. Push events to message queue
3. Worker services process queue
4. Send emails asynchronously

Architecture:

HR Request
↓
Notification Service
↓
Database
↓
Message Queue (Kafka/RabbitMQ)
↓
Email Worker
↓
Push Notification Worker

Advantages:

- Fault tolerance
- Scalability
- Faster processing
- Retry support


# Stage 6

## Priority Inbox Algorithm

Priority Score Formula:

Priority Score =
(Event Weight + Recency Weight)

Weights:

Placement = 10
Result = 8
Event = 6

Approach:

1. Fetch notifications
2. Calculate score
3. Sort descending
4. Return top N notifications

Time Complexity:

Sorting:
O(n log n)

Using Priority Queue:

O(n log k)

Advantages:

- Fast retrieval
- Dynamic ranking
- Easy maintenance

# Stage 7

## Frontend Design

Technology:
- React
- Material UI
- Axios

Pages:

1. All Notifications Page
2. Priority Notifications Page
3. Notification Details Page

Features:

- Read/Unread filter
- Pagination
- Notification type filter
- Mobile responsive UI
- Error handling
- API integration

API:

GET /notifications?page=1&limit=20

GET /notifications?notification_type=Placement

GET /notifications?priority=true



