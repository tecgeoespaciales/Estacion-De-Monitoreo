# Script para crear branches por carpeta principal
# Ejecutar después de instalar Git

# Navegar al directorio del proyecto
Set-Location "c:\Users\eoquendo\OneDrive - sgc.gov.co\Documentos\estructura_proyecto\Estacion-De-Monitoreo"

# Inicializar repositorio Git si no existe
if (!(Test-Path ".git")) {
    git init
    Write-Host "Repositorio Git inicializado"
}

# Agregar archivos al staging area
git add .

# Commit inicial en main
git commit -m "Commit inicial - estructura completa del proyecto"

# Crear branch para APLICACIÓN MÓVIL
git checkout -b aplicacion-movil
# Remover otras carpetas, mantener solo APLICACIÓN MÓVIL
Remove-Item -Recurse -Force "DISPOSITIVOS", "MATERIALES", "SERVIDOR" -ErrorAction SilentlyContinue
git add .
git commit -m "Branch aplicación móvil - solo carpeta APLICACIÓN MÓVIL"

# Volver a main y crear branch para DISPOSITIVOS
git checkout main
git checkout -b dispositivos
# Remover otras carpetas, mantener solo DISPOSITIVOS
Remove-Item -Recurse -Force "APLICACIÓN MÓVIL", "MATERIALES", "SERVIDOR" -ErrorAction SilentlyContinue
git add .
git commit -m "Branch dispositivos - solo carpeta DISPOSITIVOS"

# Volver a main y crear branch para MATERIALES
git checkout main
git checkout -b materiales
# Remover otras carpetas, mantener solo MATERIALES
Remove-Item -Recurse -Force "APLICACIÓN MÓVIL", "DISPOSITIVOS", "SERVIDOR" -ErrorAction SilentlyContinue
git add .
git commit -m "Branch materiales - solo carpeta MATERIALES"

# Volver a main y crear branch para SERVIDOR
git checkout main
git checkout -b servidor
# Remover otras carpetas, mantener solo SERVIDOR
Remove-Item -Recurse -Force "APLICACIÓN MÓVIL", "DISPOSITIVOS", "MATERIALES" -ErrorAction SilentlyContinue
git add .
git commit -m "Branch servidor - solo carpeta SERVIDOR"

# Volver a main
git checkout main

Write-Host "Branches creados:"
Write-Host "- main (estructura completa)"
Write-Host "- aplicacion-movil"
Write-Host "- dispositivos"
Write-Host "- materiales"
Write-Host "- servidor"

Write-Host ""
Write-Host "Para subir a GitHub:"
Write-Host "1. Crea un repositorio en GitHub"
Write-Host "2. Ejecuta: git remote add origin <URL_DEL_REPOSITORIO>"
Write-Host "3. Ejecuta: git push -u origin --all"
