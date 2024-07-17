# Proyecto de Reservas

## Requisitos
- Java 17
- Maven
- PostgreSQL

## Configuración
- Clona el repositorio
- Configura la base de datos en `application.properties`
- Ejecuta el proyecto con `mvn spring-boot:run`

## Endpoints
- `POST /api/bookings`: Crea una nueva reserva
    - Request Body:
      ```json
      {
          "userId": "14564088-4",
          "houseId": "213132",
          "discountCode": "D0542A23",
          "fechaReserva": "2023-07-16"
      }
      ```

## Pruebas
- Ejecuta las pruebas con `mvn test`
