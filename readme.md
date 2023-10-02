Project has been created with command **sbt new scala/scala-seed.g8**

Print all accessible flags for jvm: **java -XX:+UnlockDiagnosticVMOptions -XX:+PrintFlagsFinal -version**

withFilter vs filter

java 13 benchmarks
[info] Benchmark                             Mode  Cnt    Score   Error   Units <br>
[info] Bench.methodStandardFilter           thrpt   50    1,921 ´ 0,027  ops/ms <br>
[info] Bench.methodWithFilter               thrpt   50    1,770 ´ 0,033  ops/ms <br>
[info] Bench.testWithView                   thrpt   50  716,945 ´ 7,104   ops/s <br>
[info] Bench.testWithoutView                thrpt   50  685,747 ´ 7,205   ops/s <br>
[info] Bench.testWithoutViewWithConversion  thrpt   50  535,259 ´ 7,316   ops/s <br>


[info] Benchmark                                    Mode  Cnt  Score   Error   Units <br>
[info] MonadicWrappersChecker.testFlatten          thrpt   40  2,631 ´ 0,058  ops/ms <br>
[info] MonadicWrappersChecker.testGetOrElse        thrpt   40  2,617 ´ 0,076  ops/ms <br>
[info] MonadicWrappersChecker.testIfElse           thrpt   40  3,184 ´ 0,050  ops/ms <br>
[info] MonadicWrappersChecker.testPatternMatching  thrpt   40  2,947 ´ 0,046  ops/ms <br>

[info] MonadicWrappersChecker.testFlatten          thrpt   40  2,686 ´┐Ż 0,033  ops/ms <br>
[info] MonadicWrappersChecker.testGetOrElse        thrpt   40  2,682 ´┐Ż 0,061  ops/ms <br>
[info] MonadicWrappersChecker.testIfElse           thrpt   40  3,228 ´┐Ż 0,035  ops/ms <br>
[info] MonadicWrappersChecker.testPatternMatching  thrpt   40  2,913 ´┐Ż 0,046  ops/ms <br>
[info] MonadicWrappersChecker.testFlatten           avgt   40  0,374 ´┐Ż 0,009   ms/op <br>
[info] MonadicWrappersChecker.testGetOrElse         avgt   40  0,369 ´┐Ż 0,002   ms/op <br>
[info] MonadicWrappersChecker.testIfElse            avgt   40  0,319 ´┐Ż 0,008   ms/op <br>
[info] MonadicWrappersChecker.testPatternMatching   avgt   40  0,338 ´┐Ż 0,011   ms/op <br>


[info] StringManipulation.testNumericStringRegex               thrpt   40  0,084 ´┐Ż 0,002  ops/ms <br>
[info] StringManipulation.testNumericStringWithIndexRetrieval  thrpt   40  0,487 ´┐Ż 0,008  ops/ms <br>
[info] StringManipulation.testSplitString                      thrpt   40  0,141 ´┐Ż 0,001  ops/ms <br>

[info] MonadicWrappersChecker.testFold                             avgt   40   0,396 ´┐Ż 0,011  ms/op <br>
[info] MonadicWrappersChecker.testGetOrElse                        avgt   40   0,390 ´┐Ż 0,015  ms/op <br>
[info] MonadicWrappersChecker.testIfElse                           avgt   40   0,319 ´┐Ż 0,009  ms/op <br>
[info] MonadicWrappersChecker.testPatternMatching                  avgt   40   0,342 ´┐Ż 0,002  ms/op <br>
