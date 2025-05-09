# Roller_Speed

La escuela de patinaje "Roller Speed", ubicada en Santa Marta, Colombia, enfrenta desafíos en la administración de sus alumnos, clases, instructores y pagos. Actualmente, estos procesos se realizan de manera manual, lo que dificulta el seguimiento y la organización eficiente de la escuela.

Para mejorar su gestión, se implementará una plataforma web monolítica que permitirá la inscripción en línea de nuevos aspirantes, la gestión de pagos, el control de asistencia y la consulta de horarios. Adicionalmente, se incluirá un módulo de divulgación institucional que informará sobre la misión, visión, valores, servicios y eventos de la escuela.

Objetivo del Sistema

El objetivo de la aplicación es automatizar y optimizar la gestión de la escuela de patinaje "Roller Speed", facilitando el acceso a la información para administradores, instructores y alumnos.

Se permitirá que los aspirantes se registren directamente en la plataforma, ingresando sus datos personales y medio de pago, sin necesidad de aprobación manual por parte del administrador.

Alcance del Proyecto Usuarios del sistema y sus roles

Rol

Descripción

Administrador

Responsable de la gestión de alumnos, instructores, pagos y clases. Puede generar reportes y gestionar la información institucional.

Instructor

Consulta los horarios de clases, gestiona su lista de alumnos y registra asistencia.

Alumno/Aspirante

Se registra en la plataforma con sus datos personales y método de pago. Puede acceder a su información académica y financiera.

Público general

Accede a la información sobre la escuela, sus valores y servicios.

Módulos principales

Registro de Aspirantes

Registro en línea con datos personales.

Selección del método de pago.

Gestión de Alumnos

Edición de perfil y consulta de estado de pagos.

Acceso a horarios y notificaciones.

Gestión de Instructores

Registro y administración de instructores.

Asignación de instructores a clases.

Gestión de Clases

Programación de horarios y niveles.

Visualización de clases asignadas.

Gestión de Pagos

Registro de pagos según el método seleccionado.

Notificación automática de pagos pendientes.

Generación de reportes de pagos.

Registro de Asistencia

Control de asistencia en cada clase por parte del instructor.

Reportes de asistencia por alumno.

Divulgación de Información Institucional

Página de presentación de la escuela con historia, propósito y logros.

Sección de misión, visión y valores.

Listado de servicios (entrenamiento, clases para principiantes, preparación para competencias, etc.).

Galería de fotos y videos con imágenes de entrenamientos, eventos y logros.

Testimonios de alumnos y padres sobre su experiencia en la escuela.

Noticias y próximos eventos relacionados con la escuela y el patinaje.

Requerimientos del Sistema

Requerimientos Funcionales

Registro de Aspirantes y Alumnos

Los aspirantes pueden registrarse en la plataforma sin intervención del administrador.

Formulario de inscripción con nombre, fecha Nacimiento ,Genero, correo, teléfono y medio de pago.

Generación automática de cuenta con rol de "Alumno" tras el registro.

Gestión de Alumnos

Registro y edición de datos personales.

Acceso a horarios y estado de pagos.

Gestión de Instructores

Registrar instructores con sus datos personales.

Asignar instructores a clases específicas.

Gestión de Clases

Programar horarios y niveles de patinaje.

Asignar instructores y alumnos a cada clase.

Gestión de Pagos

Selección del método de pago al momento del registro.

Registro de Asistencia

Permitir que los instructores registren asistencia de los alumnos.

Generar reportes de asistencia por alumno.

Divulgación de Información Institucional

Página con la misión, visión y valores corporativos.

Servicios detallados con información sobre las clases y programas ofrecidos.

Galería multimedia con fotos y videos de entrenamientos y competencias.

Testimonios de alumnos y padres.

Noticias y eventos de la escuela con calendario de actividades.

Requerimientos No Funcionales

Arquitectura Monolítica

Aplicación basada en Spring Boot y Thymeleaf con patrón MVC(Model-View-Controller).

Seguridad y Control de Accesos

Spring Security para autenticación y autorización de usuarios.

Cifrado de contraseñas y validaciones en formularios.

Restricción de accesos según el rol del usuario.

Base de Datos Relacional

Uso de PostgreSQL o MySQL para almacenamiento de información.

Uso de Spring JPA para aprovechar el ecosistema de Spring y optar por utilizar prácticas más modernas

Frontend embebido con Thymeleaf

Interfaz intuitiva con Bootstrap 5 para compatibilidad móvil.

Escalabilidad y Mantenibilidad

Código modular y estructurado con buenas prácticas en Spring Boot.