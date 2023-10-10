package notDefault

import org.openjdk.jmh.annotations.{Benchmark, BenchmarkMode, Measurement, Mode, OutputTimeUnit, Scope, Setup, State, Warmup}

import java.util.concurrent.TimeUnit
@State(Scope.Benchmark)
class LibraryEfficiency {

    val intCollection: Seq[Int] = 1 to 50000
//    def countStuffWithJava()= {
//      intCollection.map(value => (Math.log1p(value)+ Math.log(value) + Math.expm1(value)))
//    }


  @Benchmark
  @Warmup(iterations = 3)
  @Measurement(iterations = 8)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  @BenchmarkMode(Array(Mode.AverageTime))
  def stringConcatenation(): Seq[String] = {
    intCollection.map(value => value + "a" + value + "icweucunuw" + value + "abc")
  }

  @Benchmark
  @Warmup(iterations = 3)
  @Measurement(iterations = 8)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  @BenchmarkMode(Array(Mode.AverageTime))
  def stringInterpolation(): Seq[String] = {
    intCollection.map(value => s"${value}a${value}icweucunuw${value}abc")
  }
}
