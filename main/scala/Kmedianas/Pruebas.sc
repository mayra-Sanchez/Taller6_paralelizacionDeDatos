import Kmedianas.{actualizarPar, actualizarSeq, clasificarPar, clasificarSeq, generarPuntosPar, generarPuntosSeq, hayConvergenciaPar, hayConvergenciaSeq, inicializarMedianasPar, inicializarMedianasSeq, kMedianasPar, kMedianasSeq}
import org.scalameter._

val standardConfig = config (
  Key.exec.minWarmupRuns := 20,
  Key.exec.minWarmupRuns := 40,
  Key.exec.benchRuns := 25,
  Key.verbose := false
) withWarmer(new Warmer.Default)

//Puntos
val numPuntos1 = 500000
val numPuntos2 = 600000
val numPuntos3 = 100000
val numPuntos4 = 50000
val numPuntos5 = 90000

//Kluseh 2, 4, 8, 16 y 32
val k1 = 2
val k2 = 4
val k3 = 8
val k4 = 16
val k5 = 32

// Valores eta
val eta1 = 0.01
val eta2 = 0.02
val eta3 = 0.03
val eta4 = 0.04
val eta5 = 0.5

//Crear puntos seq
val puntosSeq1 = generarPuntosSeq (k1, numPuntos1)
val puntosSeq2 = generarPuntosSeq (k2, numPuntos2)
val puntosSeq3 = generarPuntosSeq (k3, numPuntos3)
val puntosSeq4 = generarPuntosSeq (k4, numPuntos4)
val puntosSeq5 = generarPuntosSeq (k5, numPuntos5)

//Crear medianas seq
val medianasSeq1 = inicializarMedianasSeq (k1, puntosSeq1)
val medianasSeq2 = inicializarMedianasSeq (k2, puntosSeq2)
val medianasSeq3 = inicializarMedianasSeq (k3, puntosSeq3)
val medianasSeq4 = inicializarMedianasSeq (k4, puntosSeq4)
val medianasSeq5 = inicializarMedianasSeq (k5, puntosSeq5)

//Crear puntos par
val puntosPar1 = generarPuntosPar ( k1 , numPuntos1 )
val puntosPar2 = generarPuntosPar ( k2 , numPuntos2 )
val puntosPar3 = generarPuntosPar ( k3 , numPuntos3 )
val puntosPar4 = generarPuntosPar ( k4 , numPuntos4 )
val puntosPar5 = generarPuntosPar ( k5, numPuntos5 )

//Crear medianas par
val medianasPar1 = inicializarMedianasPar(k1 , puntosPar1 )
val medianasPar2 = inicializarMedianasPar(k2 , puntosPar2 )
val medianasPar3 = inicializarMedianasPar(k3 , puntosPar3 )
val medianasPar4 = inicializarMedianasPar(k4 , puntosPar4 )
val medianasPar5 = inicializarMedianasPar(k5 , puntosPar5 )

//Clasificar
val calificar_S1 = clasificarSeq(puntosSeq1,medianasSeq1)
val calificar_S2 = clasificarSeq(puntosSeq2,medianasSeq2)
val calificar_S3 = clasificarSeq(puntosSeq3,medianasSeq3)
val calificar_S4 = clasificarSeq(puntosSeq4,medianasSeq4)
val calificar_S5 = clasificarSeq(puntosSeq5,medianasSeq5)

val calificar_P1 = clasificarPar(puntosPar1,medianasPar1)
val calificar_P2 = clasificarPar(puntosPar2,medianasPar2)
val calificar_P3 = clasificarPar(puntosPar3,medianasPar3)
val calificar_P4 = clasificarPar(puntosPar4,medianasPar4)
val calificar_P5 = clasificarPar(puntosPar5,medianasPar5)

//Actualizar
val Actualizar_S1 = actualizarSeq(calificar_S1,medianasSeq1)
val Actualizar_S2 = actualizarSeq(calificar_S2,medianasSeq2)
val Actualizar_S3 = actualizarSeq(calificar_S3,medianasSeq3)
val Actualizar_S4 = actualizarSeq(calificar_S4,medianasSeq4)
val Actualizar_S5 = actualizarSeq(calificar_S5,medianasSeq5)

val actualizar_P1 = actualizarPar(calificar_P1,medianasPar1)
val actualizar_P2 = actualizarPar(calificar_P2,medianasPar2)
val actualizar_P3 = actualizarPar(calificar_P3,medianasPar3)
val actualizar_P4 = actualizarPar(calificar_P4,medianasPar4)
val actualizar_P5 = actualizarPar(calificar_P5,medianasPar5)

//hayConvergencia
val convergencia_S1 = hayConvergenciaSeq(eta1,medianasSeq1,Actualizar_S1)
val convergencia_S2 = hayConvergenciaSeq(eta2,medianasSeq2,Actualizar_S2)
val convergencia_S3 = hayConvergenciaSeq(eta3,medianasSeq3,Actualizar_S3)
val convergencia_S4 = hayConvergenciaSeq(eta4,medianasSeq4,Actualizar_S4)
val convergencia_S5 = hayConvergenciaSeq(eta5,medianasSeq5,Actualizar_S5)

val convergencia_P1 = hayConvergenciaPar(eta1,medianasPar1,actualizar_P1)
val convergencia_P2 = hayConvergenciaPar(eta2,medianasPar2,actualizar_P2)
val convergencia_P3 = hayConvergenciaPar(eta3,medianasPar3,actualizar_P3)
val convergencia_P4 = hayConvergenciaPar(eta4,medianasPar4,actualizar_P4)
val convergencia_P5 = hayConvergenciaPar(eta5,medianasPar5,actualizar_P5)

//kMedianas
val tiempo_S1 = standardConfig measure { kMedianasSeq(puntosSeq1,medianasSeq1,eta1) }
val tiempo_S2 = standardConfig measure { kMedianasSeq(puntosSeq2,medianasSeq2,eta2) }
val tiempo_S3 = standardConfig measure { kMedianasSeq(puntosSeq3,medianasSeq3,eta3) }
val tiempo_S4 = standardConfig measure { kMedianasSeq(puntosSeq4,medianasSeq4,eta4) }
val tiempo_S5 = standardConfig measure { kMedianasSeq(puntosSeq5,medianasSeq5,eta5) }


val tiempo_P1 = standardConfig measure { kMedianasPar(puntosPar1,medianasPar1,eta1) }
val tiempo_P2 = standardConfig measure { kMedianasPar(puntosPar2,medianasPar2,eta2) }
val tiempo_P3 = standardConfig measure { kMedianasPar(puntosPar3,medianasPar3,eta3) }
val tiempo_P4 = standardConfig measure { kMedianasPar(puntosPar4,medianasPar4,eta4) }
val tiempo_P5 = standardConfig measure { kMedianasPar(puntosPar5,medianasPar5,eta5) }