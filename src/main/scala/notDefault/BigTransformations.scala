package notDefault


import org.openjdk.jmh.annotations._

import java.util.concurrent.TimeUnit
import scala.util.Random

@State(Scope.Benchmark)
class BigTransformations {

  case class DataPlaceholder(firstName: String, lastName: String, age: Int, internalObject: InternalDataPlaceholder)
  case class InternalDataPlaceholder(someNumbers: Seq[Int], someText : String, deepObject: DeepDeepDataPlaceholder)
  case class DeepDeepDataPlaceholder(amOrNot: Option[String], dupaString: String)

  case class Dupa(fullName: String, internal: InternalDataPlaceholder)

  val bigCollectionToTransform: Seq[DataPlaceholder] = (1 to 100000)map {DataPlaceholder(Random.alphanumeric.take(10).toString(), Random.alphanumeric.take(10).toString(), _,
    InternalDataPlaceholder(someNumbers = (1 to 100).map(x => x), Random.alphanumeric.take(10).toString(), deepObject = DeepDeepDataPlaceholder(Some( Random.alphanumeric.take(10).toString()),  Random.alphanumeric.take(10).toString())))}



  @Benchmark
  @Warmup(iterations = 2)
  @Measurement(iterations = 9)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @BenchmarkMode(Array(Mode.Throughput))
  def testDefaultTransformations(): Seq[String] = {
    bigCollectionToTransform.map {placeholder =>  Dupa(fullName = placeholder.firstName+placeholder.lastName + placeholder.age, internal = placeholder.internalObject)}.zipWithIndex.map{case (dupa, index) =>
      val sumOfInts = dupa.internal.someNumbers.sum
      val textValue =  dupa.internal.someText + dupa.fullName + index
      (sumOfInts, textValue, dupa.internal.deepObject)
    }.flatMap{ case (sum, text, deepobject ) =>
      val bigText = sum + text + deepobject.amOrNot.get
      bigText.grouped(2).toSeq
    }}


  @Benchmark
  @Warmup(iterations = 2)
  @Measurement(iterations = 9)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @BenchmarkMode(Array(Mode.Throughput))
  def testTransformationWithView(): Seq[String] = {
    bigCollectionToTransform.view.map { placeholder => Dupa(fullName = placeholder.firstName + placeholder.lastName + placeholder.age, internal = placeholder.internalObject) }.zipWithIndex.map { case (dupa, index) =>
      val sumOfInts = dupa.internal.someNumbers.sum
      val textValue = dupa.internal.someText + dupa.fullName + index
      (sumOfInts, textValue, dupa.internal.deepObject)
    }.flatMap { case (sum, text, deepobject) =>
      val bigText = sum + text + deepobject.amOrNot.get
      bigText.grouped(2).toSeq
    }
  }.toSeq


  @Benchmark
  @Warmup(iterations = 2)
  @Measurement(iterations = 9)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @BenchmarkMode(Array(Mode.Throughput))
  def testTransformationsWithIterator(): Seq[String] = {
    bigCollectionToTransform.iterator.map { placeholder => Dupa(fullName = placeholder.firstName + placeholder.lastName + placeholder.age, internal = placeholder.internalObject) }.zipWithIndex.map { case (dupa, index) =>
      val sumOfInts = dupa.internal.someNumbers.sum
      val textValue = dupa.internal.someText + dupa.fullName + index
      (sumOfInts, textValue, dupa.internal.deepObject)
    }.flatMap { case (sum, text, deepobject) =>
      val bigText = sum + text + deepobject.amOrNot.get
      bigText.grouped(2).toSeq
    }
  }.toSeq


//    @Benchmark
//    @Warmup(iterations = 2)
//    @Measurement(iterations = 9)
//    @OutputTimeUnit(TimeUnit.MILLISECONDS)
//    @BenchmarkMode(Array(Mode.Throughput))
//    def testTransformationsWithIterable(): Seq[String] = {
//      bigCollectionToTransform.map { placeholder => Dupa(fullName = placeholder.firstName + placeholder.lastName + placeholder.age, internal = placeholder.internalObject) }.zipWithIndex.map { case (dupa, index) =>
//        val sumOfInts = dupa.internal.someNumbers.sum
//        val textValue = dupa.internal.someText + dupa.fullName + index
//        (sumOfInts, textValue, dupa.internal.deepObject)
//      }.flatMap { case (sum, text, deepobject) =>
//        val bigText = sum + text + deepobject.amOrNot.get
//        bigText.grouped(2).toSeq
//      }
//    }
}