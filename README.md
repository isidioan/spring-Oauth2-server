# spring-Oauth2-server

docker volume create --name auth_server_data --driver local
docker run -p 5555:5432 --name postgres_auth_server_db -e POSTGRES_PASSWORD=password -e POSTGRES_USER=authuser -e POSTGRES_DB=authDb -v auth_server_data:/var/lib/postgresql/data -d postgres
