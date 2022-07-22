# chatapp

Run SpringBoot App with Docker-Compose
sh start_chat.sh

Endpoints:

GET localhost:8081/user/message/40

POST localhost:8081/user/message
body:
{
"senderId":41,
"receiverId":40,
"content":"bine, tu ce zici?"
}

POST localhost:8081/user/
body:
{
"username": "test1",
"password":"pass"
}


