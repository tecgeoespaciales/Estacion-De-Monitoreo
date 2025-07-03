# Script para crear branches por carpeta principal manteniendo estructura completa
# Ejecutar después de instalar Git

# Navegar al directorio del proyecto
Set-Location "c:\Users\eoquendo\OneDrive - sgc.gov.co\Documentos\estructura_proyecto\Estacion-De-Monitoreo"

# Verificar que Git está disponible
try {
    & "C:\Users\eoquendo\AppData\Local\Programs\Git\cmd\git.exe" --version
    Write-Host "Git está disponible" -ForegroundColor Green
} catch {
    Write-Host "Error: Git no está disponible. Por favor instala Git primero." -ForegroundColor Red
    exit 1
}

# Configurar Git si es la primera vez
$userName = & "C:\Users\eoquendo\AppData\Local\Programs\Git\cmd\git.exe" config --global user.name
$userEmail = & "C:\Users\eoquendo\AppData\Local\Programs\Git\cmd\git.exe" config --global user.email

if (!$userName) {
    $name = Read-Host "Ingresa tu nombre para Git"
    & "C:\Users\eoquendo\AppData\Local\Programs\Git\cmd\git.exe" config --global user.name $name
}

if (!$userEmail) {
    $email = Read-Host "Ingresa tu email para Git"
    & "C:\Users\eoquendo\AppData\Local\Programs\Git\cmd\git.exe" config --global user.email $email
}

# Inicializar repositorio Git si no existe
if (!(Test-Path ".git")) {
    & "C:\Users\eoquendo\AppData\Local\Programs\Git\cmd\git.exe" init
    Write-Host "Repositorio Git inicializado" -ForegroundColor Green
} else {
    Write-Host "Repositorio Git ya existe" -ForegroundColor Yellow
}

# Commit inicial en main
& "C:\Users\eoquendo\AppData\Local\Programs\Git\cmd\git.exe" add .
& "C:\Users\eoquendo\AppData\Local\Programs\Git\cmd\git.exe" commit -m "Commit inicial - estructura completa del proyecto"
Write-Host "Commit inicial realizado" -ForegroundColor Green

# Crear branch para APLICACIÓN MÓVIL
Write-Host "Creando branch 'aplicacion-movil'..." -ForegroundColor Cyan
& "C:\Users\eoquendo\AppData\Local\Programs\Git\cmd\git.exe" checkout -b aplicacion-movil
Write-Host "Branch 'aplicacion-movil' creado - mantiene estructura completa, enfocado en APLICACIÓN MÓVIL" -ForegroundColor Green

# Crear branch para DISPOSITIVOS
Write-Host "Creando branch 'dispositivos'..." -ForegroundColor Cyan
& "C:\Users\eoquendo\AppData\Local\Programs\Git\cmd\git.exe" checkout main
& "C:\Users\eoquendo\AppData\Local\Programs\Git\cmd\git.exe" checkout -b dispositivos
Write-Host "Branch 'dispositivos' creado - mantiene estructura completa, enfocado en DISPOSITIVOS" -ForegroundColor Green

# Crear branch para MATERIALES
Write-Host "Creando branch 'materiales'..." -ForegroundColor Cyan
& "C:\Users\eoquendo\AppData\Local\Programs\Git\cmd\git.exe" checkout main
& "C:\Users\eoquendo\AppData\Local\Programs\Git\cmd\git.exe" checkout -b materiales
Write-Host "Branch 'materiales' creado - mantiene estructura completa, enfocado en MATERIALES" -ForegroundColor Green

# Crear branch para SERVIDOR
Write-Host "Creando branch 'servidor'..." -ForegroundColor Cyan
& "C:\Users\eoquendo\AppData\Local\Programs\Git\cmd\git.exe" checkout main
& "C:\Users\eoquendo\AppData\Local\Programs\Git\cmd\git.exe" checkout -b servidor
Write-Host "Branch 'servidor' creado - mantiene estructura completa, enfocado en SERVIDOR" -ForegroundColor Green

# Volver a main
& "C:\Users\eoquendo\AppData\Local\Programs\Git\cmd\git.exe" checkout main
Write-Host "Volviendo a branch 'main'" -ForegroundColor Cyan

Write-Host ""
Write-Host "¡Branches creados exitosamente!" -ForegroundColor Green
Write-Host "- main (rama principal)"
Write-Host "- aplicacion-movil (para desarrollo de la aplicación móvil)"
Write-Host "- dispositivos (para desarrollo de dispositivos)"
Write-Host "- materiales (para gestión de materiales)"
Write-Host "- servidor (para desarrollo del servidor)"

Write-Host ""
Write-Host "Branches disponibles:" -ForegroundColor Yellow
& "C:\Users\eoquendo\AppData\Local\Programs\Git\cmd\git.exe" branch -a

Write-Host ""
Write-Host "Para subir a GitHub:" -ForegroundColor Cyan
Write-Host "1. Crea un repositorio en GitHub"
Write-Host "2. Ejecuta: git remote add origin <URL_DEL_REPOSITORIO>"
Write-Host "3. Ejecuta: git push -u origin --all"

Write-Host ""
Write-Host "Para cambiar entre branches:" -ForegroundColor Cyan
Write-Host "git checkout main              # Rama principal"
Write-Host "git checkout aplicacion-movil  # Trabajar en app móvil"
Write-Host "git checkout dispositivos      # Trabajar en dispositivos"
Write-Host "git checkout materiales        # Trabajar en materiales"
Write-Host "git checkout servidor          # Trabajar en servidor"

Write-Host ""
Write-Host "Ventajas de esta estructura:" -ForegroundColor Green
Write-Host "- Cada branch mantiene la estructura completa del proyecto"
Write-Host "- Puedes trabajar en una carpeta específica sin afectar las demás"
Write-Host "- Fácil fusión de cambios entre branches"
Write-Host "- Ideal para equipos que trabajan en diferentes componentes"
