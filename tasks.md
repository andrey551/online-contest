1. dockerfile: when run, create a temp file proto for each service with needed file proto
2. implements file transfer from docker service to file service
3. implement spring security on gateway
4. integrate with keycloak
5. triển khai việc lấy file từ cloud -> trả về cho người dùng 
6. fix docker auto start

15/04/2025:
#### code:
1. implements file transfer from docker service to file service(2) (X)
2. implements result transfer from tester to submission(3):
    - When send submission, a new container is created, then check if exist solution with specific user, problem, then update them, instead create a new one(x)
    - Implements grpc message from tester -> submission (x)
#### report:
1. file service (done)
2. course service (done)

16/04/2025:
1. implements file transfer from docker service to file service(2) -> download link to submission
2. implements result transfer from tester to submission(3):
   - When send submission, a new container is created, then check 
if exist solution with specific user, problem, then update them, 
instead create a new one (done)
   - Implements grpc message from tester -> submission (done)
#### report:
1. Docker service(Done)
2. Tester service (Done)

17/04/2025
1. implement spring security on gateway
2. Teacher search by student id (done)
#### report:
1. Gateway service 
2. User service

19/04/2025
1. implement spring security on gateway
#### report:
1. Gateway service
2. User service

20/04/2025
1. Dockerize all project
2. check gateway access by role
#### report:
1. Gateway service
2. User service


http://localhost:8086/realms/master/protocol/openid-connect/auth?client_id=security-admin-console&redirect_uri=http%3A%2F%2Flocalhost%3A8086%2Fadmin%2Fmaster%2Fconsole%2F%23%2Fcontest%2Fclients%2F8eb277e7-3cf0-4338-8470-17e82a5539c9%2Fsettings&state=bbedfaa1-d741-4685-9d24-fa6016e62469&response_mode=query&response_type=code&scope=openid&nonce=7ea2e9e7-dc63-409e-bcd6-72fef83dd069&code_challenge=puKgXNQS3-yZhbncKfV6S4AiAi4dMQENhgbbTjXB8gw&code_challenge_method=S256
http://localhost:8080/realms/contest/protocol/openid-connect/auth?response_type=code&client_id=online-contest-frontend&scope=openid&state=bObcNqNJjbzzm1qoCAB-2MhDdxBi7XJK1FctN64GYsk%3D&redirect_uri=http://localhost:8080/login/oauth2/code/keycloak&nonce=PApLFStglpXjFnUI7wRLaOo3MHqJrT51uqIKNpc1jto