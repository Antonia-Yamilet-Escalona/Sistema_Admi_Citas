# Sistema de Administración de Citas Médicas 🏥

Este proyecto es un sistema backend para gestionar pacientes, doctores y citas médicas. La aplicación está desarrollada en Java utilizando el framework Spring Boot.
---

## 🔧 Instalación y Configuración

Sigue estos pasos para levantar el entorno de desarrollo en tu máquina local.

### Prerrequisitos

Asegúrate de tener instalado el siguiente software:
* **Java JDK 17** o superior.
* **Apache Maven 3.8** o superior.
* **Git** para el control de versiones.

### Pasos de Instalación

1.  **Clonar el repositorio:**
    ```bash
    git clone https://github.com/Antonia-Yamilet-Escalona/Sistema_Admi_Citas.git
    ```

2.  **Navegar al directorio del proyecto:**
    ```bash
    cd Sistema_Admi_Citas
    ```

3.  **Configurar la base de datos (opcional):**
    El proyecto está configurado para usar la base de datos H2 en modo archivo por defecto, por lo que no se requiere ninguna configuración adicional para empezar.

    Si deseas usar una base de datos externa como PostgreSQL, modifica el archivo `src/main/resources/application.properties` con tus credenciales:
    ```properties
    # spring.datasource.url=jdbc:postgresql://localhost:5432/citas_db
    # spring.datasource.username=tu_usuario
    # spring.datasource.password=tu_contraseña
    ```

4.  **Compilar y construir el proyecto:**
    Este comando descargará todas las dependencias y compilará el código.
    ```bash
    mvn clean install
    ```

5.  **Ejecutar la aplicación:**
    Una vez construido, puedes iniciar el servidor con el siguiente comando:
    ```bash
    mvn spring-boot:run
    ```
    La aplicación estará corriendo en `http://localhost:8080`.

---

## 🎮 Uso del Programa

Una vez que la aplicación esté en ejecución, puedes interactuar con ella a través de sus endpoints de API.

### Funcionalidades Principales
* Gestión de Pacientes (Crear, Leer, Actualizar, Borrar).
* Gestión de Doctores.
* Programación y consulta de Citas.

### Endpoints de la API
Aquí hay una lista de los endpoints principales disponibles:

| Método | Ruta | Descripción |
| :--- | :--- | :--- |
| `POST` | `/api/pacientes` | Registra un nuevo paciente. |
| `GET` | `/api/pacientes` | Obtiene una lista de todos los pacientes. |
| `GET` | `/api/pacientes/{id}` | Obtiene los detalles de un paciente específico. |
| `PUT` | `/api/pacientes/{id}` | Actualiza la información de un paciente. |
| `DELETE` | `/api/pacientes/{id}` | Elimina a un paciente del sistema. |

Puedes usar herramientas como Postman o Insomnia para probar estos endpoints.

---

## 🧑‍💻 Créditos

Este proyecto fue desarrollado por:

* **Antonia Yamilet Escalona** - *Desarrolladora Principal* - [Antonia-Yamilet-Escalona](https://github.com/Antonia-Yamilet-Escalona)


---

## 📄 Licencia

Este proyecto se distribuye bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.
