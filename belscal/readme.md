Project has been created with command **sbt new scala/scala-seed.g8**

Print all accessible flags for jvm: **java -XX:+UnlockDiagnosticVMOptions -XX:+PrintFlagsFinal -version**

withFilter vs filter

java 13 benchmarks
[info] Benchmark                             Mode  Cnt    Score   Error   Units
[info] Bench.methodStandardFilter           thrpt   50    1,921 ´ 0,027  ops/ms
[info] Bench.methodWithFilter               thrpt   50    1,770 ´ 0,033  ops/ms
[info] Bench.testWithView                   thrpt   50  716,945 ´ 7,104   ops/s
[info] Bench.testWithoutView                thrpt   50  685,747 ´ 7,205   ops/s
[info] Bench.testWithoutViewWithConversion  thrpt   50  535,259 ´ 7,316   ops/s


[info] Benchmark                                    Mode  Cnt  Score   Error   Units
[info] MonadicWrappersChecker.testFlatten          thrpt   40  2,631 ´┐Ż 0,058  ops/ms
[info] MonadicWrappersChecker.testGetOrElse        thrpt   40  2,617 ´┐Ż 0,076  ops/ms
[info] MonadicWrappersChecker.testIfElse           thrpt   40  3,184 ´┐Ż 0,050  ops/ms
[info] MonadicWrappersChecker.testPatternMatching  thrpt   40  2,947 ´┐Ż 0,046  ops/ms

