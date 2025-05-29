# muraveri ou rovekami

### db via docker
rode o comando:
```
docker pull mariadb
```
e depois:
```
docker run -d --name db -e MYSQL_ROOT_PASSWORD=ifsp -v mariadb_data:/var/lib/mysql -p 3308:3308 mariadb
```
