package example

import org.openjdk.jmh.annotations.{Benchmark, BenchmarkMode, Measurement, Mode, OutputTimeUnit, Warmup}

import java.util.concurrent.TimeUnit

class StringManipulation {

  val generatedStrings = (1 to 50_000).map { value => s"here:hrn:dupa:${value + 3256}"}

  @Benchmark
  @Warmup(iterations = 3)
  @Measurement(iterations = 8)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  @BenchmarkMode(Array(Mode.AverageTime))
  def testSplitString()  = {
    generatedStrings.map(x => x.split(":").last)
  }

  @Benchmark
  @Warmup(iterations = 3)
  @Measurement(iterations = 8)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  @BenchmarkMode(Array(Mode.AverageTime))
  def testNumericStringRegex()  = {
    generatedStrings.map(x => ("""\d+""".r findFirstIn x ).get)
  }

  @Benchmark
  @Warmup(iterations = 3)
  @Measurement(iterations = 8)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  @BenchmarkMode(Array(Mode.AverageTime))
  def testNumericStringWithIndexRetrieval()  = {
    generatedStrings.map(x => x.substring(x.lastIndexOf(":")+1))
  }


}
