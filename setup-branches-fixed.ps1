# Script para crear branches limpios
# Ejecutar después de instalar Git y reiniciar VS Code

# Navegar al directorio del proyecto
Set-Location "c:\Users\eoquendo\OneDrive - sgc.gov.co\Documentos\estructura_proyecto\Estacion-De-Monitoreo"

# Verificar que Git está disponible
try {
    git --version
    Write-Host "Git está disponible" -ForegroundColor Green
} catch {
    Write-Host "Error: Git no está disponible. Por favor:" -ForegroundColor Red
    Write-Host "1. Cierra VS Code completamente"
    Write-Host "2. Vuelve a abrir VS Code"
    Write-Host "3. Ejecuta este script nuevamente"
    exit 1
}

# Configurar Git si es la primera vez
$userName = git config --global user.name 2>$null
$userEmail = git config --global user.email 2>$null

if (!$userName) {
    $name = Read-Host "Ingresa tu nombre para Git"
    git config --global user.name "$name"
}

if (!$userEmail) {
    $email = Read-Host "Ingresa tu email para Git"
    git config --global user.email "$email"
}

# Inicializar repositorio Git si no existe
if (!(Test-Path ".git")) {
    git init
    Write-Host "Repositorio Git inicializado" -ForegroundColor Green
} else {
    Write-Host "Repositorio Git ya existe" -ForegroundColor Yellow
}

# Commit inicial en main
git add .
git commit -m "Commit inicial - estructura completa del proyecto"
Write-Host "Commit inicial realizado" -ForegroundColor Green

# Definir carpetas a procesar
$carpetaMovil = "APLICACIÓN MÓVIL"
$carpetaDispositivos = "DISPOSITIVOS"
$carpetaMateriales = "MATERIALES"
$carpetaServidor = "SERVIDOR"

# Verificar que las carpetas existen
$carpetas = @($carpetaMovil, $carpetaDispositivos, $carpetaMateriales, $carpetaServidor)
foreach ($carpeta in $carpetas) {
    if (!(Test-Path $carpeta)) {
        Write-Host "Advertencia: No se encontró la carpeta '$carpeta'" -ForegroundColor Yellow
    }
}

# Función para copiar contenido de carpeta
function Copy-FolderContent {
    param($SourceFolder, $DestinationRoot)
    
    if (Test-Path $SourceFolder) {
        Get-ChildItem $SourceFolder -Recurse | ForEach-Object {
            $relativePath = $_.FullName.Substring($SourceFolder.Length + 1)
            $destPath = Join-Path $DestinationRoot $relativePath
            
            if ($_.PSIsContainer) {
                New-Item -ItemType Directory -Path $destPath -Force | Out-Null
            } else {
                $destDir = Split-Path $destPath -Parent
                if (!(Test-Path $destDir)) {
                    New-Item -ItemType Directory -Path $destDir -Force | Out-Null
                }
                Copy-Item $_.FullName -Destination $destPath -Force
            }
        }
    }
}

# Crear branch limpio para APLICACIÓN MÓVIL
Write-Host "Creando branch 'aplicacion-movil'..." -ForegroundColor Cyan
git checkout --orphan aplicacion-movil
git rm -rf . --quiet 2>$null
Copy-FolderContent $carpetaMovil (Get-Location).Path
git add .
git commit -m "Branch aplicación móvil - contenido limpio"
Write-Host "Branch 'aplicacion-movil' creado" -ForegroundColor Green

# Crear branch limpio para DISPOSITIVOS
Write-Host "Creando branch 'dispositivos'..." -ForegroundColor Cyan
git checkout --orphan dispositivos
git rm -rf . --quiet 2>$null
Copy-FolderContent $carpetaDispositivos (Get-Location).Path
git add .
git commit -m "Branch dispositivos - contenido limpio"
Write-Host "Branch 'dispositivos' creado" -ForegroundColor Green

# Crear branch limpio para MATERIALES
Write-Host "Creando branch 'materiales'..." -ForegroundColor Cyan
git checkout --orphan materiales
git rm -rf . --quiet 2>$null
Copy-FolderContent $carpetaMateriales (Get-Location).Path
git add .
git commit -m "Branch materiales - contenido limpio"
Write-Host "Branch 'materiales' creado" -ForegroundColor Green

# Crear branch limpio para SERVIDOR
Write-Host "Creando branch 'servidor'..." -ForegroundColor Cyan
git checkout --orphan servidor
git rm -rf . --quiet 2>$null
Copy-FolderContent $carpetaServidor (Get-Location).Path
git add .
git commit -m "Branch servidor - contenido limpio"
Write-Host "Branch 'servidor' creado" -ForegroundColor Green

# Volver a main
git checkout main
Write-Host "Volviendo a branch 'main'" -ForegroundColor Cyan

Write-Host ""
Write-Host "¡Branches limpios creados exitosamente!" -ForegroundColor Green
Write-Host "- main (estructura completa)"
Write-Host "- aplicacion-movil (solo contenido de APLICACIÓN MÓVIL)"
Write-Host "- dispositivos (solo contenido de DISPOSITIVOS)"
Write-Host "- materiales (solo contenido de MATERIALES)"
Write-Host "- servidor (solo contenido de SERVIDOR)"

Write-Host ""
Write-Host "Branches disponibles:" -ForegroundColor Yellow
git branch -a

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
