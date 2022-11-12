package example

import org.openjdk.jmh.annotations.{Benchmark, BenchmarkMode, Measurement, Mode, OutputTimeUnit, Warmup}

import java.util.concurrent.TimeUnit

class Bench {

  @Benchmark
  @BenchmarkMode(Array(Mode.Throughput))
  @Warmup(iterations = 5)
  @Measurement(iterations = 10)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  def methodStandardFilter(): IndexedSeq[Int] = {
    (1 to 50_000).filter(_ % 2 != 0).map(_ +2)
  }

  @Benchmark
  @BenchmarkMode(Array(Mode.Throughput))
  @Warmup(iterations = 5)
  @Measurement(iterations = 10)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  def methodWithFilter(): IndexedSeq[Int] = {
    (1 to 50_000).withFilter(_ % 2 != 0).map(_ +2)
  }

  @Benchmark
  @BenchmarkMode(Array(Mode.Throughput))
  @Warmup(iterations = 5)
  @Measurement(iterations = 10)
  def testWithView(): List[Int] = {
    (1 to 50_000).view.map(_*2).zipWithIndex.map{x => x._1 + x._2 }.toList}

  @Benchmark
  @BenchmarkMode(Array(Mode.Throughput))
  @Warmup(iterations = 5)
  @Measurement(iterations = 10)
  def testWithoutView(): IndexedSeq[Int] = {
    (1 to 50_000).map(_*2).zipWithIndex.map{x => x._1 + x._2 }}

  @Benchmark
  @BenchmarkMode(Array(Mode.Throughput))
  @Warmup(iterations = 5)
  @Measurement(iterations = 10)
  def testWithoutViewWithConversion(): List[Int] = {
    (1 to 50_000).map(_*2).zipWithIndex.map{x => x._1 + x._2 }.toList}
}