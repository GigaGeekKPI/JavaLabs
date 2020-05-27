**Перед запуском установите**  
docker  
docker-compose
Также склонируйте в папку configserver епозиторий config.

**Для проверки работы микросервисов**  
cd JavaLabs/Lab3  
sudo docker-compose up

**Для смены конфігураций**:  
sudo docker exec -it [configserver_container_id] /bin/sh  
// или
sudo docker exec -it [configserver_container_id] /bin/bash  
cd config  
echo test.prop1=1 > application.properties  
echo test.prop2=2 >> application.properties  
git add .  
git commit -m "change config" 

Eureka Server URL: http://localhost:8761  
Api Gateway URL: http://localhost:8080  
Service URL (instance 1): http://localhost:8082   
Service URL (instance 2): http://localhost:8083    
Config Server URL: http://localhost:8888  
