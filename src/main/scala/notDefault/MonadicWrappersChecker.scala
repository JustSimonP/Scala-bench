package notDefault

import org.openjdk.jmh.annotations._

import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
class MonadicWrappersChecker {
  val generatedOptions: Seq[Option[Int]] = (1 to 50_000).map { value => {if (value % 2 != 0) Option(value) else None}}

  @Benchmark
  @Warmup(iterations = 3)
  @Measurement(iterations = 8)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  @BenchmarkMode(Array(Mode.AverageTime))
  def testFlatten() :Seq[Int] = {
    generatedOptions.flatten
  }

  @Benchmark
  @Warmup(iterations = 3)
  @Measurement(iterations = 8)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  @BenchmarkMode(Array(Mode.AverageTime))
  def testFlatMap() :Seq[Int] = {
    generatedOptions.flatMap(x => x)
  }

  @Benchmark
  @Warmup(iterations = 3)
  @Measurement(iterations = 8)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  @BenchmarkMode(Array(Mode.AverageTime))
  def testPatternMatching() :Seq[Int] = {
    generatedOptions.map {
      case Some(value) => value
      case None => 1
    }
  }

  @Benchmark
  @Warmup(iterations = 3)
  @Measurement(iterations = 8)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  @BenchmarkMode(Array(Mode.AverageTime))
  def testIfElse() :Seq[Int] = {
    generatedOptions.map { x => {
      if (x.isDefined) {
        x.get
      } else 1 }
    }
  }

  @Benchmark
  @Warmup(iterations = 3)
  @Measurement(iterations = 8)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  @BenchmarkMode(Array(Mode.AverageTime))
  def testGetOrElse() :Seq[Int] = {
    generatedOptions.map { x => {
     x.getOrElse(1) }
    }
  }

  @Benchmark
  @Warmup(iterations = 3)
  @Measurement(iterations = 8)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  @BenchmarkMode(Array(Mode.AverageTime))
  def testFold() :Seq[Int] = {
    generatedOptions.map { x => {
      x.fold(1)(x => x) }
    }
  }

}
