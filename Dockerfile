FROM ubuntu:latest
LABEL authors="Never"

ENTRYPOINT ["top", "-b"]