export $(cat environment/.env.local | xargs) && ./gradlew repairMigrations
