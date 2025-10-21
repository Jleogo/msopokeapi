# msopokeapi
Proyecto en Spring Boot que consume la API pública de Pokémon y devuelve información de un Pokémon específico, incluyendo:
Nombre / especie, Tipo(s), Habilidades, Ataques (limitado a 5), Estadísticas, Imagen en Base64
Registra las búsquedas en H2 Database

#Tecnologías
-Java 17
-Spring Boot 3.5.6
-Maven
-WebClient para consumir API externa  
-H2 Database en memoria

#Requisitos
Para ejecutar el backend necesitas:
-JDK 17 o superior  
-Maven 3.5.6

Ejecución local del backend
1. Clona el repositorio:
git clone https://github.com/Jleogo/msopokeapi.git
cd msopokeapi

2. Ejecucion con Maven
mvn spring-boot:run

La aplicación estará disponible en:  http://localhost:8080/pokemon




