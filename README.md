# ChatIAVision v1

## Descripción

ChatIAVision es una aplicación que permite almacenar sus propios chats de forma privada. Esta aplicación utiliza conexión directa con **gemini-2.0-flash** (requiere API key) y **Room** como base de datos local. La lógica del proyecto permite añadir contexto a la conversación y guardarla en Room, así como enviar imágenes a gemini mediante el uso de la cámara. Por el momento, solo es posible para una conversación y no almacena las imágenes enviadas a gemini.

## Características

- **Conexión con gemini-2.0-flash**: Requiere API key.
- **Base de datos local**: Utiliza Room para almacenar las conversaciones.
- **Añadir contexto a la conversación**: Lógica para guardar el contexto en la base de datos.
- **Enviar imágenes**: Integración con la cámara para enviar imágenes a gemini.
- **Chat privado**: Mantén tus conversaciones privadas desde un dispositivo móvil.

## Requisitos

- Android Studio
- API key para gemini-2.0-flash
- Dispositivo Android con cámara

## Instalación

1. Clona este repositorio en tu máquina local:

    git clone https://github.com/ze0rX/ChatIAVision.git

2. Abre el proyecto en Android Studio.

3. Asegúrate de tener una API key válida para gemini-2.0-flash y configúrala en el proyecto.

4. Conecta tu dispositivo Android o utiliza un emulador.

5. Compila y ejecuta la aplicación.

## Uso

1. Abre la aplicación en tu dispositivo Android.
2. Conéctate a gemini-2.0-flash ingresando tu API key.
3. Inicia una conversación y añade el contexto que deseas almacenar.
4. Envía imágenes a gemini utilizando la cámara de tu dispositivo.
5. Mantén tus conversaciones privadas y seguras.

## Contribuciones

¡Las contribuciones son bienvenidas! Si deseas contribuir a este proyecto, por favor sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-caracteristica`).
3. Realiza tus cambios y commitea (`git commit -am 'Añadir nueva característica'`).
4. Sube tu rama (`git push origin feature/nueva-caracteristica`).
5. Crea un Pull Request.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.

## Contacto

Para preguntas o sugerencias, puedes contactarme en system32x86@outlook.com.
