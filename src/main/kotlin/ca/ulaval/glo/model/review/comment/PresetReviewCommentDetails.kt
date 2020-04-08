package ca.ulaval.glo.model.review.comment

import ca.ulaval.glo.model.review.comment.CommentTag.CLEAN_CODE
import kotlin.reflect.full.declaredMemberProperties

val CLEAN_CODE_TAGS = mutableListOf(CLEAN_CODE)

class PresetReviewCommentDetails {
    val cleanCodeC01InappropriateInformation =
        ReviewCommentDetails(
            "[C01] - Inappropriate Information",
            "It is inappropriate for a comment to hold information better held in a different kind of system such as your source code control system, your issue tracking system, or any other record-keeping system. Change histories, for example, just clutter up source files with volumes of historical and uninteresting text. In general, meta-data such as authors, last-modified-date, SPR number, and so on should not appear in comments. Comments should be reserved for technical notes about the code and design.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeC02ObsoleteComment = ReviewCommentDetails(
        "[C02] - Obsolete Comment",
        "A comment that has gotten old, irrelevant, and incorrect is obsolete. Comments get old quickly. It is best not to write a comment that will become obsolete. If you find an obsolete comment, it is best to update it or get rid of it as quickly as possible. Obsolete comments tend to migrate away from the code they once described. They become floating islands of irrelevance or misdirection.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeC03RedundantComment = ReviewCommentDetails(
        "[C03] - Redundant Comment",
        "A comment that has gotten old, irrelevant, and incorrect is obsolete. Comments get old quickly. It is best not to write a comment that will become obsolete. If you find an obsolete comment, it is best to update it or get rid of it as quickly as possible. Obsolete comments tend to migrate away from the code they once described. They become floating islands of irrelevance or misdirection. Comments should say things that the code cannot say for itself.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeC04PoorlyWrittenComment = ReviewCommentDetails(
        "[C04] - Poorly Written Comment",
        "A comment worth writing is worth writing well. If you are going to write a comment, take the time to make sure it is the best comment you can write. Choose your words carefully. Use correct grammar and punctuation. Don’t ramble. Don’t state the obvious. Be brief.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeC05CommentedOutCode = ReviewCommentDetails(
        "[C05] - Commented-Out Code",
        "It makes me crazy to see stretches of code that are commented out. Who knows how old it is? Who knows whether or not it’s meaningful? Yet no one will delete it because everyone assumes someone else needs it or has plans for it. That code sits there and rots, getting less and less relevant with every passing day. It calls functions that no longer exist. It uses variables whose names have changed. It follows conventions that are obsolete. It pollutes the modules that contain it and distracts the people who try to read it. Commented-out code is an abomination.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeE01BuildRequiresMoreThanOneStep =
        ReviewCommentDetails(
            "[E01] - Build Requires More Than One Step",
            "Building a project should be a single trivial operation. You should not have to check many little pieces out from source code control. You should not need a sequence of arcane commands or context dependent scripts in order to build the individual elements. You should not have to search near and far for all the various little extra JARs, XML files, and other artifacts that the system requires. You should be able to check out the system with one simple command and then issue one other simple command to build it.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeE02TestsRequireMoreThanOneStep =
        ReviewCommentDetails(
            "[E02] - Tests Require More Than One Step",
            "You should be able to run all the unit tests with just one command. In the best case you can run all the tests by clicking one button in your IDE. In the worst case you should be able to issue a single simple command in a shell. Being able to run all the tests is so fundamental and so important that it should be quick, easy, and obvious to do.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeF01TooManyArguments = ReviewCommentDetails(
        "[F01] - Too Many Arguments",
        "Functions should have a small number of arguments. No argument is best, followed by one, two, and three. More than three is very questionable and should be avoided with prejudice.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeF02OutputArguments = ReviewCommentDetails(
        "[F02] - Output Arguments",
        "Output arguments are counterintuitive. Readers expect arguments to be inputs, not outputs. If your function must change the state of something, have it change the state of the object it is called on.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeF03FlagArguments = ReviewCommentDetails(
        "[F03] - Flag Arguments",
        "Boolean arguments loudly declare that the function does more than one thing. They are confusing and should be eliminated.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeF04DeadFunction = ReviewCommentDetails(
        "[F04] - Dead Function",
        "Methods that are never called should be discarded. Keeping dead code around is wasteful. Don’t be afraid to delete the function. Remember, your source code control system still remembers it.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeG01MultipleLanguagesInOneSourceFile =
        ReviewCommentDetails(
            "[G01] - Multiple Languages in One Source File",
            "Today’s modern programming environments make it possible to put many different languages into a single source file. For example, a Java source file might contains snippets of XML, HTML, YAML, JavaDoc, English, JavaScript, and so on. For another example, in addition to HTML a JSP file might contain Java, a tag library syntax, English comments, Javadocs, XML, JavaScript, and so forth. This is confusing at best and carelessly sloppy at worst. The ideal is for a source file to contain one, and only one, language. Realistically, we will probably have to use more than one. But we should take pains to minimize both the number and extent of extra languages in our source files.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG02ObviousBehaviorIsUnimplemented =
        ReviewCommentDetails(
            "[G02] - Obvious Behavior Is Unimplemented",
            "Following “The Principle of Least Surprise”, any function of class should implement the behaviors that another programmer could reasonably expect. When an obvious behavior is not implemented, readers and users of the code can no longer depend on their intuition about function names. They lose their trust in the original author and must fall back on reading the details of the code.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG03IncorrectBehaviorAtTheBoundaries =
        ReviewCommentDetails(
            "[G03] - Incorrect Behavior at the Boundaries",
            "It seems obvious to say that code should behave correctly. The problem is that we seldom realize just how complicated correct behavior is. Developers often write functions that they think will work, and then trust their intuition rather than going to the effort to prove that their works in all the corner and boundary cases. There is no replacement for due diligence. Every boundary condition, every corner case, every quirk and exception represents something that can confound an elegant and intuitive algorithm. Don’t rely on your intuition. Look for every boundary condition and write a test for it.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG04OverriddenSafeties =
        ReviewCommentDetails(
            "[G04] - Overridden Safeties",
            "Chernobyl melted down because the plan manager overrode each of the safety mechanisms one by one. The safeties were making it inconvenient to run an experiment. The result was that the experiment did not get run, and the world saw it’s first major civilian nuclear catastrophe.\nIt is risky to override safeties. Exerting manual control over serialVersionUID may be necessary, but it is always risky. Turning off certain compiler warnings (or all warnings!) may help you get the build to succeed, but at the risk of endless debugging sessions. Turning off failing tests and telling yourself you’ll get them to pass later is as bad as pretending your credit cards are free money.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG05Duplication =
        ReviewCommentDetails(
            "[G05] - Duplication",
            "This is one of the most important rules in this book, and you should take it very seriously. Virtually every author who writes about software design mentions this rule. Dave Thomas and Andy Hunt called it the DRY1 principle (Don’t Repeat Yourself). Kent Beck made it one of the core principles of Extreme Programming and call it: “Once, and only once.” Ron Jeffries ranks this rule second, just below getting all the tests to pass.\nEvery time you see duplication in the code, it represents a missed opportunity for abstraction. That duplication could probably become a subroutine or perhaps another class outright. By folding the duplication into such an abstraction, you increase the vocabulary of the language of your design. Other programmers can use the abstract facilities you create. Coding becomes faster and less error prone because you have raised the abstraction level.\nThe most obvious form of duplication is when you have clumps of identical code that look like some programmers went wild with the mouse, pasting the same code over and over again. These should be replaced with simple methods.\nA more subtle form is the switch/case or if/else chain that appears again and again in various modules, always testing for the same set of conditions. These should be replaced with polymorphism.\nStill more subtle are the modules that have similar algorithms, but that don’t share similar lines of code. This is still duplication and should be addressed by using the Template Method2 or Strategy pattern3.\nIndeed, most of the design patterns that have appeared in the last fifteen years are simply well-known ways to eliminate duplication. So too the Codd Normal Forms are a strategy for eliminating duplication. Not surprisingly, so is structured programming.\nI think the point has been made. Find and eliminate duplication wherever you can.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG06CodeAtWrongLevelOfAbstraction =
        ReviewCommentDetails(
            "[G06] - Code at Wrong Level of Abstraction",
            "It is important to create abstractions that separate higher level general concepts from lower level detailed concepts. Sometimes we do this by creating abstract classes to hold the higher level concepts and derivatives to hold the lower level concepts, When we do this, we need to make sure that the separation is complete. We want all the lower level concepts to be in the derivatives and all the higher level concepts to be in the base class.\nFor example, constants, variables, or utility functions that pertain only to the detailed implementation should not be present in the base class.\nThis rule also pertains to source files, components, and modules. Good software design requires that we separate concepts at different levels and place them in different containers. Sometimes these containers are base classes or derivatives and sometimes they are source files, modules, or components. Whatever the case may be, the separation needs to be complete. We don’t want lower and higher level concepts mixed together.\nThe point is that you cannot lie or fake your way out of misplaced abstraction. Isolating abstractions is one of the hardest things that software developers do, and there is no quick fix when you get it wrong.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG07BaseClassesDependingOnTheirDerivatives =
        ReviewCommentDetails(
            "[G07] - Base Classes Depending on Their Derivatives",
            "The most common reason for partitioning concepts into base and derivative classes is so that the higher level base class concepts can be independent of the lower level derivative class concepts. Therefore, when we see base classes mentioning the names of their derivatives, we suspect a problem. In general, base classes should know nothing about their derivatives.\nThere are exceptions to this rule, of course. Sometimes the number of derivatives is strictly fixed, and the base class has code that selects between the derivatives. We see this a lot in finite state machine implementations. However, in that case the derivatives and base class are strongly coupled and always deploy together in the same jar file. In the general case we want to be able to deploy derivatives and bases in different jar files.\nDeploying derivatives and bases in different jar files and making sure the base jar files know nothing about the contents of the derivative jar files allows us to deploy our systems in discrete and independent components. When such components are modified, they can be redeployed without having to redeploy the base components. This means that the impact of change is greatly lessened, and maintaining systems in the field is made much simpler.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG08TooMuchInformation =
        ReviewCommentDetails(
            "[G08] - Too Much Information",
            "Well-defined modules have very small interfaces that allow you to do a lot with a little. Poorly defined modules have wide and deep interfaces that force you to use many different gestures to get simple things done. A well-defined interface does not offer very many functions to depend upon, so coupling is low. A poorly defined interface provides lots of functions that you must call, so coupling is high.\nGood software developers learn to limit what they expose at the interfaces of their classes and modules. The fewer methods a class has, the better, The fewer variables a function knows about, the better. The fewer instance variables a class has, the better.\nHide your data. Hide your utility functions. Hide your constants and your temporaries. Don’t create classes with lots of methods or lots of instance variables. Don’t create lots of protected variables and functions for your subclasses. Concentrate on keeping interfaces very tight and small. Help keep coupling low by limiting information.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG09DeadCode =
        ReviewCommentDetails(
            "[G09] - Dead Code",
            "Dead code is code that isn’t executed. You find it in the body of an if statement that checks for a condition that can’t happen. You find it in the catch block of a try that never throws. You find it in little utility methods that are never called or switch/case conditions that never occur.\nThe problem with dead code is that after awhile it starts to smell. The older it is, the stronger and sourer the odor becomes. This is because dead code is not completely updated when designs change. It still compiles, but it does not follow newer conventions or rules. It was written at a time when the system was different. When you find dead code, do the right thing. Give it a decent burial. Delete it from the system.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG10VerticalSeparation =
        ReviewCommentDetails(
            "[G10] - Vertical Separation",
            "Variables and function should be defined close to where they are used. Local variables should be declared just above their first usage and should have a small vertical scope. We don’t want local variables declared hundreds of lines distant from their usages.\nPrivate functions should be defined just below their first usage. Private functions belong to the scope of the whole class, but we’d still like to limit the vertical distance between the invocations and definitions. Finding a private function should just be a matter of scanning downward from the first usage.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG11Inconsistency =
        ReviewCommentDetails(
            "[G11] - Inconsistency",
            "If you do something a certain way, do all similar things in the same way. This goes back to the principle of least surprise. Be careful with the conventions you choose, and once chosen, be careful to continue to follow them.\nIf within a particular function you use a variable names response to hold an HttpServletResponse, then use the same variable name consistently in the other functions that use HttpServletResponse objects. If you name a method processVerificationRequest, then use a similar name, such as processDeletionRequest, for the methods that process other kinds of requests.\nSimple consistency like this, when reliably applied, can make code much easier to read and modify.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG12Clutter =
        ReviewCommentDetails(
            "[G12] - Clutter",
            "Of what use is a default constructor with no implementation? All it serves to do is clutter up the code with meaningless artifacts. Variables that aren’t used, functions that are never called, comments that add no information, and so forth. All these things are clutter and should be removed. Keep your source files clean, well organized, and free of clutter.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG13ArtificialCoupling =
        ReviewCommentDetails(
            "[G13] - Artificial Coupling",
            "Things that don’t depend upon each other should not be artificially coupled. For example, general enums should not be contained within more specific classes because this forces the whole application to know about these more specific classes. The same goes for general purpose static functions being declared in specific classes.\nIn general an artificial coupling is a coupling between two modules that serves no direct purpose. It is a result of putting a variable, constant, or function in a temporarily convenient, though inappropriate, location. This is lazy and careless.\nTake the time to figure out where functions, constants, and variables ought to be declared. Don’t just toss them in the most convenient place at hand and then leave them there.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG14FeatureEnvy =
        ReviewCommentDetails(
            "[G14] - Feature Envy",
            "This is one of Martin Fowler’s code smells. The methods of a class should be interested in the variables and functions of the class they belong to, and not the variables and functions of other classes. When a method uses accessors and mutators of some other object to manipulate the data within that object, then it envies the scope of the class of that other object. It wishes that it were inside that other class so that it could have direct access to the variables it is manipulating.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG15SelectorArguments =
        ReviewCommentDetails(
            "[G15] - Selector Arguments",
            "There is hardly anything more abominable than a dangling false argument at the end of a function call. What does it mean? What would it change if it were true? Not only is the purpose of a selector argument difficult to remember, each selector argument combines many functions into one. Selector arguments are just a lazy way to avoid splitting a large function into several smaller functions.\nOf course, selectors needs not be boolean. They can be enums, integers, or any other type of argument that is used to select the behavior of the function. In general it is better to have many functions than to pass some code into a function to select the behavior.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG16ObscuredIntent =
        ReviewCommentDetails(
            "[G16] - Obscured Intent",
            "We want code to be as expressive as possible. Run-on expressions, Hungarian notation, and magic numbers all obscure the author’s intent.\nSmall and dense as this might appear, it’s also virtually impenetrable. It is worth taking the time to make the intent of our code visible to our readers.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG17MisplacedResponsibility =
        ReviewCommentDetails(
            "[G17] - Misplaced Responsibility",
            "One of the most important decisions a software developer can make is where to put code. For example, where should the PI constant go? Should it be in the Math class? Perhaps it belongs in the Trigonometry class? Or maybe in the Circle class?\nThe principle of least surprise comes into play here. Code should be placed where a reader would naturally expect it to be. The PI constant should go where the trig functions are declared. The OVERTIME_RATE constant should be declared in the HourlyPayCalculator class.\nSometimes we get “clever” about where to put certain functionality. We’ll put it in a function that’s convenient for us, but not necessarily intuitive to the reader. For example, perhaps we need to print a report with the total of hours that an employee worked. We could sum up those hours in the code that prints the report, or we could try to keep a running total in the code that accepts time cards.\nOne way to make this decision is to look at the names of the functions. Let’s say that our report module has a function named getTotalHours. Let’s also say that the module that accepts time cards has a saveTimeCard function. Which of these functions, by it’s name, implies that it calculates the total? The answer should be obvious.\nClearly, there are sometimes performance reasons why the total should be calculated as time cards are accepted rather than when the report is printed. That’s fine, but the names of the functions ought to reflect this. For example, there should be a computeRunningTotalOfHour function in the timecard module.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG18InappropriateState =
        ReviewCommentDetails(
            "[G18] - Inappropriate State",
            "Math.max(double a, double b) is a good static method. It does not operate on a single instance; indeed it would be silly to have to say new Math().max(a, b) or even a.max(b). All the data that max uses comes from its two arguments, and not from any “owning” object. More to the point, there is almost no chance that we’d want Math.max to be polymorphic.\nSometimes, however, we write static functions that should not be static.\nIn general you should prefer nonstatic methods to static methods. When in doubt, make the function nonstatic. If you really want a function to be static, make sure that there is no chance that you’ll want it to behave polymorphically.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG19UseExplanatoryVariables =
        ReviewCommentDetails(
            "[G19] - Use Explanatory Variables",
            "Kent Beck wrote about this in his great book Smalltalk Best Practice Patterns1 and again more recently in his equally great book Implementation Patterns2. One of the more powerful ways to make a program readable is to break the calculations up into intermediate values that are held in variables with meaningful names.\nIt is hard to overdo this. More explanatory variables are generally better than fewer. It is remarkable how an opaque module can suddenly become transparent simply by breaking the calculations up into well-named intermediate values.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG20FunctionNamesShouldSayWhatTheyDo =
        ReviewCommentDetails(
            "[G20] - Function Names Should Say What They Do",
            "If you have to look at the implementation (or documentation) of the function to know what it does, then you should work to find a better name or rearrange the functionality so that it can be placed in functions with better names.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG21UnderstandTheAlgorithm =
        ReviewCommentDetails(
            "[G21] - Understand the Algorithm",
            "Lots of very funny code is written because people don’t take the time to understand the algorithm. They get something to work by plugging in enough if statements and flags, without really stopping to consider what is really going on.\nProgramming if often an exploration. You think you know the right algorithm for something, but then you wind up fiddling with it, prodding and poking at it, until you get it to “work”. How do you know it “works”? Because it passes the test cases you can think of.\nThere is nothing wrong with this approach. Indeed, often it is the only way to get a function to do what you think it should. However, it is not sufficient to leave the quotation marks around the work “work”.\nBefore you consider yourself to be done with a function, make sure you understand how it works. It is not good enough that it passes all the tests. You must know1 that the solution is correct.\nOften the best way to gain this knowledge and understanding is to refactor the function into something that is so clean and expressive that it is obvious how it works.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG22MakeLogicalDependenciesPhysical =
        ReviewCommentDetails(
            "[G22] - Make Logical Dependencies Physical",
            "If one module depends upon another, that dependency should be physical, not just logical. The dependent module should not make assumptions (in other words, logical dependencies) about the module it depends upon. Rather it should ask that module for all the information it depends upon.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG23PreferPolymorphismToIfElseOrSwitchCase =
        ReviewCommentDetails(
            "[G23] - Prefer Polymorphism to If/Else or Switch/Case",
            "This might seem a strange suggestion given the topic of Chapter 6. After all, in that chapter I make the point that switch statements are probably appropriate in the parts of the system where adding new functions is more likely than adding new types.\nFirst, most people use switch statements because it’s the obvious brute force solution, not because it’s the right solution for the situation. So this heuristic is here to remind us to consider polymorphism before using a switch.\nSecond, the cases where functions are more volatile than types are relatively rare. So every switch statement should be suspect.\nI use the following “ONE SWITCH” rule: There may be no more than one switch statement for a given type of selection. The cases in that switch statement must create polymorphic objects that take the place of other switch statements in the rest of the system.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG24FollowStandardConvention =
        ReviewCommentDetails(
            "[G24] - Follow Standard Convention",
            "Every team should follow a coding standard based on common industry norms. This coding standard should specify things like where to declare instance variables; how to name classes, methods, and variables; where to put braces; and so on. The team should not need a document to describe these conventions because their code provides examples.\nEveryone on the team should follow these conventions. This means that each team member must be mature enough to realize that it doesn’t matter a white where you put your braces so long as you all agree on where to put them.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG25ReplaceMagicNumbersWithNamedConstants =
        ReviewCommentDetails(
            "[G25] - Replace Magic Numbers with Named Constants",
            "This is probably one of the oldest rules in software development. I remember reading it in the late sixties in introductory COBOL, FORTRAN, and PL/I manuals. In general it is a bad idea to have raw numbers in your code. you should hide them behind well-named constants.\nThe term “Magic Number” does not apply only to numbers. It applies to any token that has value that is not self-describing.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG26BePrecise =
        ReviewCommentDetails(
            "[G26] - Be Precise",
            "Expecting the first match to be the only match to a query is probably naive. Using floating point numbers to represent currency is almost criminal. Avoiding locks and/or transaction management because you don’t think concurrent update is likely is lazy at best. Declaring a variable to be an ArrayList when a List will do is overly constraining. Making all variables protected by default is not constraining enough.\nWhen you make a decision in your code, make sure you make it precisely. Know why you have made it and how you will deal with any exceptions. Don’t be lazy about the precision of your decisions. If you decide to call a function that might return null, make sure you check for null. If you query for what you think is the only record in the database, make sure your code checks to be sure there aren’t others. If you need to deal with currency, use integers1 and deal with rounding appropriately. If there is the possibility of concurrent update, make sure you implement some kind of locking mechanism.\nAmbiguities and imprecision in code are either a result of disagreements or laziness. In either case they should be eliminated.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG27StructureOverConvention =
        ReviewCommentDetails(
            "[G27] - Structure over Convention",
            "Enforce design decisions with structure over convention. Naming conventions are good, but they are inferior to structures that force compliance. For example, switch/cases with nicely named enumerations are inferior to base classes with abstract methods. No one is forced to implement the switch/case statement the same way each time; but the base classes do enforce that concrete classes have all abstract methods implemented.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG28EncapsulateConditionals =
        ReviewCommentDetails(
            "[G28] - Encapsulate Conditionals",
            "Boolean logic is hard enough to understand without having to see it in the context of an if or while statement. Extract functions that explain the intent of the conditional.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG29AvoidNegativeConditionals =
        ReviewCommentDetails(
            "[G29] - Avoid Negative Conditionals",
            "Negatives are just a bit harder to understand than positives. So, when possible, conditionals should be expressed as positives.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG30FunctionsShouldDoOneThing =
        ReviewCommentDetails(
            "[G30] - Functions Should Do One Thing",
            "It is often tempting to create functions that have multiple sections that perform a series of operations. Functions of this kind do more than one thing, and should be converted into many smaller functions, each of which does one thing.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG31HiddenTemporalCoupling =
        ReviewCommentDetails(
            "[G31] - Hidden Temporal Coupling",
            "Temporal couplings are often necessary, but you should not hide the coupling. Structure the arguments of your functions such that the order in which they should be called is obvious.\nEach function produces a result that the next function needs, so there is no reasonable way to call them out of order.\nYou might complain that this increases the complexity of the functions, and you’d be right. But that extra syntactic complexity exposes the true temporal complexity of the situation.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG32DontBeArbitrary =
        ReviewCommentDetails(
            "[G32] - Don't Be Arbitrary",
            "Have a reason for the way you structure your code, and make sure that reason is communicated by the structure of the code. If a structure appears consistently throughout the system, others will use it and preserve the convention.\nPublic classes that are not utilities of some other class should not be scoped inside another class. The convention is to make them public att the top level of their package.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG33EncapsulateBoundaryConditions =
        ReviewCommentDetails(
            "[G33] - Encapsulate Boundary Conditions",
            "Boundary conditions are hard to keep track of. Put the processing of them in one place. Don’t let them leak all over the code. We don’t want swarms of +1s and -1s scatter hither and yon.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG34FunctionsShouldDescendOnlyOneLevelOfAbstraction =
        ReviewCommentDetails(
            "[G34] - Functions Should Descend Only One Level of Abstraction",
            "The statements within a function should all be written at the same level of abstraction, which should be one level below the operation described by the name of the function. This may be the hardest of these heuristics to interpret and follow. Though the idea is plain enough, humans are just far too good at seamlessly mixing levels of abstraction.\nSeparating levels of abstraction is one of the most important functions of refactoring, and it’s one of the hardest to do well.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG35KeepConfigurableDataAtHighLevels =
        ReviewCommentDetails(
            "[G35] - Keep Configurable Data at High Levels",
            "If you have a constant such as a default or configuration value that is known and expected at a high level of abstraction, do not bury it in a low-level function. Expose it as an argument to that low-level function called from the high-level function.\nThe configuration constants reside at a very high level and are easy to change. They get passed down to the rest of the application. The lower levels of the application do not own the values of these constants.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeG36AvoidTransitiveNavigation =
        ReviewCommentDetails(
            "[G36] - Avoid Transitive Navigation",
            "In general we don’t want a single module to know much about its collaborators. More specifically, if A collaborates with B, and B collaborates with C, we don’t want modules that use A to know about C.\nThis is sometimes called the Law of Demeter. The Pragmatic Programmers call it “Writing Shy Code.”[^12] In either case it comes down to making sure that modules know only about their immediate collaborators and do not know the navigation map of the whole system.\nIf many modules used some form of the statement a.getB().getC(), then it would be difficult to change the design and architecture to interpose a Q between B and C. You’d have to find every instance of a.getB().getC() and convert it to a.getB().getQ().getC(). This is how architectures become rigid. Too many modules know too much about the architecture.\nRather we want our immediate collaborators to offer all the services we need. We should not have to roam through the object graph of the system, hunting for the method we want to call.",
            CLEAN_CODE_TAGS
        )

    // TODO: finish list
    val cleanCodeT01InsufficientTests = ReviewCommentDetails(
        "[T01] - Insufficient Tests",
        "How many tests should be in a test suite? Unfortunately, the metric many programmers use is “That seems like enough”. A test suite should test everything that could possible break. The tests are insufficient so long as there are conditions that have not been explored by the tests or calculation that have not been validated.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeT02UseACoverageTool = ReviewCommentDetails(
        "[T02] - Use a Coverage Tool!",
        "Coverage tools report gaps in your testing strategy. They make it easy to find modules, classes, and functions that are insufficiently tested. Most IDEs give you a visual indication, marking lines that are covered in green and those that are uncovered in red. This makes it quick and easy to find if or catch statements whose bodies haven’t been checked.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeT03DontSkipTrivialTests = ReviewCommentDetails(
        "[T03] - Don't Skip Trivial Tests",
        "They are easy to write and their documentary value is higher than the code to produce them.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeT04AnIgnoredTestIsAQuestionAboutAnAmbiguity =
        ReviewCommentDetails(
            "[T04] - An Ignored Test Is a Question about an Ambiguity",
            "Sometimes we are uncertain about a behavioral detail because the requirements are unclear. We can express our question about the requirements as a test that is commented out, or as a test that annotated with @Ignore. Which you choose depends upon whether the ambiguity is about something that would compile or not.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeT05TestBoundaryConditions = ReviewCommentDetails(
        "[T05] - Test Boundary Conditions",
        "Take special care to test boundary conditions. We often get the middle of an algorithm right but misjudge the boundaries.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeT06ExhaustivelyTestNearBugs =
        ReviewCommentDetails(
            "[T06] - Exhaustively Test Near Bugs",
            "Bugs tend to congregate. When you find a bug in a function, it is wise to do an exhaustive test of that function. You’ll probably find that the bug was not alone.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeT07PatternsOfFailureAreRevealing =
        ReviewCommentDetails(
            "[T07] - Patterns of Failure Are Revealing",
            "Sometimes you can diagnose a problem by finding patterns in the way the test cases fail. This is another argument for making the test cases as complete as possible. Complete test cases, ordered in a reasonable way, expose patterns. As a simple example, suppose you noticed that all tests with an input larger than five characters failed? or what if any test that passed a negative number into a second argument of a function failed? Sometimes just seeing the pattern of red and green on the test report is enough to spark the “Aha!” that leads to the solution. Look page at page 267 to see an interesting example of this in the SerialDate example.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeT08TestCoveragePatternsCanBeRevealing =
        ReviewCommentDetails(
            "[T08] - Test Coverage Patterns Can Be Revealing",
            "Looking at the code that is or is not executed by the passing tests gives clues to why the failing tests fail.",
            CLEAN_CODE_TAGS
        )
    val cleanCodeT09TestsShouldBeFast = ReviewCommentDetails(
        "[T09] - Tests Should Be Fast",
        "A slow test is a test that won’t get run. When things get tight, it’s the slow tests that will be dropped from the suite. So do what you must to keep your tests fast.",
        CLEAN_CODE_TAGS
    )
}

fun getAllPresetReviewCommentDetails(): Array<ReviewCommentDetails> {
    return PresetReviewCommentDetails::class.declaredMemberProperties.map(fun(x): ReviewCommentDetails {
        return x.get(PresetReviewCommentDetails()) as ReviewCommentDetails
    }).toTypedArray()
}
