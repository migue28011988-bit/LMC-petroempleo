# Sistema de Levantamiento de Medios de Cómputo para petroempleo

Sistema compuesto por:
- **App principal** (Java Swing + Hibernate + PostgreSQL)
- **Agente** (corre en cada PC, envía datos con OSHI y PowerShell)

## Instalación completa

1. **Instalar PostgreSQL** (versión 16 o superior) desde [aquí](https://www.postgresql.org/download/windows/)
2. **Descargar los instaladores** desde la sección [Releases](https://github.com/migue28011988-bit/LMC-petroempleo/releases)
3. Ejecutar e Instalar `LMC-1.0-setup.exe` en el servidor
4. Ejecutar e Instalar `LMC_agente-1.0-setup.exe` en cada PC cliente
8. Agregar al PATH del sistema las variables de entorno de postgreSQL (la dirección donde se encuentra la carpeta PostgreSQL\xx\bin en el instalado) y java (la dirección donde se encuentra la carpeta runtime/bin en la app instalada)
9. Siempre ejecutar como administrador

## Compilar desde código

-Requiere Java 24+. Ver cada subproyecto.
-Este proyecto fue desarrollado con ANT por tanto las dependencias fueron incorporadas manualmente al IDE netbeans 25, puede descargar las dependencias usadas en el proyecto desde [Releases](https://github.com/migue28011988-bit/LMC-petroempleo/releases)