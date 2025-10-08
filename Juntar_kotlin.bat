@echo off
set "OUTPUT_FILE=codigo_kotlin_completo.txt"

:: Apaga o arquivo de saída anterior, se existir
if exist "%OUTPUT_FILE%" del "%OUTPUT_FILE%"

echo Processando arquivos Kotlin (.kt)...
echo ========================================================================= >> "%OUTPUT_FILE%"
echo ARQUIVOS KOTLIN (.kt) >> "%OUTPUT_FILE%"
echo ========================================================================= >> "%OUTPUT_FILE%"

:: Busca recursivamente por todos os arquivos .kt na pasta src e seus subdiretórios
for /R ".\app\src" %%f in (*.kt) do (
    echo. >> "%OUTPUT_FILE%"
    echo -------------------------------------------------------------------------- >> "%OUTPUT_FILE%"
    echo ARQUIVO: %%f >> "%OUTPUT_FILE%"
    echo -------------------------------------------------------------------------- >> "%OUTPUT_FILE%"
    type "%%f" >> "%OUTPUT_FILE%"
    echo. >> "%OUTPUT_FILE%"
)

echo.
echo Processo concluido! O arquivo "codigo_kotlin_completo.txt" foi criado.
pause