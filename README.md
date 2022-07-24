
# Chat App Api

## Overview
The following repo contains a basic chat app api with the possibilities of creating, updating, deleting users and sending messages.

## Guidelines
Run the Chat Api:

1. Clone this repository

2. Go to root directory and run 
```bash
sh start_chat.sh
```
3. Once the java app starts, test the api.

4. Example:
Create 2 users

```bash
POST localhost:8081/user/
body:
{
    "username": "test1",
    "password":"pass"
}
POST localhost:8081/user/

{
    "username": "test2",
    "password":"pas2"
}
```

5. Verify that the users were created:
```bash
   GET localhost:8081/user/
```

6. Create a message between them:
```bash
POST localhost:8081/user/message
{
  "senderId":[USER_ID],
  "receiverId":[USER_ID],
  "content":"hi, how are you?"
}
```

7. Verify the message was created:
```bash
GET localhost:8081/user/message
```
