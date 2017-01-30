#docker ps -a | awk '{print }' | xargs docker rm
docker stop $(docker ps -aq)
docker rm $(docker ps -a -q)
docker rmi $(docker images -a -q)