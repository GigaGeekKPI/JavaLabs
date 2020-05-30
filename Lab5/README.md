**Перед запуском установите**  
docker  
docker-compose
Также перейдите в папку configserver/config-repo и создайте пустой .git репозиторий .

**Для проверки работы микросервисов**  
cd JavaLabs/Lab4
sudo docker-compose up --scale consumer=3

Eureka Server URL: http://localhost:8761  
Api Gateway URL: http://localhost:8080  
Service URL (instance 1): http://localhost:8082  
Service URL (instance 2): http://localhost:8083  
Config Server URL: http://localhost:8888  
Grafana UI(username/password:admin/admin): http://localhost:3000

Логи consumer можно увидеть с помощью команды:
sudo docker logs -f [container-id]