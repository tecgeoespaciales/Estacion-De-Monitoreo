# Script simplificado para crear branches limpios
# Ejecutar después de instalar Git

# Navegar al directorio del proyecto
Set-Location "c:\Users\eoquendo\OneDrive - sgc.gov.co\Documentos\estructura_proyecto\Estacion-De-Monitoreo"

# Agregar Git al PATH
$env:PATH += ";C:\Users\$env:USERNAME\AppData\Local\Programs\Git\cmd"

# Crear un .gitignore para evitar problemas con archivos problemáticos
$gitignoreContent = @"
# Archivos de Node.js
node_modules/
*.log
.npm/

# Archivos de sistema
.DS_Store
Thumbs.db

# Archivos de certificados sensibles
*.key
*.pem
*.crt

# Archivos de configuración sensibles
*.env
passwd/
flows_cred.json
"@

Set-Content -Path ".gitignore" -Value $gitignoreContent -Encoding UTF8

# Commit con gitignore
git add .gitignore
git commit -m "Add .gitignore to avoid problematic files"

# Función para crear branch limpio
function New-CleanBranch {
    param($BranchName, $SourceFolder)
    
    Write-Host "Creando branch '$BranchName' desde carpeta '$SourceFolder'..." -ForegroundColor Cyan
    
    # Crear branch huérfano
    git checkout --orphan $BranchName
    
    # Limpiar el área de trabajo
    git rm -rf . --quiet --ignore-unmatch 2>$null
    
    # Copiar solo archivos seguros de la carpeta
    if (Test-Path $SourceFolder) {
        robocopy $SourceFolder . /E /XF *.key *.pem *.crt /XD node_modules .npm /NFL /NDL /NJH /NJS /nc /ns /np
    }
    
    # Crear README específico para el branch
    $readmeContent = @"
# $BranchName

Este branch contiene únicamente el contenido de la carpeta `$SourceFolder` del proyecto Estación de Monitoreo.

## Contenido

Esta rama está optimizada para el desarrollo específico de este componente.

## Estructura Original

El proyecto completo se encuentra en la rama `main`.
"@
    
    Set-Content -Path "README.md" -Value $readmeContent -Encoding UTF8
    
    # Agregar archivos al commit
    git add .
    git commit -m "Branch $BranchName - contenido limpio de $SourceFolder"
    
    Write-Host "Branch '$BranchName' creado exitosamente" -ForegroundColor Green
}

# Crear branches para cada carpeta principal
New-CleanBranch "aplicacion-movil" "APLICACIÓN MÓVIL"
New-CleanBranch "dispositivos" "DISPOSITIVOS"
New-CleanBranch "materiales" "MATERIALES"
New-CleanBranch "servidor" "SERVIDOR"

# Volver a main
git checkout main
Write-Host "Volviendo a branch 'main'" -ForegroundColor Cyan

Write-Host ""
Write-Host "¡Branches limpios creados exitosamente!" -ForegroundColor Green

Write-Host ""
Write-Host "Branches disponibles:" -ForegroundColor Yellow
git branch

Write-Host ""
Write-Host "Para subir a GitHub:" -ForegroundColor Cyan
Write-Host "1. Crea un repositorio en GitHub"
Write-Host "2. Ejecuta: git remote add origin <URL_DEL_REPOSITORIO>"
Write-Host "3. Ejecuta: git push -u origin --all"

Write-Host ""
Write-Host "Para cambiar entre branches:" -ForegroundColor Cyan
Write-Host "git checkout main              # Ver estructura completa"
Write-Host "git checkout aplicacion-movil  # Solo app móvil"
Write-Host "git checkout dispositivos      # Solo dispositivos"
Write-Host "git checkout materiales        # Solo materiales"
Write-Host "git checkout servidor          # Solo servidor"
