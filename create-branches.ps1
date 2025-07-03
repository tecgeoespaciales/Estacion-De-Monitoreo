# Script para crear branches limpios - Version ASCII
# Ejecutar después de instalar Git y reiniciar VS Code

# Navegar al directorio del proyecto
Set-Location "c:\Users\eoquendo\OneDrive - sgc.gov.co\Documentos\estructura_proyecto\Estacion-De-Monitoreo"

# Verificar que Git está disponible
try {
    git --version | Out-Null
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

# Obtener nombres reales de carpetas
$carpetas = Get-ChildItem -Directory | Select-Object -ExpandProperty Name
Write-Host "Carpetas encontradas:" -ForegroundColor Yellow
$carpetas | ForEach-Object { Write-Host "- $_" }

# Crear branch limpio para cada carpeta
foreach ($carpeta in $carpetas) {
    if ($carpeta -like "*VIL*" -or $carpeta -like "*MOVIL*") {
        $branchName = "aplicacion-movil"
    } elseif ($carpeta -like "*DISPOSITIVOS*") {
        $branchName = "dispositivos"
    } elseif ($carpeta -like "*MATERIALES*") {
        $branchName = "materiales"
    } elseif ($carpeta -like "*SERVIDOR*") {
        $branchName = "servidor"
    } else {
        continue
    }
    
    Write-Host "Creando branch '$branchName' para carpeta '$carpeta'..." -ForegroundColor Cyan
    
    # Crear branch huérfano
    git checkout --orphan $branchName
    git rm -rf . --quiet 2>$null
    
    # Copiar contenido de la carpeta
    if (Test-Path $carpeta) {
        Get-ChildItem $carpeta -Recurse | ForEach-Object {
            $relativePath = $_.FullName.Substring((Join-Path (Get-Location) $carpeta).Length + 1)
            $destPath = Join-Path (Get-Location) $relativePath
            
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
    
    git add .
    git commit -m "Branch $branchName - contenido limpio de $carpeta"
    Write-Host "Branch '$branchName' creado exitosamente" -ForegroundColor Green
}

# Volver a main
git checkout main
Write-Host "Volviendo a branch 'main'" -ForegroundColor Cyan

Write-Host ""
Write-Host "¡Branches limpios creados exitosamente!" -ForegroundColor Green

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
