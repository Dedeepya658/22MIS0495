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
