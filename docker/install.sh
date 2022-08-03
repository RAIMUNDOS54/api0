docker stop api0api
docker rm api0api
docker build -t api0-api .
docker tag api0-api exactaworks/api0-api
docker run -d -p 8080:8080 --name api0api api0-api