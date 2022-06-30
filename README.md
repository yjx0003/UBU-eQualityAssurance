# UBU-eQualityAssurance

## Resumen

Entre el auge de las tecnologías del ámbito educativo y las res-
tricciones sociales contra la COVID-19, se ha producido un cambio
masivo hacia un proceso de enseñanza y aprendizaje en línea enfo-
cado a plataformas online como puede ser Moodle o BlackBoard.
Este proceso y aprendizaje se le conoce también como e-learning y
su control de calidad es importante para la enseñanza actualmente.
Hay varios frameworks que calculan la calidad de una asignatura
online valorando desde las perspectivas pedagógicas, tecnológicas
y estratégica por una parte. Por otra parte se identifican los roles
de diseñador, facilitador y proveedor. Y por último las diferentes
fases de análisis, diseño, implementación, evaluación. Sin embargo,
no existen muchas herramientas automáticas de control de la calidad
en el ámbito práctico de los cursos en línea.
Por este motivo se ha realizado el presente proyecto, UBU e-
Quality Assurance, una herramienta de ordenador que adapta la
checklist del Centro de Enseñanza Virtual (UBUCEV) y también
un framework de calidad con varias métricas aplicadas a los cursos
de Moodle. Esta herramienta generará tablas anidadas de reglas
mostrando los porcentajes al validar los diferentes componentes del
curso.

## Dependencies:

### Java dependencies:

* **JSON In Java**
  * Versión: **20190722**
  * [Github](https://github.com/stleary/JSON-java)
  * [Maven Repository](https://mvnrepository.com/artifact/org.json/json)
* [JSoup Java HTML Parser](https://jsoup.org/)
  * Versión: **1.12.1**
  * [Github](https://github.com/jhy/jsoup)
  * [Maven Repository](https://mvnrepository.com/artifact/org.jsoup/jsoup/1.11.3)
* [OkHttp](https://square.github.io/okhttp/)
  * Versión: **4.4.1**
  * [Github](https://github.com/square/okhttp/)
  * [Maven Repository](https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp)
* [SLF4J API Module](https://www.slf4j.org/)
  * Versión: **1.7.26**
  * [Github](https://github.com/qos-ch/slf4j)
  * [Maven Repository](https://mvnrepository.com/artifact/org.slf4j/slf4j-api)
* [SLF4J LOG4J 12 Binding](https://www.slf4j.org/)
  * Versión: **1.7.26**
  * [Github](https://github.com/qos-ch/slf4j/tree/master/slf4j-log4j12)
  * [Maven Repository](https://mvnrepository.com/artifact/org.slf4j/slf4j-log4j12)
 ### JavaScript dependencies:
 * [Tabulator](http://tabulator.info/)
  * Versión: **5.2**
  * [Github](https://github.com/olifolkerd/tabulator)
  * [CDNJS](https://cdnjs.com/libraries/tabulator)
