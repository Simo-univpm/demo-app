FROM postgres:alpine

# Copy the SQL script to initialize the database
COPY ./database_seeding.sql /docker-entrypoint-initdb.d/database_seeding.sql
