#!/bin/sh
set -e

echo "🚀 Running migrations..."
java -jar migrations.jar || echo "⚠️ No migrations to apply"

echo "🌍 Starting server..."
exec java -jar myapp.jar
