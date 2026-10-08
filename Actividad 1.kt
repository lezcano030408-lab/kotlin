/**
 * You can edit, run, and share this code.
 * play.kotlinlang.org
 */
fun main() {
    // 1. Crear varios usuarios con distintos roles
    val admin = Usuario("carlos_admin", "12345", Rol.ADMIN)
    val editor = Usuario("maria_editor", "abcde", Rol.EDITOR)
    val visitante = Usuario("juan_invitado", "00000", Rol.VISITANTE)

    println("=== PRUEBA DE AUTENTICACIÓN ===")
    println("Login admin correcto: ${admin.login("12345")}")       
    println("Login editor incorrecto: ${editor.login("wrongpass")}") // Imprime: false

    println("\n=== REGISTRO Y PERMISOS DE USUARIOS ===")
    // Registrar usuarios en el singleton
    GestorUsuarios.registrarUsuario(admin)
    GestorUsuarios.registrarUsuario(editor)
    GestorUsuarios.registrarUsuario(visitante)

    println("\n=== VALIDACIÓN DE ROLES CON WHEN ===")
    // Verificar los permisos según el rol asignado
    GestorUsuarios.verificarPermisos(admin)
    GestorUsuarios.verificarPermisos(editor)
    GestorUsuarios.verificarPermisos(visitante)
}

  enum class Rol {
        ADMIN,
        EDITOR,
        VISITANTE
    }
    
    interface Autenticable{
        val username: String
        fun login(password:String): Boolean
    }
    
    data class Usuario(
    override val username: String,
    	val contraseña: String,
    	val rol: Rol
    ) : Autenticable{
        
        override fun login(password: String): Boolean {
            return this.contraseña == password
        }
    }
    
    object GestorUsuarios {
        private val listaUsuarios = mutableListOf<Usuario>()
        
        fun registrarUsuario(usuario: Usuario) { 
        	listaUsuarios.add(usuario)
        println("Usuario '${usuario.username}' registrado con exito.")
        }
        
        fun verificarPermisos(usuario:Usuario){
            when(usuario.rol){
                Rol.ADMIN -> println("Acceso total: El usuario '${usuario.username}' puede crear, editar y eliminar recursos.")
                Rol.EDITOR -> println("Acceso limitado: El usuario '${usuario.username}' solo puede crear y editar contenido.")
                Rol.VISITANTE -> println("Acceso restringido: El usuario '${usuario.username}' solo puede ver contenido.")
            }
        }
    }
