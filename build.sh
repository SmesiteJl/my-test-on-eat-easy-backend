#!/bin/bash
set -e
set -o pipefail

echo "🧼 Cleaning up Docker containers, volumes, and networks..."
docker container ls -aq | xargs -r docker rm -f
docker volume ls -q | xargs -r docker volume rm
docker network ls --filter "type=custom" -q | xargs -r docker network rm || true

echo "🧪 Starting temporary Redis for tests..."
docker run --rm -d --name test-redis -p 6379:6379 redis:7-alpine
sleep 3

echo "🧹 Cleaning previous builds..."
./gradlew clean --no-daemon

echo "✅ Running tests..."
./gradlew build --no-daemon

echo "🧱 Building fat JARs (bootJar for all modules)..."
./gradlew bootJar --no-daemon

echo "🧹 Stopping test Redis..."
docker stop test-redis > /dev/null || true

echo "✅ Tests and fat JAR build completed successfully."

echo "🐳 Starting Docker Compose (including Redis)..."
docker-compose up --build
