package ar.edu.unahur.obj2.tareas

interface Tarea {
    val responsable : Empleado
    fun horasNecesarias() : Int
    fun costoDeLaTarea() : Int
    fun nominaDeEmpleados() : List<Empleado>
}

class TareaComun(val costoInfraestructura : Int, val horasEstimadas: Int, override val responsable: Empleado) : Tarea {
    var empleados = mutableListOf<Empleado>()

    override fun horasNecesarias() = horasEstimadas / empleados.size

    fun salario(empleado: Empleado) = empleado.cobroPorHoraTrabajada * this.horasNecesarias()
    fun salarioResponsable() = responsable.cobroPorHoraTrabajada * horasEstimadas
    fun salarioTotalEmpleados() = empleados.sumBy { salario(it) }

    override fun costoDeLaTarea() =
        costoInfraestructura + salarioTotalEmpleados() + salarioResponsable()

    override fun nominaDeEmpleados(): List<Empleado> = empleados + responsable
}

class TareaDeIntegracion(val horasEstimadas: Int, override val responsable: Empleado) : Tarea{
    val subtareas = mutableListOf<Tarea>()

    fun horasSubtareas() = subtareas.sumBy { it.horasNecesarias() }
    fun horasDeReunion() = this.horasSubtareas() / 8
    override fun horasNecesarias() =  this.horasSubtareas() + this.horasDeReunion()

    fun costoSubtareas() = subtareas.sumBy { it.costoDeLaTarea()}
    fun bonusResponsable() = this.costoSubtareas() * 0.3
    override fun costoDeLaTarea() = (this.costoSubtareas() + this.bonusResponsable()).toInt()

    override fun nominaDeEmpleados(): List<Empleado> = subtareas.flatMap{ it.nominaDeEmpleados() } + responsable
}