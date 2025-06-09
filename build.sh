#!/bin/bash
set -e
set -o pipefail

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
docker-compose up --build --scale user-service=2 --scale product-service=2 -d

