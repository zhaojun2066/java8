## 流的操作
    Intermediate：一个流可以后面跟随零个或多个 intermediate 操作。其目的主要是打开流，做出某种程度的数据映射/过滤，然后返回一个新的流，交给下一个操作使用。这类操作都是惰性化的（lazy），就是说，仅仅调用到这类方法，并没有真正开始流的遍历。
    Terminal：一个流只能有一个 terminal 操作，当这个操作执行后，流就被使用“光”了，无法再被操作。所以这必定是流的最后一个操作。Terminal 操作的执行，才会真正开始流的遍历，并且会生成一个结果，或者一个 side effect。
    
    Intermediate：
    map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
    
    Terminal：
    forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
    
    Short-circuiting：
    anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
    
    
    Stream 有三个 match 方法，从语义上说：
    allMatch：Stream 中全部元素符合传入的 predicate，返回 true
    anyMatch：Stream 中只要有一个元素符合传入的 predicate，返回 true
    noneMatch：Stream 中没有一个元素符合传入的 predicate，返回 true