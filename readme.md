# Small Project: Scala & Knockout TypeScript Application

## Frontend Libraries

* Tailwind CSS
* Knockout.js
* TypeScript

## Backend Libraries

* Akka HTTP & Streams
* MacWire DI
* Spray JSON
* MongoDB Reactive Driver

## Testing Requirements

You need **Docker** and **Docker Engine** installed on your machine.

## Steps to Run the Project

### 1. Clone the repository

```bash
git clone https://github.com/LexaFrontDev/Small-Project-Scala-Knockout-TypeScript-Application.git
cd Small-Project-Scala-Knockout-TypeScript-Application
```

### 2. Build the project

```bash
make build
```

### 3. Start services

```bash
make up
```

### 4. Open in browser

```
http://localhost:8080
```

---

## Project Tree

```
│   docker-compose.yml
│   Makefile
│   readme.md
├───backend
│   │   build.sbt
│   │   start.sh
│   │
│   ├───docker
│   │       Dockerfile
│   │
│   ├───migration
│   │       MongoMigrations.scala
│   │
│   └───src
│       └───main
│           └───scala
│               ├───bootstrap
│               │         Main.scala
│               │       AppModule.scala
│               │
│               ├───domain
│               │   ├───entity
│               │   │       Users.scala
│               │   │
│               │   └───persistence
│               │       └───repository
│               │           └───users
│               │                   UsersRepository.scala
│               │
│               └───infrastructure
│                   ├───EntityMapper
│                   │   └───users
│                   │           UserMapper.scala
│                   │
│                   ├───http
│                   │   ├───json
│                   │   │       JsonFormats.scala
│                   │   │
│                   │   └───users
│                   │           UsersController.scala
│                   │
│                   └───persistence
│                       └───repository
│                           └───users
│                                   MongoUsersRepository.scala
│
└───frontend
    │   package.json
    │   postcss.config.js
    │   tailwind.config.js
    │   tsconfig.json
    │
    ├───docker
    │       Dockerfile
    │
    └───src
        │   index.html
        │   main.ts
        │
        ├───pages
        │   ├───dashboard
        │   │       DashboardPage.ts
        │   │
        │   └───register
        │           RegisterPage.ts
        │
        ├───props
        │   ├───auth
        │   │   └───register
        │   │           RegisterFetchProps.ts
        │   │
        │   └───dashboard
        │           UsersList.ts
        │
        ├───service
        │   ├───fetch
        │   │   ├───auth
        │   │   │       RegisterFetch.ts
        │   │   │
        │   │   └───dashboard
        │   │           DataFetch.ts
        │   │
        │   └───router
        │           Router.ts
        │
        └───styles
                 tailwind.css
```


