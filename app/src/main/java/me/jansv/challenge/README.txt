- Proyecto

    GithubChallenge es una aplicacion que consume el API de github para obtener los
    usuarios con participacion en el lenguaje de Java.
    Estos usuarios son listados en la pantalla principal. La aplicacion
    aun esta en desarrollo, por tanto, contiene bugs y aun necesita nuevas funcionalidades.


- Tu tarea
    1. Encontrar el/los bug(s) en las clases del paquete ui.screens
    2. Implementar la funcionalidad de listado de repositorios para cada usuario.
       Al darle click al card de un usuario, se iniciara una nueva actividad ReposActivity.java
       en la que se veran los repositorios del usuario seleccionado.


- Observaciones
    1. La actividad ReposActivity.java tiene la configuracion inicial y necesaria para realizar
        la tarea, es MUY recomendable no cambiar dicha configuracion.
    2. En la actividad UsersActivity.java hay componentes marcados como "optional". Esto quiere decir
        que pueden ser eliminados en conjunto al codigo que depende de ellos y la aplicacion no se
        veria afectada.
    3. Es recomendable iniciar por la tarea numero 2 y luego trabajar con la numero 1.
    4. La forma en como se listan los repositorios es libre; puedes elegir el modo que prefieras.


- Evaluacion
    Se evaluara la entrega en terminos de porcentaje de cumplimiento, calidad y organizacion del codigo.


- Bono (Opcional)
    Esto es completamente opcional pero seria de gran ayuda si despues de completar las asignaciones,
    se logra cambiar la interfaz de Retrofit agregandole la integracion con RxJava, Es decir, que
    en vez de que los metodos de GithubService retornen Call<{Entity}>, se cambien para que
    retornen io.reactivex.Observable<{Entity}> y luego adaptar el codigo para trabajar con RxJava.

    Nota: El proyecto esta configurado para dicho cambio.
