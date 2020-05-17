**Перед запуском установите**  
docker  
docker-compose

**Для проверки работы микросервисов**  
cd JavaLabs/Lab2  
sudo docker-compose up --phone-app=2 // запустити 2 экземпляра Eureka Client

Eureka Server URL: http://localhost:8761  
Service URL (instance 1): http://localhost:8081  
Service URL (instance 2): http://localhost:8082  
Api Gateway URL: http://localhost:8080
