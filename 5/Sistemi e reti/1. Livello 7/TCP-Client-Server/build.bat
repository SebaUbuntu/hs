@echo off
gcc client.c -lws2_32 -o client.exe
gcc server.c -lws2_32 -o server.exe
