# Evidencia Laboratorio 6: Estado y Rotación

## Tabla de Comparación de Mecanismos

| Mecanismo | Mi predicción: pulsar / recomponer / rotar | Al pulsar | Al recomponer con otro control | Al rotar | Decisión final |
|---|---|---|---|---|---|
| **Variable local** | Se reinicia / Se reinicia / Se reinicia | Cambia el valor pero no se refleja en la UI | Se reinicia a 0 | Se reinicia a 0 | No sirve para estado mutable |
| **remember** | Mantiene / Mantiene / Se reinicia | La UI se actualiza correctamente | Mantiene el valor | Se reinicia a 0 | Solo sirve si no hay recreación de Activity |
| **rememberSaveable** | Mantiene / Mantiene / Mantiene | La UI se actualiza correctamente | Mantiene el valor | **Mantiene el valor** | **Ideal para estado de UI que debe persistir** |

## Respuestas a Preguntas de Conclusión

1. **¿Qué diferencia observaste entre pulsar un control y rotar el dispositivo?**
   Pulsar un control (como el Switch o el buscador) solo dispara una **recomposición** de Compose (se vuelven a ejecutar las funciones necesarias para dibujar). Rotar el dispositivo provoca una **recreación de la Activity**, donde el sistema destruye y vuelve a crear toda la instancia de la pantalla, ejecutando el ciclo de vida completo de Android.

2. **¿Qué callbacks aparecieron al rotar y en qué orden?**
   El orden observado en Logcat fue: `onPause` -> `onStop` -> `onDestroy` -> `onCreate` -> `onStart` -> `onResume`.

3. **¿Por qué la lista de resultados es un val derivado y no otro estado mutable?**
   Para mantener una "fuente única de verdad". Si fuera otro estado mutable, tendríamos que estar actualizándolo manualmente cada vez que cambie la búsqueda o los filtros, lo que causaría errores de sincronización. Al ser un `val` derivado, Compose lo recalcula automáticamente y de forma eficiente solo cuando cambian sus dependencias.

4. **Con la búsqueda vacía y el switch apagado, ¿qué títulos mostró cada pestaña y qué propiedades del modelo explican el resultado?**
   - **Para ti:** Mostró todos los artículos (Ana, Diego, Sofía).
   - **Siguiendo:** Mostró a Ana y Sofía, ya que son las únicas con `isAuthorFollowed = true`.
   - **Destacados:** Mostró a Diego y Sofía, ya que son los únicos con `isFeatured = true`.
