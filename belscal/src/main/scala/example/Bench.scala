package example

import org.openjdk.jmh.annotations.{Benchmark, BenchmarkMode, Fork, Measurement, Mode, Warmup}
import org.openjdk.jmh.infra.Blackhole

class Bench {

  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime))
  @Fork(value = 2)
  @Warmup(iterations = 2)
  @Measurement(iterations = 2)
  def testMethod(blackHole: Blackhole): Double = {
    val list: List[Int] = List.range(1, 1000)
    val sum: Double = list.sum
    blackHole.consume(sum)
    sum
  }
}