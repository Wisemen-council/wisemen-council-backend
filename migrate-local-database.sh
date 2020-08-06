export $(cat environment/.env.local | xargs) && ./gradlew migrateLocalDatabase
