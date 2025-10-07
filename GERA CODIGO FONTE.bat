@echo off
cd /d %~dp0
powershell -Command "Get-ChildItem -Recurse -Include *.kt,*.java | Get-Content | Out-File CODIGO_COMPLETO.txt -Encoding utf8"
echo Arquivo codigo_completo.txt gerado com sucesso!
pause