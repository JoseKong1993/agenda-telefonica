# 📱 Agenda Telefónica en Java

Sistema de gestión de contactos telefónicos desarrollado en Java con persistencia en archivo CSV.

## 📋 Descripción del Proyecto

**Actividad 4 - Tema 12**  
Aplicación de consola que permite gestionar una agenda telefónica con las siguientes funcionalidades:
- Crear nuevos contactos
- Listar todos los contactos
- Eliminar contactos existentes
- Buscar contactos por nombre o número
- Persistencia automática en archivo CSV

## 🎯 Características Principales

- ✅ Almacenamiento en `HashMap<String, String>`
- ✅ Persistencia en archivo CSV
- ✅ Validación de números telefónicos
- ✅ Búsqueda avanzada de contactos
- ✅ Interfaz de menú interactivo
- ✅ Manejo robusto de errores
- ✅ Guardado automático de cambios

## 🚀 Cómo Ejecutar

### Requisitos
- Java JDK 8 o superior
- NetBeans IDE (opcional)

### Compilación y Ejecución

```bash
# Compilar
javac AddressBook.java

# Ejecutar
java AddressBook
```

### Usando NetBeans
1. Abrir NetBeans IDE
2. File → New Project → Java Application
3. Copiar el código de `AddressBook.java`
4. Run Project (F6)

## 📂 Estructura del Proyecto

```
agenda-telefonica/
├── src/
│   └── AddressBook.java        # Código fuente principal
├── screenshots/                 # Capturas de pantalla
│   ├── 01-menu-principal.png
│   ├── 02-listar-contactos.png
│   ├── 03-crear-contacto.png
│   ├── 04-eliminar-contacto.png
│   ├── 05-buscar-contacto.png
│   └── 06-archivo-csv.png
├── docs/
│   └── Reporte_AgendaTelefonica.pdf  # Reporte completo
├── contactos.csv               # Archivo de datos (ejemplo)
└── README.md                   # Este archivo
```

## 💻 Funcionalidades

### 1. Listar Contactos
Muestra todos los contactos en formato:
```
Contactos: {5551234567} : {Juan Perez} {5559876543} : {Maria Garcia}
```

### 2. Crear Contacto
- Solicita número telefónico (con validación)
- Solicita nombre del contacto
- Detecta duplicados
- Guarda automáticamente

### 3. Eliminar Contacto
- Solicita confirmación
- Elimina por número telefónico
- Actualiza el archivo CSV

### 4. Buscar Contactos (Funcionalidad Extra)
- Búsqueda por nombre o número
- Búsqueda case-insensitive
- Muestra todos los resultados coincidentes

## 📄 Formato del Archivo CSV

```csv
numero,nombre
5551234567,Juan Perez
5559876543,Maria Garcia
+52 981 111 2222,Pedro Lopez
```

## 🎨 Interfaz de Usuario

```
=========================================
    BIENVENIDO A LA AGENDA TELEFONICA    
=========================================

========================================
           MENU PRINCIPAL
========================================
1. Listar contactos
2. Crear contacto
3. Eliminar contacto
4. Buscar contactos
5. Guardar cambios
6. Recargar desde archivo
0. Salir
========================================
Seleccione una opcion: 
```

## 🔍 Validaciones Implementadas

- ✅ Formato de número telefónico (números, espacios, guiones, paréntesis, +)
- ✅ Campos no vacíos
- ✅ Detección de duplicados
- ✅ Confirmación para eliminaciones
- ✅ Manejo de archivos inexistentes

## 📊 Tecnologías Utilizadas

- **Lenguaje:** Java 8+
- **Estructura de datos:** HashMap
- **Persistencia:** Archivo CSV (texto plano)
- **IDE recomendado:** NetBeans / IntelliJ IDEA / Eclipse

## 📸 Capturas de Pantalla

### Menú Principal
![Menú Principal](screenshots/01-menu-principal.png)

### Listado de Contactos
![Listar Contactos](screenshots/02-listar-contactos.png)

### Crear Contacto
![Crear Contacto](screenshots/03-crear-contacto.png)

### Eliminar Contacto
![Eliminar Contacto](screenshots/04-eliminar-contacto.png)

### Buscar Contactos
![Buscar Contacto](screenshots/05-buscar-contacto.png)

### Archivo CSV Generado
![Archivo CSV](screenshots/06-archivo-csv.png)

## 📝 Documentación

Para más detalles sobre la implementación, consulta el [Reporte Completo](docs/Reporte_AgendaTelefonica.pdf).

## 🧪 Casos de Prueba

### Pruebas Realizadas
- ✅ Crear contacto con diferentes formatos de número
- ✅ Listar contactos vacíos y con datos
- ✅ Eliminar contactos existentes e inexistentes
- ✅ Buscar por nombre parcial
- ✅ Buscar por número parcial
- ✅ Manejo de archivo no existente
- ✅ Validación de números inválidos
- ✅ Actualización de contactos duplicados

## 🎓 Autor

**[Tu Nombre Completo]**
- 📧 Email: [tu_email@universidad.edu]
- 🎓 Universidad: [Nombre de tu Universidad]
- 📚 Materia: Programación en Java
- 📅 Fecha: Septiembre 2025

## 📋 Actividad Académica

- **Actividad:** 4
- **Tema:** 12
- **Institución:** [Tu Universidad]
- **Profesor:** [Nombre del Profesor]

## 🔗 Enlaces

- [Repositorio en GitHub](https://github.com/[tu-usuario]/agenda-telefonica)
- [Reporte PDF](docs/Reporte_AgendaTelefonica.pdf)

## 📜 Licencia

Este proyecto fue desarrollado con fines académicos.

---

⭐ **Si este proyecto te fue útil, no olvides darle una estrella!**
