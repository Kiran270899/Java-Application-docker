# Java Application – Multi-Stage Docker Build

## 📌 Project Overview

This project demonstrates how to containerize a simple Java HTTP application using a **Multi-Stage Docker Build**.

The application is compiled and packaged into a JAR file in the **Builder Stage**, and only the required JAR file and configuration are copied into the **Final Stage**.

The application runs inside a Docker container and can be accessed from a web browser using port **8080**.

---

## 🏗️ Architecture

```text
MyApp.java
    │
    ▼
Builder Stage
    │
    ├── Compile Java source
    │
    └── Create myapp.jar
            │
            ▼
       Final Stage
            │
            ├── Java Runtime
            ├── myapp.jar
            └── application.properties
            │
            ▼
      Docker Container
            │
            ▼
      Port 8080
            │
            ▼
   http://localhost:8080
```

---

## 📂 Project Structure

```text
docker-java-app/
│
├── Dockerfile
├── MyApp.java
│
└── config/
    └── application.properties
```

---

## 🐳 Dockerfile

The Dockerfile uses two stages:

### Stage 1 – Builder

The Builder Stage:

* Uses Java 17
* Copies the Java source code
* Compiles `MyApp.java`
* Creates an executable `myapp.jar`

### Stage 2 – Final Image

The Final Stage:

* Uses Java 17
* Copies only the generated JAR from the Builder Stage
* Copies the application configuration
* Exposes port 8080
* Starts the Java application

The important Multi-Stage Docker command is:

```dockerfile
COPY --from=builder /build/myapp.jar /app/myapp.jar
```

---

## ⚙️ Prerequisites

Install the following:

* Docker Desktop
* Windows PowerShell
* Git
* GitHub account

Make sure **Docker Desktop is running** before executing Docker commands.

---

## 🚀 Build Docker Image

Open PowerShell inside the project directory:

```powershell
cd C:\Users\sathivelli\Desktop\docker-java-app
```

Build the Docker image:

```powershell
docker build -t myapp:multistage .
```

Verify the image:

```powershell
docker images
```

---

## ▶️ Run Docker Container

Run the application:

```powershell
docker run -d --name myapp-container -p 8080:8080 myapp:multistage
```

Check the running container:

```powershell
docker ps
```

Expected port mapping:

```text
0.0.0.0:8080 -> 8080/tcp
```

---

## 🌐 Access Application

Open Chrome and navigate to:

```text
http://localhost:8080
```

The application displays:

```text
Hello from Docker!

Multi-Stage Docker Build

Java application is running successfully.

Environment: production

Port: 8080
```

---

## 🔍 Check Container Logs

To view application logs:

```powershell
docker logs myapp-container
```

Expected:

```text
Java application started on port 8080
```

To continuously monitor logs:

```powershell
docker logs -f myapp-container
```

Press:

```text
Ctrl + C
```

to stop viewing the logs.

---

## 🛑 Stop Container

```powershell
docker stop myapp-container
```

Remove the container:

```powershell
docker rm myapp-container
```

---

## 🔄 Run Again

After removing the container:

```powershell
docker run -d --name myapp-container -p 8080:8080 myapp:multistage
```

Then open:

```text
http://localhost:8080
```

---

## 🧪 Test Application from PowerShell

You can also test the application without Chrome:

```powershell
curl http://localhost:8080
```

Or:

```powershell
Invoke-WebRequest http://localhost:8080
```

---

## 🔐 Environment Variables

The Dockerfile defines the following environment variables:

```dockerfile
ENV APP_ENV=production
ENV APP_PORT=8080
```

Check them inside the running container:

```powershell
docker exec myapp-container env
```

---

## 📦 Docker Multi-Stage Build Concept

Traditional Docker build:

```text
Source Code
    ↓
Compiler + Build Tools
    ↓
Application
    ↓
Final Image
```

Multi-Stage Docker build:

```text
Source Code
    ↓
Builder Stage
    ↓
Application JAR
    ↓
Final Stage
    ↓
Runtime Application
```

This approach keeps build-related files out of the final application image.

---

## 🛠️ Technologies Used

* Java 17
* Docker
* Docker Desktop
* Docker Multi-Stage Builds
* Windows PowerShell
* Git
* GitHub

---

## 🎯 Learning Objectives

This project demonstrates:

* Creating a Java Dockerfile
* Multi-Stage Docker builds
* Docker image creation
* Docker container creation
* Port mapping
* Environment variables
* Docker volumes
* Docker container logs
* Running a Java application inside Docker
* Accessing a containerized application through Chrome
* Basic Docker troubleshooting

---

## 📌 Useful Docker Commands

```powershell
docker images
docker ps
docker ps -a
docker build -t myapp:multistage .
docker run -d --name myapp-container -p 8080:8080 myapp:multistage
docker logs myapp-container
docker stop myapp-container
docker rm myapp-container
docker exec -it myapp-container bash
```

---

## 👨‍💻 Author

**Gowtam Brahma Kiran Sathivalli**

DevOps Engineer | Azure | Docker | Kubernetes | Terraform | Azure DevOps

---

## ⭐ Project Highlights

* Java 17 application
* Multi-Stage Docker build
* Builder and Final stages
* Executable JAR creation
* Docker port mapping
* Browser-based application access
* Container logging
* Environment configuration
* Production-style Docker workflow
