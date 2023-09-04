import java.text.SimpleDateFormat
import java.util.*


enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }
enum class NivelConhecimento { JUNIOR, PLENO, SENIOR }


data class Usuario(
    val nome:  String,
    val idade: Int,
    val nivConhecimento: NivelConhecimento,
    val cidadereside: String,
    val endereco: String,
    val telefone: String
    )

data class ConteudoEducacional(var nome: String, val duracao: Int = 60, val nivel: Nivel)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {

    val inscritosEDataMatricula = mutableMapOf<Usuario,Long>()
        fun matricular(usuario: Usuario) {
        inscritosEDataMatricula[usuario] = System.currentTimeMillis()

    }
}

fun main() {
    val cursoDeDireito = Formacao(nome = "Curso de direito",conteudos = listOf(ConteudoEducacional(nome = "Direito penal", duracao = 120, nivel = Nivel.DIFICIL)))
    val rayanne = Usuario(nome = "Rayanne", idade = 28, nivConhecimento = NivelConhecimento.PLENO, cidadereside = "São Paulo", endereco = "Rua XXX" , telefone = "81 9 96486131")
    val vinicius = Usuario(nome = "Vinicius", idade = 28, nivConhecimento = NivelConhecimento.PLENO, cidadereside = "São Paulo", endereco = "Rua XXX" , telefone = "81 9 96486131")
    cursoDeDireito.matricular(rayanne)
    cursoDeDireito.matricular(vinicius)
    val numeroInscritos = cursoDeDireito.inscritosEDataMatricula.size
    println("numeroInscritos do curso de direito = $numeroInscritos")
    cursoDeDireito.inscritosEDataMatricula.forEach {
        val date = getDate(it.value,"dd/MM/yyyy hh:mm:ss.SSS")
        println("usuario ${it.key.nome} matriculado no curso de Direito em $date")
    }
    listarInformacoesAlunosCurso(cursoDeDireito )
}

fun getDate(milliSeconds: Long, dateFormat: String?): String? {
    val formatter = SimpleDateFormat(dateFormat)
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = milliSeconds
    return formatter.format(calendar.time)
}

fun listarInformacoesAlunosCurso(curso: Formacao) {
    curso.inscritosEDataMatricula.forEach {
        println("Dados de ${it.key.nome} : idade = ${it.key.idade}, nivel de conhecimento = ${it.key.nivConhecimento}, cidade = ${it.key.cidadereside}, endereço = ${it.key.endereco}, telefone = ${it.key.telefone} ")
    }
}