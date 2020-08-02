# Wisemen council

Backend monolithic application for personal self-management

## Contributing

### Start the server
Use Gradle task `startServer`. This will create docker containers for the application
and the database:
```bash
./gradlew startServer
```

### Stop the server
Use Gradle task `stopServer`. This will remove all containers created with `startServer:
```bash
./gradlew stopServer
```

The database will be persisted in `./docker-compose-data/pgdata`
