package notDefault


import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.Blackhole

import java.util.concurrent.TimeUnit
import scala.util.Random

@State(Scope.Benchmark)
class BigTransformations {

  case class DataPlaceholder(firstName: String, lastName: String, age: Int, internalObject: InternalDataPlaceholder)
  case class InternalDataPlaceholder(someNumbers: Seq[Int], someText : String, deepObject: DeepDeepDataPlaceholder)
  case class DeepDeepDataPlaceholder(amOrNot: Option[String], basicString: String)

  case class Intermediate(fullName: String, internal: InternalDataPlaceholder)

  val bigCollectionToTransform: Seq[DataPlaceholder] = (1 to 100000)map {DataPlaceholder(Random.alphanumeric.take(10).toString(), Random.alphanumeric.take(10).toString(), _,
    InternalDataPlaceholder(someNumbers = (1 to 100).map(x => x), Random.alphanumeric.take(10).toString(), deepObject = DeepDeepDataPlaceholder(Some( Random.alphanumeric.take(10).toString()),  Random.alphanumeric.take(10).toString())))}


  @Benchmark
  @Warmup(iterations = 2)
  @Measurement(iterations = 9)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @BenchmarkMode(Array(Mode.Throughput))
  def testTransformationsImperatively(): collection.mutable.Seq[String] = {
    var index = 0
    var imperativeSeq = collection.mutable.Seq[String]()
    while (index < bigCollectionToTransform.size) {
      val element = bigCollectionToTransform.apply(index)
      val inter = Intermediate(fullName = element.firstName + element.lastName + element.age, internal = element.internalObject)
      val sumOfInts = inter.internal.someNumbers.sum
      val textValue = inter.internal.someText + inter.fullName + index
      val bigText = sumOfInts + textValue + inter.internal.deepObject.amOrNot.get
      imperativeSeq.prependedAll(bigText.grouped(2))
    }
      imperativeSeq
  }

  @Benchmark
  @Warmup(iterations = 2)
  @Measurement(iterations = 9)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @BenchmarkMode(Array(Mode.Throughput))
  def testDefaultTransformations(blackhole : Blackhole): Seq[String] = {
    val tempColl = bigCollectionToTransform.map {placeholder =>  Intermediate(fullName = placeholder.firstName+placeholder.lastName + placeholder.age, internal = placeholder.internalObject)}.zipWithIndex.map{case (someValue, index) =>
      val sumOfInts = someValue.internal.someNumbers.sum
      val textValue =  someValue.internal.someText + someValue.fullName + index
      (sumOfInts, textValue, someValue.internal.deepObject)
    }.flatMap{ case (sum, text, deepobject ) =>
      val bigText = sum + text + deepobject.amOrNot.get
      bigText.grouped(2).toSeq
    }
    blackhole.consume(tempColl)
    tempColl
  }


  @Benchmark
  @Warmup(iterations = 2)
  @Measurement(iterations = 9)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @BenchmarkMode(Array(Mode.Throughput))
  def testTransformationWithView(blackhole : Blackhole): Seq[String] = {
    val tempColl = bigCollectionToTransform.view.map { placeholder => Intermediate(fullName = placeholder.firstName + placeholder.lastName + placeholder.age, internal = placeholder.internalObject) }.zipWithIndex.map { case (someValue, index) =>
      val sumOfInts = someValue.internal.someNumbers.sum
      val textValue = someValue.internal.someText + someValue.fullName + index
      (sumOfInts, textValue, someValue.internal.deepObject)
    }.flatMap { case (sum, text, deepobject) =>
      val bigText = sum + text + deepobject.amOrNot.get
      bigText.grouped(2).toSeq
    }.toSeq

    blackhole.consume(tempColl)
    tempColl
  }


  @Benchmark
  @Warmup(iterations = 2)
  @Measurement(iterations = 9)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @BenchmarkMode(Array(Mode.Throughput))
  def testTransformationsWithIterator(blackhole : Blackhole): Seq[String] = {
    val tempColl = bigCollectionToTransform.iterator.map { placeholder => Intermediate(fullName = placeholder.firstName + placeholder.lastName + placeholder.age, internal = placeholder.internalObject) }.zipWithIndex.map { case (someValue, index) =>
      val sumOfInts = someValue.internal.someNumbers.sum
      val textValue = someValue.internal.someText + someValue.fullName + index
      (sumOfInts, textValue, someValue.internal.deepObject)
    }.flatMap { case (sum, text, deepobject) =>
      val bigText = sum + text + deepobject.amOrNot.get
      bigText.grouped(2).toSeq
    }.toSeq

    blackhole.consume(tempColl)
    tempColl
  }

}