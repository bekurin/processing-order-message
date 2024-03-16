# 주문 메시지 처리 시스템

## 사용 기술
- spring jpa, spring web
- redis, mysql, kafka
- kotlin, java

## 실행 방법
1. docker-compose up -d
2. service-api 실행

## docker-compose.yml 설명
- database: postgresql 데이터베이스 서비스
- zookeeper: kafka 클러스터 관리를 위한 zookeeper 서비스
- kafka: apache kafka 서비스
- connect: CDC 연결을 위한 debezium 서비스
- kafka-ui: kafka 확인을 위한 GUI
- debezium-ui: debezium 확인을 위한 GUI
- setup-debezium-connector: 모든 서비스 실행 후에 debezium과 postgresql을 연결한 서비스

## 아키텍처
![주문_시스템_아키텍처_v1.png](images%2F%EC%A3%BC%EB%AC%B8_%EC%8B%9C%EC%8A%A4%ED%85%9C_%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98_v1.png)


## Trouble Shooting

### Could not find org.springframework.cloud:spring-cloud-starter-stream-kafka:
![spring-cloud-not-found.png](images%2Fspring-cloud-not-found.png)

dependencyManager 에서 spring cloud 관련 정보를 찾을 수 없기 때문에 발생한 것으로 build.gradle.kts 에 아래 코드를 추가해여 해결했다.
```kotlin
dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}")
    }
}
```
