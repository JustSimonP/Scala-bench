package example

import org.openjdk.jmh.annotations.{Benchmark, BenchmarkMode, Measurement, Mode, OutputTimeUnit, Scope, State, Warmup}

import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
class MonadicWrappersChecker {
    val generatedOptions: Seq[Option[Int]] = (1 to 50_000).map { value => {if (value % 2 != 0) Option(value) else None}}

  @Benchmark
  @Warmup(iterations = 5)
  @Measurement(iterations = 8)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  @BenchmarkMode(Array(Mode.Throughput))
  def testFlatten() :Seq[Int] = {
    generatedOptions.flatten
  }

  @Benchmark
  @Warmup(iterations = 5)
  @Measurement(iterations = 8)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  @BenchmarkMode(Array(Mode.Throughput))
  def testPatternMatching() :Seq[Int] = {
    generatedOptions.map {
      case Some(value) => value
      case None => 1
    }
  }

  @Benchmark
  @Warmup(iterations = 5)
  @Measurement(iterations = 8)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  @BenchmarkMode(Array(Mode.Throughput))
  def testIfElse() :Seq[Int] = {
    generatedOptions.map { x => {
      if (x.isDefined) {
        x.get
      } else 1 }
    }
  }

  @Benchmark
  @Warmup(iterations = 5)
  @Measurement(iterations = 8)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  @BenchmarkMode(Array(Mode.Throughput))
  def testGetOrElse() :Seq[Int] = {
    generatedOptions.map { x => {
     x.getOrElse(1) }
    }
  }

}
