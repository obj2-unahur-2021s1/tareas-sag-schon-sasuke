package ar.edu.unahur.obj2.tareas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class TareaTest : DescribeSpec({
  val doroti = Empleado(500)
  val empleado1 = Empleado(200)
  val empleado2 = Empleado(200)
  val tarea = TareaComun(11000,8,doroti)


  describe("Una tarea") {
    tarea.empleados.add(empleado1)
    tarea.empleados.add(empleado2)

    it("nómina de empleados y su resposable"){
      tarea.nominaDeEmpleados().shouldBe(listOf(empleado1,empleado2,doroti))
    }
    it("cuántas horas se necesitan para finalizar"){
      tarea.horasNecesarias().shouldBe(4)
    }
    it("Costo de una tarea"){
      tarea.costoDeLaTarea().shouldBe(16600)
    }
  }


  describe("Tarea de integracion"){
  val tareaDeIntegracion = TareaDeIntegracion(300,doroti)
    tarea.empleados.add(empleado1)
    tareaDeIntegracion.subtareas.add(tarea)

    it("Responsable") {
      tareaDeIntegracion.responsable.shouldBe(doroti)
    }
    it("Horas necesarias"){
      tareaDeIntegracion.horasNecesarias().shouldBe(9)
    }

    it("Costos"){
      tareaDeIntegracion.costoDeLaTarea().shouldBe(21580)
    }
  }
})